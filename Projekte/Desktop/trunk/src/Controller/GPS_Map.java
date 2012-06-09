package Controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GPS_Map
{
	Controller_Computer controller_computer;
	String link;
    
    public GPS_Map(Controller_Computer ControllerComputer) {
		controller_computer = ControllerComputer;
	}
    
    public void open_map(String longitude, String latitude)
    {
    	generatelink(longitude, latitude);
    	openLink();
    }
    
    private void generatelink(String longitude, String latitude)
    {
    	link = "http://maps.google.com/maps?q=loc:" + longitude + "," + latitude;
    }
    
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