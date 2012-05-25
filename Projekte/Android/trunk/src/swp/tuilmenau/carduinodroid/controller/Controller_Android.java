package swp.tuilmenau.carduinodroid.controller;

import android.content.Context;
import swp.tuilmenau.carduinodroid.model.*;
import swp.tuilmenau.carduinodroid.view.*;

public class Controller_Android {
	
	Arduino arduino;
	Camera camera;
	Connection connection;
	Controller_Android controller_android;
	GPS gps;
	Network network;
	Record_Sound record_sound;
	Sound sound;
	
	LOG log;
	
	int framerate;
	
	public Controller_Android(Context context) {
		
		arduino = new Arduino();
		camera = new Camera();
		connection = new Connection(context);
		gps = new GPS(context);
		network = new Network(this);
		record_sound = new Record_Sound();
		sound = new Sound();
		
		log = new LOG();
	}
	
	public void SendData() {
	}
	public void receiveSteuerdaten(String Steuerdaten) {
	}
	public void kamerabild() {
	}	
}