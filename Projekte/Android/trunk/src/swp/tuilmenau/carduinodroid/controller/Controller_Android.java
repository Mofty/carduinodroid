package swp.tuilmenau.carduinodroid.controller;

import swp.tuilmenau.carduinodroid.model.*;

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
	
	public Controller_Android() {
		
		arduino = new Arduino();
		//connection = new Connection());
		// gps = new GPS();
		camera = new Camera();
		network = new Network(controller_android);
		record_sound = new Record_Sound();
		log = new LOG();
		sound = new Sound();
	}
	
	public void SendData() {
	}
	public void receiveSteuerdaten(String Steuerdaten) {
	}
	public void kamerabild() {
	}	
}