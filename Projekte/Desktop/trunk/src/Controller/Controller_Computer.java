package Controller;

import Model.Log;

public class Controller_Computer {
	public Log log;
	public Sound_Output sound_output;
	public Camera_Settings camera_settings;
	public Camera_Picture camera_picture;
	public Car_Controller car_controller;
	public Packagedata packagedata;
	public GPS_Map gps_map;
	public Soundrecording soundrecording;
	public Network network;
	public Controller_Computer(Log LOG) {
		log = LOG;
		camera_picture=new Camera_Picture();
		packagedata=new Packagedata();
		gps_map=new GPS_Map();
		network=new Network("",packagedata,camera_picture);
		sound_output=new Sound_Output(this);
		camera_settings=new Camera_Settings(network);
		car_controller=new Car_Controller(network);
		soundrecording=new Soundrecording(network);
		
	}

}
