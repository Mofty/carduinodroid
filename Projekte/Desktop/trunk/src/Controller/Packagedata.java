package Controller;

import View.GUI_Computer;

public class Packagedata {
	
	GUI_Computer gui_computer;
	int[] valuesInt;
	double[] valuesDouble;
	String [] tokens;
	
	public Packagedata(GUI_Computer gui_Computer){
		gui_computer = gui_Computer;
	}
	
	public void receive_package(String data) 
	{
		tokens = data.split(";",-1);
		for (int i = 0; i < 6; i++) tokens[i] = tokens[i].trim(); //Leerzeichen weg vorn und hinten
		updateInformationbox();
	}
	
	public void updateInformationbox()
	{
		tokens[5]="50.687222";
		tokens[4]="10.914167";
		//gui_computer.connection_type.setText();
		gui_computer.latitude.setText(tokens[5]);
		gui_computer.longitude.setText(tokens[4]);
		//MobileAvailable tokens[0]; 
		//WLANAvailable tokens[1];
		//Mobile tokens[2];
		//WLAN tokens[3];
		//GPS longitude tokens[4];
		//GPS latitude tokens[5];
	}
}
	

