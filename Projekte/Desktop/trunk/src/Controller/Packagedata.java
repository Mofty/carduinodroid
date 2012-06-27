package Controller;

import java.text.DecimalFormat;

/**
 * Methods receive the data strings from the Android and send them to the GUI.
 * @version 18.06.2012
 * @author Christian Schulze
 */

public class Packagedata {
	
	Controller_Computer controller_computer;
	String [] tokens;
	
	public Packagedata(Controller_Computer ControllerComputer){
		controller_computer = ControllerComputer;
	}
	
	/**
	 * This method gets the data- String and split it into an array.
	 * Then the informationbox will be updated.
	 * @param data is the received String from the Android.
	 */
	
	public void receive_package(String data) 
	{
		tokens = data.split(";",-1);
		for (int i = 0; i < tokens.length; i++) tokens[i] = tokens[i].trim(); //Leerzeichen weg vorn und hinten
		
		if (tokens[0].equals("1"))
			updateInformationbox();
		if (tokens[0].equals("2"))
			updateItemIndex();
	}
	
	/**
	 * This method send the separate information to the GUI. 
	 */
	
	private void updateInformationbox()
	{
		double longi= ((double)Math.round(Double.parseDouble(tokens[5]) * 100000)) / 100000;
		double lat= ((double)Math.round(Double.parseDouble(tokens[6]) * 100000)) / 100000;
		controller_computer.gui_computer.longitude.setText(String.valueOf(longi));
		controller_computer.gui_computer.latitude.setText(String.valueOf(lat));
	
		if (tokens[4].equals("1"))
			controller_computer.gui_computer.connection_type.setText("WLAN");
		if (tokens[3].equals("1"))
			controller_computer.gui_computer.connection_type.setText("Mobile Internet");
			else
				controller_computer.gui_computer.connection_type.setText("No Connection");

		//MobileAvailable tokens[1]; 
		//WLANAvailable tokens[2];
		//Mobile tokens[3];
		//WLAN tokens[4];
	}
	
	private void updateItemIndex()
	{
		int itemslength = tokens.length;
		String[] resolution = new String[itemslength-1];
		
		for (int i = 0; i<itemslength-2; i++)
		{
			resolution[i] = tokens[i+1];
		}
		
		controller_computer.gui_computer.FillResolutionbox(resolution);
	}
	
}
	

