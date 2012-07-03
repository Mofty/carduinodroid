package swp.tuilmenau.carduinodroid.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import swp.tuilmenau.carduinodroid.R;
import swp.tuilmenau.carduinodroid.model.LOG;
import android.app.Activity;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
/**
 * The Cam Class is used to set the settings, initialized the cam, the socket and the preview.
 * @author Robin
 * @see android.hardware.Camera
 */
public class Cam implements CameraCallback
{
	Camera camera;
	Parameters parameters;

	LOG log;
	private Controller_Android controller;
	private Activity activity;
	private CameraSurface camerasurface;
	private ViewGroup cameraholder;
	private Socket client;
	private OutputStream os;
	private ServerSocket ss;
	private int quality;
	private boolean inPreviewFrame = false;
	public int height;
	public int width;
	public MyPreviewCallback previewcallback;
	public int previewFormat;
	
	/**
	 * This is the constructor of the Cam-Class. In this Method the Camera object and the Serversocket are created
	 * @param controller
	 * @param activity
	 */
	public Cam(Controller_Android controller, Activity activity)
	{	
		previewcallback = new MyPreviewCallback(this);
		Log.e("cam", "cam erstellung gestartet");
		quality = 30;
		this.activity = activity;
		camera = Camera.open();
		cameraholder = (ViewGroup) activity.findViewById(R.id.preview);
		parameters = camera.getParameters();
		width = parameters.getPreviewSize().width;
		height = parameters.getPreviewSize().height;
		previewFormat = parameters.getPreviewFormat();
		this.controller = controller;
		parameters.setRotation(270);
		camera.setParameters(parameters);
		Log.e("cam", "cam erstellung fertig");
		camera.startPreview();
		Log.e("cam", "preview gestartet");
		setupPictureMode();
		Thread t = new Thread(new Runnable(){
			public void run() {
				ss = null;
				client = null;
				Log.e("thread camera","thread camera gestartet");
				try {
					ss = new ServerSocket(12347);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					Log.e("thread camera","serversocket fehlgeschlagen");
				}
				try {
					client = ss.accept();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					Log.e("thread camera","accept fehlgeschlagen");
				}
				try {
					os = client.getOutputStream();
					Log.e("thread camera","outputstream gesetzt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					Log.e("thread camera","output bekommen fehlgeschlagen");
				}

				if(client != null)
					Log.e("thread camera","javaprog gefunden" + client.getInetAddress().toString());
			}
		});
		t.start();
	}
	/**
	 * This Method enables the flashlight of the camera
	 */
	public void enableFlash()
	{
		parameters = camera.getParameters();
		parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
		camera.setParameters(parameters);
	}
	/**
	 * This Method disables the flashlight of the camera
	 */
	public void disableFlash()
	{
		parameters = camera.getParameters();
		parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
		camera.setParameters(parameters);
	}
	/**
	 * This Method release the current Camera and starts the camera with the ID
	 * 
	 * @param id of the camera to access
	 * 
	 * @see android.hardware.Camera#open(int)
	 */
	public void switchCam(int cameraId) {

	    if (camera != null) {
	        stopCamera();
	    }
	    
	    camera = Camera.open(cameraId);
        try {
            camera.setPreviewDisplay(camerasurface.holder);
            camera.setPreviewCallback(previewcallback);
    } catch (IOException e) { e.printStackTrace(); }
	    camerasurface.setCamera(camera);
		camerasurface.setCallback(this);
	    parameters = camera.getParameters();
	    width = parameters.getPreviewSize().width;
	    height = parameters.getPreviewSize().height;
	    previewFormat = parameters.getPreviewFormat();
	    camera.startPreview();
	}
	
	/**
	 * Change the Resolution of the preview pictures
	 * @param width the width of the pictures, in pixels
	 */
	public void changeRes(int index)
	{
		while(inPreviewFrame)
		{
			
		}
		camera.stopPreview();
		Log.e("cam", "preview stop changeres");
		List<Size> temp = parameters.getSupportedPreviewSizes();
		int newwidth = temp.get(temp.size()-1-index).width;
		int newheight = temp.get(temp.size()-1-index).height;
		width = newwidth;
		height = newheight;
		parameters = camera.getParameters();
		parameters.setPreviewSize(newwidth, newheight);
		camera.setParameters(parameters);
		camera.startPreview();
		Log.e("cam", "preview restarted changeres");
	}
	/**
	 * Set the preview frame rate
	 * @param fps the frame rate
	 */
	public void changeFPS(int fps)
	{
		List<Integer> temp = parameters.getSupportedPreviewFrameRates();
		if(temp.contains(Integer.valueOf(fps))){
			parameters.setPreviewFrameRate(fps);
			camera.setParameters(parameters);
		}
		else
		{
			controller.log.write(LOG.WARNING, fps + " fps not supported");
		}
	}
	/**
	 * Releases the camera
	 */
	public void disableCamera()
	{
		disableFlash();
		camera.stopPreview();
		camera.release();
	}



	/**
	 * Sets the Surface and the Callback
	 */
	private void setupPictureMode(){
		if(camerasurface  == null)
		camerasurface = new CameraSurface(activity, camera, this);
		else
			camerasurface.setCamera(camera);
		cameraholder.removeAllViews();
		cameraholder.addView(camerasurface, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

		camerasurface.setCallback(this);
	}
	/**
	 * not used
	 */
	public void onJpegPictureTaken(byte[] data, Camera camera) {
	}

	/**
	 *   Called as preview frames are displayed. Compress the data too a jpeg-file and send it to the java-program
	 */
	public void onPreviewFrame(byte[] data, Camera camera) {
		if(os != null && !client.isClosed()){
			inPreviewFrame = true;
			if(camera.getParameters() != null)
		    previewFormat = camera.getParameters().getPreviewFormat();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			YuvImage temp = new YuvImage(data, previewFormat, width,  height, null);
			Rect rect = new Rect(0, 0, width, height);
			temp.compressToJpeg(rect, quality, baos);
			byte[] image = baos.toByteArray();
			try {
				os.write(image);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e("cam", "fehler beim schreiben des previewimage");
		}
			inPreviewFrame = false;
		}}
	
		
		
		public String getSupportedSize(){
			List<Size> supsize = parameters.getSupportedPreviewSizes();
			String result = supsize.get(0).width+"x"+supsize.get(0).height;
			for(int i = 1; i< supsize.size(); i++)
			{
				result = result + ";" +supsize.get(i).width+"x"+supsize.get(i).height;
			}
			return result;
			}
		
		public String[] getSupportedFPS(){
			List<Integer> supfps = parameters.getSupportedPreviewFrameRates();
			String[] result = new String[supfps.size()];
			for(int i = 0; i< supfps.size(); i++)
			{
				result[i] = supfps.get(i).intValue() + "";
			}
			return result;
			}

	/**
	 *   	not used      
	 */
	public void onRawPictureTaken(byte[] data, Camera camera) {
	}

	/**
	 *    	not used    
	 */
	public void onShutter() {
	}
	/**
	 *    	not used
	 */
	public String onGetVideoFilename() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setQuality(int quality){
		if(quality > 100){
			this.quality = 100;
			controller.log.write(LOG.WARNING, "Quality changed to 100");
		}
		else{
			this.quality = quality;
			controller.log.write(LOG.WARNING, "Quality changed to " + quality);
			}
	}
	
	public void close() {
		try {
		if(client != null){
				os = null;
				client.close();
				}
		Thread t = new Thread(new Runnable(){
			public void run() {
				client = null;
					try {
						if(ss != null)
						{
							Log.e("cam","serversocket da");
						}
						client = ss.accept();
						os = client.getOutputStream();
					} catch (IOException e) {
						// TODO Auto-generated catch block
							e.printStackTrace();
					}
			}
		});
		t.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	

	private void stopCamera(){
	    if (camera != null){
	        camera.stopPreview();
	        camera.release();
	        camera = null;
	        camerasurface.holder.removeCallback(camerasurface);
	        camerasurface.setCallback(null);
	    }
	}
}