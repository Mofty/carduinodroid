package swp.tuilmenau.carduinodroid.controller;

import android.content.Context;
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
	
	int framerate;
	
	public Controller_Android(Context context) 
	{
		log = new LOG();
		
		arduino = new Arduino(context,log);
		cam = new Cam(context, log);
		connection = new Connection(context);
		gps = new GPS(context,/* zu testzwecken. in der finalen version löschen */ log);
		// network = new Network(this);
		record_sound = new Record_Sound(log);
		sound = new Sound(context);	
		
		final Controller_Android temp = this;
		new Thread(new Runnable()
        {
        	public void run() 
        	{
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
		
		//kammera information anhängen + string schicken!
		
		return data;
		
	}
	
	public void receiveSteuerdaten(String Steuerdaten) 
	{
		
	}
	
	public void camerPicture() 
	{
		
	}	
}