package Controller;

import Model.Log;

public class Controller_Computer {
	Log log;
	Sound_Output sound_output;
	Camera_Settings camera_settings;
	Camera_Picture camera_picture;
	Car_Controller car_controller;
	Packagedata packagedata;
	GPS_Map gps_map;
	Soundrecording soundrecording;
	Network network;
	public Controller_Computer(Log LOG) {
		log = LOG;
		camera_picture=new Camera_Picture();
		packagedata=new Packagedata();
		gps_map=new GPS_Map();
		network=new Network(camera_picture,packagedata);
		sound_output=new Sound_Output(network);
		camera_settings=new Camera_Settings(network);
		car_controller=new Car_Controller(network);
		soundrecording=new Soundrecording(network);
		
	}

}
