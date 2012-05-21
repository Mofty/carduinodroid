package swp.tuilmenau.carduinodroid.controller;

import swp.tuilmenau.carduinodroid.controller.Arduino;
import swp.tuilmenau.carduinodroid.controller.Camera;
import swp.tuilmenau.carduinodroid.controller.Connection;
import swp.tuilmenau.carduinodroid.controller.GPS;
import swp.tuilmenau.carduinodroid.controller.Network;
import swp.tuilmenau.carduinodroid.controller.RecordSound;





class Controller{
	RecordSound sound;
	GPS gps;
	Arduino arduino;
	Connection connection;
	Camera camera;
	Network network;
	int framerate;
	Controller(){
		sound = new RecordSound();
		gps = new GPS();
		arduino = new Arduino();
		connection = new Connection();
		camera = new Camera();
		network = new Network();
	}
	void SendData(){
		
	}
	void resiveControlData(String data){
		
	}
	void CameraPicture(){
		
	}
}