package Controller;

import Model.Log;
import View.GUI_Computer;

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
	public GUI_Computer gui_computer;
	
	public Controller_Computer(Log LOG, GUI_Computer GUI_computer) {
		gui_computer = GUI_computer;
		log = LOG;
		camera_picture=new Camera_Picture();
		packagedata=new Packagedata(this);
		gps_map=new GPS_Map(this);
		network=new Network("",packagedata,camera_picture);
		sound_output=new Sound_Output(this);
		camera_settings=new Camera_Settings(this);
		car_controller=new Car_Controller(this);
		soundrecording=new Soundrecording(this);
		
	}

}
