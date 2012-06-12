package swp.tuilmenau.carduinodroid.controller;

import java.io.IOException;
import java.util.List;

import swp.tuilmenau.carduinodroid.model.LOG;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Cam
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
	private SurfaceView preview;
	private SurfaceHolder previewHolder;

	public Cam(SurfaceView preview, Controller_Android controller)
	{	
		camera = Camera.open();
		this.preview= preview;
		previewHolder = preview.getHolder();
		previewHolder.addCallback(surfaceCallback);
		previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		try {
			camera.setPreviewDisplay(previewHolder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parameters = camera.getParameters();
		socket_Cam = new Socket_Cam(controller);
		cameraPreview = new CameraPreview();
		camera.setPreviewCallback(cameraPreview);
		this.controller = controller;
		camera.startPreview();
		height = parameters.getPreviewSize().height;
		width = parameters.getPreviewSize().width;
		fps = parameters.getPreviewFrameRate();
		flashmode = parameters.getFlashMode();
		Thread camthread = new Thread(socket_Cam);
		camthread.start();

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

	private void startPreview() {
		if (camera!=null) {
			camera.startPreview();
		}
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

	private void initPreview(int width, int height) {
		if (camera!=null && previewHolder.getSurface()!=null) {
			try {
				camera.setPreviewDisplay(previewHolder);
			}
			catch (Throwable t) {
				//TODO
			}

			Camera.Parameters parameters=camera.getParameters();
			Camera.Size size=getBestPreviewSize(width, height);

			if (size!=null) {
				parameters.setPreviewSize(size.width, size.height);
				camera.setParameters(parameters);
			}
		}
	}


	SurfaceHolder.Callback surfaceCallback=new SurfaceHolder.Callback() {
		public void surfaceCreated(SurfaceHolder holder) {
			// no-op -- wait until surfaceChanged()
		}

		public void surfaceChanged(SurfaceHolder holder,
				int format, int width,
				int height) {
			initPreview(width, height);
			startPreview();
		}

		public void surfaceDestroyed(SurfaceHolder holder) {
			// no-op
		}
	};

}
