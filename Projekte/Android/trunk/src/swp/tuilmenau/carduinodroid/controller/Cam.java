package swp.tuilmenau.carduinodroid.controller;

import swp.tuilmenau.carduinodroid.model.LOG;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;

public class Cam
{
	Camera camera;
	Parameters parameters;
	Context context;
	
	LOG log;
	
	public Cam(Context CONTEXT, LOG Log)
	{
		boolean flashAvailable;
		context = CONTEXT;
		camera = Camera.open();     
		parameters = camera.getParameters();
		flashAvailable = context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
		if (flashAvailable)
			log.write("Flashlight available");
		else log.write("Flashlight not available");

	}
	
	public void enableFlash()
	{
		parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
		camera.setParameters(parameters);
		camera.startPreview(); // evtl unn�tig
	}
	
	public void disableFlash()
	{
		parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
		camera.setParameters(parameters);
	}
}