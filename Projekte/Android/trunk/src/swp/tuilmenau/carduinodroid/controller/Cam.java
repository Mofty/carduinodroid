package swp.tuilmenau.carduinodroid.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

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
import android.widget.FrameLayout;

public class Cam implements CameraCallback
{
	Camera camera;
	Parameters parameters;

	LOG log;
	public CameraPreview cameraPreview;
	public Socket_Cam socket_Cam;
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

	public Cam(FrameLayout frameLayout, Controller_Android controller, Activity activity)
	{	
		Log.v("cam", "cam erstellung gestartet");
		this.activity = activity;
		camera = Camera.open();
		cameraholder = frameLayout;
		parameters = camera.getParameters();
		//socket_Cam = new Socket_Cam(controller);
		cameraPreview = new CameraPreview();
		camera.setPreviewCallback(cameraPreview);
		this.controller = controller;
		height = parameters.getPreviewSize().height;
		width = parameters.getPreviewSize().width;
		fps = parameters.getPreviewFrameRate();
		flashmode = parameters.getFlashMode();
		//Thread camthread = new Thread(socket_Cam, "cam thread");
		//camthread.start();
		Log.v("cam", "cam erstellung fertig");
		camera.startPreview();
		Log.v("cam", "preview gestartet");
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

	public void enableFlash()
	{
		parameters = camera.getParameters();
		parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
		camera.setParameters(parameters);
		flashmode = Parameters.FLASH_MODE_TORCH;
	}

	public void disableFlash()
	{
		parameters = camera.getParameters();
		parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
		camera.setParameters(parameters);
		flashmode = Parameters.FLASH_MODE_OFF;
	}

	@SuppressWarnings("static-access")
	public void switchCam(int id)
	{
		camera.stopPreview();
		camera.release();
		camera.open(id);
		parameters = camera.getParameters();
		parameters.setPreviewFrameRate(fps);
		parameters.setPreviewSize(width, height);
		parameters.setFlashMode(flashmode);
		camera.startPreview();
	}

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

	public void changeFPS(int fps)
	{
		List<Integer> temp = parameters.getSupportedPreviewFrameRates();
		if(temp.contains(new Integer(fps))){
			parameters.setPreviewFrameRate(fps);
			this.fps = fps;
			camera.setParameters(parameters);
		}
		else
		{
			controller.log.write(LOG.WARNING, fps + " fps not supported");
		}
	}

	public void disableCamera()
	{
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


    private void setupPictureMode(){
        camerasurface = new CameraSurface(activity, camera);
        
        cameraholder.addView(camerasurface, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        
        camerasurface.setCallback(this);
    }
  
        public void onJpegPictureTaken(byte[] data, Camera camera) {
        }

        
        public void onPreviewFrame(byte[] data, Camera camera) {
        		if(os != null){
    			ByteArrayOutputStream baos = new ByteArrayOutputStream();
				YuvImage temp = new YuvImage(data,camera.getParameters().getPreviewFormat(), camera.getParameters().getPreviewSize().width,  camera.getParameters().getPreviewSize().height, null);
				Rect rect = new Rect(0,0,camera.getParameters().getPreviewSize().width,camera.getParameters().getPreviewSize().height);
				Log.e("cam", temp.toString());
				temp.compressToJpeg(rect, 30, baos);
				byte[] image = baos.toByteArray();
				Log.e("cam", image.length + " arraygröße");
				try {
					os.write(image);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		}else
					Log.e("cam","outputstream null");
        }

        
        public void onRawPictureTaken(byte[] data, Camera camera) {
        }

        
        public void onShutter() {
        }

		public String onGetVideoFilename() {
			// TODO Auto-generated method stub
			return null;
		}
}