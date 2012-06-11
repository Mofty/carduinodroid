package swp.tuilmenau.carduinodroid.controller;

import swp.tuilmenau.carduinodroid.model.LOG;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;

public class Cam
{
	Camera camera;
	Parameters parameters;
	
	LOG log;
	
	public Cam(Context context, LOG Log)
	{	
		camera = Camera.open();
		parameters = camera.getParameters();
		camera.startPreview();
	}
	
	public void enableFlash()
	{
		parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
		camera.setParameters(parameters);
	}
	
	public void disableFlash()
	{
		parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
		camera.setParameters(parameters);
	}
	
	public void switchCam(int id)
	{
		
	}
	
	public void changeRes(int width, int height)
	{
		
	}
	
	public void disableCamera()
	{
		camera.release();
	}
	
	
}
