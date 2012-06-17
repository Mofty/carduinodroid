package swp.tuilmenau.carduinodroid.controller;

import android.app.Activity;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import swp.tuilmenau.carduinodroid.CarDuinoDroidAppActivity;
import swp.tuilmenau.carduinodroid.model.*;

public class Controller_Android 
{
	public LOG log;
	
	public Arduino arduino;
	public Cam cam;
	public Connection connection;
	public GPS gps;
	public Network network;
	public Record_Sound record_sound;
	public Sound sound;
	
	public int framerate;
	
	public Controller_Android(Activity activity) 
	{
		log = new LOG();
		
		arduino = new Arduino(activity, log);
		cam = new Cam(this, activity);
		connection = new Connection(activity, log);
		gps = new GPS(activity, log);
		record_sound = new Record_Sound(log);
		sound = new Sound(activity);	
				
		final Controller_Android temp = this;
		new Thread(new Runnable()
        {
        	public void run() 
        	{
        		@SuppressWarnings("unused")
				Network network = new Network(temp);
        	}
        }).start();
	}
	
	public String packData() 
	{
		String data;
		data = "";
		if (connection.getMobileAvailable()) data = data + 1 + ";"; 
										else data = data + 0 + ";";
		if (connection.getWLANAvailable())   data = data + 1 + ";"; 
										else data = data + 0 + ";";	
		if (connection.getMobile())			 data = data + 1 + ";"; 
							   			else data = data + 0 + ";";
		if (connection.getWLAN()) 			 data = data + 1 + ";"; 
							 			else data = data + 0 + ";";	
		data = data + gps.getGPS() + ";";
			
		return data;
	}
	
	public void receiveData(String data) 
	{
		String[] parts = new String[(data.length()/2)];
	
		int type;
		int camtype;
		parts = data.split(";");
		type = Integer.parseInt(parts[0]);
		switch (type)
		{
			case 1: {
					 	camtype = Integer.parseInt(parts[1]);
					 	switch (camtype)
					 	{
					 		case 1: {
					 					cam.switchCam(Integer.parseInt(parts[2]));
					 				}
					 				break;
					 		case 2: {
					 					cam.changeRes(Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
					 				}
					 				break;
					 		case 3: {
					 					if (parts[2] == "1") cam.enableFlash();
					 					if (parts[2] == "0") cam.disableFlash();
					 				}
					 				break;
					 		default: log.write(LOG.WARNING, "Unknown camera command from PC");
					 				 break;
					 	}
					};
					break;
			case 2: {
					 	arduino.SendCommand(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
					};
					break;
			case 3: {
					 	sound.horn();
					};
					break;
					
			default: log.write(LOG.WARNING, "unknown command from PC");
					 break;
		}
	}
	
	public void camerPicture() 
	{
		
	}	
}