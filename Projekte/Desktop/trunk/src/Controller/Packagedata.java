package Controller;

public class Packagedata {
	
	Controller_Computer controller_computer;
	int[] valuesInt;
	double[] valuesDouble;
	String [] tokens;
	
	public Packagedata(Controller_Computer ControllerComputer){
		controller_computer = ControllerComputer;
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
		controller_computer.gui_computer.latitude.setText("test");
		//MobileAvailable tokens[0]; 
		//WLANAvailable tokens[1];
		//Mobile tokens[2];
		//WLAN tokens[3];
		//GPS longitude tokens[4];
		//GPS latitude tokens[5];
	}
}
	

