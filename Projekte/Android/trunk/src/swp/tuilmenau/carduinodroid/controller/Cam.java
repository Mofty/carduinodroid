package swp.tuilmenau.carduinodroid.controller;

import swp.tuilmenau.carduinodroid.model.LOG;
import swp.tuilmenau.carduinodroid.R;

import java.io.*;
import java.net.*;
import java.util.List;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.*;
import android.util.Log;
import android.view.*;
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
	private int height;
	private int width;
	private String flashmode;
	private int fps;
	private Activity activity;
	private CameraSurface camerasurface;
	private ViewGroup cameraholder;
	private Socket client;
	private OutputStream os;
	private ServerSocket ss;
	
	/**
	 * This is the constructor of the Cam-Class. In this Method the Camera object and the Serversocket are created
	 * @param controller
	 * @param activity
	 */
	public Cam(Controller_Android controller, Activity activity)
	{	
		Log.e("cam", "cam erstellung gestartet");
		this.activity = activity;
		camera = Camera.open();
		cameraholder = (ViewGroup) activity.findViewById(R.id.preview);
		parameters = camera.getParameters();
		this.controller = controller;
		parameters.setRotation(270);
		height = parameters.getPreviewSize().height;
		width = parameters.getPreviewSize().width;
		parameters.setPreviewSize(640, 480);
		camera.setParameters(parameters);
		fps = parameters.getPreviewFrameRate();
		flashmode = parameters.getFlashMode();
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
		flashmode = Parameters.FLASH_MODE_TORCH;
	}
	/**
	 * This Method disables the flashlight of the camera
	 */
	public void disableFlash()
	{
		parameters = camera.getParameters();
		parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
		camera.setParameters(parameters);
		flashmode = Parameters.FLASH_MODE_OFF;
	}
	/**
	 * This Method release the current Camera and starts the camera with the ID
	 * 
	 * @param id of the camera to access
	 * 
	 * @see android.hardware.Camera#open(int)
	 */
	public void switchCam(int id)
	{
		camera.stopPreview();
		camera.release();
		camera = Camera.open(id);
		parameters = camera.getParameters();
		parameters.setPreviewFrameRate(fps);
		parameters.setPreviewSize(width, height);
		parameters.setFlashMode(flashmode);
		camera.startPreview();
	}
	/**
	 * Change the Resolution of the preview pictures
	 * @param width the width of the pictures, in pixels
	 * @param height the height of the pictures, in pixels
	 */
	public void changeRes(int width, int height)
	{
		List<Size> temp = parameters.getSupportedPreviewSizes();
		if(temp.contains(camera.new Size(width, height))){
			parameters = camera.getParameters();
			parameters.setPreviewSize(width, height);
			camera.setParameters(parameters);
			this.width = width;
			this.height = height;
		}
		else
		{
			Camera.Size buffer = getBestPreviewSize(width, height);
			if(buffer != null){
				parameters.setPreviewSize(buffer.width, buffer.height);
				this.width = buffer.width;
				this.height = buffer.height;		
			}
		}
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
			this.fps = fps;
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

	private Camera.Size getBestPreviewSize(int width, int height) {
		Camera.Size result=null;
		List<Size> supsize = parameters.getSupportedPreviewSizes();

		for (int i = 0; i<supsize.size(); i++) {
			if (supsize.get(i).width<=width && supsize.get(i).height<=height) {
				if (result==null) {
					result=supsize.get(i);
				}
				else {
					int resultArea=result.width*result.height;
					int newArea=supsize.get(i).width*supsize.get(i).height;

					if (newArea>resultArea) {
						result=supsize.get(i);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Sets the Surface and the Callback
	 */
	private void setupPictureMode(){
		camerasurface = new CameraSurface(activity, camera);

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
		if(os != null){
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			YuvImage temp = new YuvImage(data,camera.getParameters().getPreviewFormat(), camera.getParameters().getPreviewSize().width,  camera.getParameters().getPreviewSize().height, null);
			Rect rect = new Rect(0,0,camera.getParameters().getPreviewSize().width,camera.getParameters().getPreviewSize().height);
			temp.compressToJpeg(rect, 20, baos);
			byte[] image = baos.toByteArray();
			try {
				os.write(image);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e("cam", "fehler beim schreiben des previewimage");
			}
		}
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
}