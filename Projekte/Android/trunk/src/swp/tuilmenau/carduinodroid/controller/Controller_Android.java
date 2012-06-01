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
	
	boolean mobile;
	boolean mobileConnected;
	boolean wlan;
	boolean wlanConnected;
	
	int framerate;
	
	public Controller_Android(Context context) 
	{
		log = new LOG();
		
		arduino = new Arduino(context,log);
		cam = new Cam(context, log);
		connection = new Connection(context);
		gps = new GPS(context);
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
		 mobile = connection.getMobileAvailable();
		 mobileConnected = connection.getMobile();
		 wlan = connection.getWLANAvailable();
		 wlanConnected = connection.getWLAN();
		
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
		
		if (mobile != connection.getMobileAvailable())
		{
			mobile = connection.getMobileAvailable();
			if (mobile)
				log.write("Mobileinternet is available");
			else 
				log.write("Mobileinternet is not available");
		}
		
		if (mobileConnected != connection.getMobile())
		{
			mobileConnected = connection.getMobile();
			if (mobileConnected)
				log.write("Mobileinternet is connected");
			else 
				log.write("Mobileinternet is not connected");
		}
		
		if (wlan != connection.getWLANAvailable())
		{
			wlan = connection.getWLANAvailable();
			if (wlan)
				log.write("WLAN is available");
			else 
				log.write("WLAN is not available");
		}
		
		if (wlanConnected != connection.getWLAN())
		{
			wlanConnected = connection.getWLAN();
			if (wlanConnected)
				log.write("WLAN is connected");
			else 
				log.write("WLAN is not connected");
		}
		
		return data;
		
	}
	
	public void receiveSteuerdaten(String Steuerdaten) 
	{
		
	}
	
	public void camerPicture() 
	{
		
	}	
}