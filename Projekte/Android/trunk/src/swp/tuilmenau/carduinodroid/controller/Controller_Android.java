package swp.tuilmenau.carduinodroid.controller;

import android.app.Activity;
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
		cam = new Cam(activity, log);
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
		byte[] buffer = new byte[1];
		
		parts = data.split(";");
		type = Integer.parseInt(parts[0]);
		switch (type)
		{
			case 1:	{
					 	//change camera settings
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
					
			default: log.write("unknown command from PC");
					 break;
		}
	}
	
	public void camerPicture() 
	{
		
	}	
}