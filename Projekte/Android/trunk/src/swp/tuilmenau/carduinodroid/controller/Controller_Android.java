package swp.tuilmenau.carduinodroid.controller;

import android.content.Context;
import swp.tuilmenau.carduinodroid.model.*;
import swp.tuilmenau.carduinodroid.view.*;

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
		
		arduino = new Arduino(context,this);
		camera = new Camera();
		connection = new Connection(context);
		gps = new GPS(context,/* zu testzwecken. in der finalen version l�schen */ this);
		network = new Network(this);
		record_sound = new Record_Sound();
		sound = new Sound(context);	
	}
	
	public void SendData() 
	{
		
	}
	
	public void receiveSteuerdaten(String Steuerdaten) 
	{
		
	}
	
	public void camerPicture() 
	{
		
	}	
}