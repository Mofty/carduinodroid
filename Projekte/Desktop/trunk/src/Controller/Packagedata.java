package Controller;

/**
 * Method Packagedata receive the data strings from the Android.. 
 * @since version 1.00
 * @author Christian Schulze
 */

public class Packagedata {
	
	Controller_Computer controller_computer;
	String [] tokens;
	
	public Packagedata(Controller_Computer ControllerComputer){
		controller_computer = ControllerComputer;
	}
	
	/**
	 * This method gets the String and split it into an array.
	 
	 * @param data is the received String from the Android.
	 */
	
	public void receive_package(String data) 
	{
		tokens = data.split(";",-1);
		for (int i = 0; i < tokens.length; i++) tokens[i] = tokens[i].trim(); //Leerzeichen weg vorn und hinten
		updateInformationbox();
	}
	
	/**
	 * This method send the separate information to the GUI. 
	 */
	
	public void updateInformationbox()
	{
		controller_computer.gui_computer.longitude.setText(tokens[4]);
		controller_computer.gui_computer.latitude.setText(tokens[5]);
		
		if (tokens[3] == "1")
			controller_computer.gui_computer.connection_type.setText("WLAN");
		else
			if (tokens[2] == "1")
				controller_computer.gui_computer.connection_type.setText("Mobile Internet");
			else
				controller_computer.gui_computer.connection_type.setText("No Connection");
		
		//MobileAvailable tokens[0]; 
		//WLANAvailable tokens[1];
		//Mobile tokens[2];
		//WLAN tokens[3];
	}
}
	

