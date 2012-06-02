package swp.tuilmenau.carduinodroid.controller;

import android.app.Activity;
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
	
	public Controller_Android(Activity activity) 
	{
		log = new LOG();
		
		arduino = new Arduino(activity, log);
		cam = new Cam(activity, log);
		connection = new Connection(activity, log);
		gps = new GPS(activity, log);
		record_sound = new Record_Sound(log);
		sound = new Sound(activity);	
				
		final Controller_Android temp = this; // <- app schmiert ab wenn log - teil
		new Thread(new Runnable()			  //    unten einkommentiert wird
        {									  //    liegt vermutlich an dem final
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
			
		return data;
		
	}
	
	public void receiveSteuerdaten(String Steuerdaten) 
	{
		
	}
	
	public void camerPicture() 
	{
		
	}	
}