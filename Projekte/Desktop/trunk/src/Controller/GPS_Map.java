package Controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/** 
 * methods to creates a link with the coordinates which can be opened in the browser
 * @author Sven Haueisen
 * @version 18.06.2012
 */

public class GPS_Map
{
	Controller_Computer controller_computer;
	String link;

	/**
	 * Needs the Controller_Computer to get access to the log
	 */
	
	public GPS_Map(Controller_Computer ControllerComputer) 
	{
		
		controller_computer = ControllerComputer;
	}
	
	/**
	 * the method opne_map opens the link with the longitudes and latitudes coordinates
	 */
	
	public void open_map(String longitude, String latitude)
	{
		generatelink(longitude, latitude);
		openLink();
	}
	
	/**
	 * this is a method which generates the link
	 */

	private void generatelink(String longitude, String latitude)
	{
		 //creates a google link with the longitudes and latitudes coordinates of the current position
		link = "http://maps.google.com/maps?q=loc:" + longitude + "," + latitude;
	}
	
	/**
	 * returnes "Google Maps opening in browser"
	 * 
	 *  @return {@link String} containing the link to the GPS-Data.
	 *  
	 */
	
	private void openLink()
	{
	
		try {
			Desktop.getDesktop().browse(new URI(link));
			controller_computer.log.writelogfile("Google Maps im Standardbrowser aufgerufen");
		} 
		catch (IOException e) {e.printStackTrace();} 
		catch (URISyntaxException e) {e.printStackTrace();}
	} 
}