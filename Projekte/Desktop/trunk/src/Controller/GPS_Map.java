package Controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
/**
 * 
 * creates a link with the coordinates which can be opened in the browser
 * @author Mofty
 * @version1.0
 *
 */
public class GPS_Map
{
	Controller_Computer controller_computer;
	String link;

	public GPS_Map(Controller_Computer ControllerComputer) 
	{
		/**
		 * @see controller_computer
		 */
		controller_computer = ControllerComputer;
	}

	public void open_map(String longitude, String latitude)
	{
		generatelink(longitude, latitude);
		openLink();
	}

	private void generatelink(String longitude, String latitude)
	{
		/**
		 * creates the link
		 */
		 //creates a link with the longitudes and latitudes coordinates of the current position
		link = "http://maps.google.com/maps?q=loc:" + longitude + "," + latitude;
	}

	private void openLink()
	{
		/**
		 * returnes "Google Maps opening in browser"
		 * 
		 *  @return {@link String} containing the link to the GPS-Data.
		 *  
		 */
		try {
			Desktop.getDesktop().browse(new URI(link));
			controller_computer.log.writelogfile("Google Maps im Standardbrowser aufgerufen");
		} 
		catch (IOException e) {e.printStackTrace();} 
		catch (URISyntaxException e) {e.printStackTrace();}
	} 
}