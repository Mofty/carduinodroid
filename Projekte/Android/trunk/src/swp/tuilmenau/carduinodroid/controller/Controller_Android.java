package swp.tuilmenau.carduinodroid.controller;

import android.content.Context;
import swp.tuilmenau.carduinodroid.model.*;

public class Controller_Android 
{
	public LOG log;
	
	public Arduino arduino;
	public Camera camera;
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
		camera = new Camera();
		connection = new Connection(context);
		gps = new GPS(context,/* zu testzwecken. in der finalen version l�schen */ log);
		network = new Network(this);
		record_sound = new Record_Sound();
		sound = new Sound(context);	
	}
	
	public void SendData() 
	{
		String data;
		if (connection.getMobileAvailable())data=""+1+";";else data=""+0+";";
		if (connection.getWLANAvailable())data=data+1+";";else data=data+0+";";	
		if (connection.getMobile())data=data+1+";";else data=data+0+";";
		if (connection.getWLAN())data=data+1+";";else data=data+0+";";
		
		data=data+gps.getGPS()+";";
		
		//kammera information anh�ngen + string schicken!
		
	}
	
	public void receiveSteuerdaten(String Steuerdaten) 
	{
		
	}
	
	public void camerPicture() 
	{
		
	}	
}