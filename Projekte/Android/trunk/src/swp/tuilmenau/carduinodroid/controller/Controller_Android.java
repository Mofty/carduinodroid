package swp.tuilmenau.carduinodroid.controller;

import swp.tuilmenau.carduinodroid.model.*;

public class Controller_Android {
	
	Arduino arduino;
	Connection connection;
	GPS gps;
	Camera camera;
	Network network;
	Record_Sound record_sound;
	LOG log;
	Sound sound;
	Controller_Android controller_android;
	int framerate;
	
	public Controller_Android() {
		
		arduino = new Arduino();
		connection = new Connection();
		gps = new GPS();
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