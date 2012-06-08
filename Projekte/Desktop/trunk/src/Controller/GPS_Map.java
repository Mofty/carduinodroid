package Controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GPS_Map
{
    String link;
    
    public void open_map(double longitude, double latitude)
    {
    	generatelink(longitude, latitude);
    	openLink();
    }
    
    private void generatelink(double longitude, double latitude)
    {
    	link = "http://maps.google.com/maps?q=loc:" + longitude + "," + latitude;
    }
    
    private void openLink()
    {
        try {
            	Desktop.getDesktop().browse(new URI(link));
            } 
        catch (IOException e) {e.printStackTrace();} 
        catch (URISyntaxException e) {e.printStackTrace();}
    } 
}