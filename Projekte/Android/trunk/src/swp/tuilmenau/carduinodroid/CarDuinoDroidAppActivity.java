package swp.tuilmenau.carduinodroid;

import swp.tuilmenau.carduinodroid.controller.*;
import swp.tuilmenau.carduinodroid.model.*;
import swp.tuilmenau.carduinodroid.view.*;
import android.app.Activity;
import android.os.Bundle;

public class CarDuinoDroidAppActivity extends Activity {
	
	Controller_Android controller_Android;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        controller_Android = new Controller_Android(this);
        
        // nur zu testzwecken
        controller_Android.log.write("App erfolgreich gestartet");
        
        controller_Android.log.write(controller_Android.gps.getGPS());
        
        if (controller_Android.connection.getMobileAvailable()){
        	controller_Android.log.write("Mobiles Internet verfügbar.");
        		if (controller_Android.connection.getMobile())
        			controller_Android.log.write("Mobiles Internet verbunden.");
        		else 
        			controller_Android.log.write("Mobiles Internet nicht verbunden."); 
        }
        else
        	controller_Android.log.write("Mobiles Internet nicht verfügbar.");
        
        if (controller_Android.connection.getWLANAvailable()){
        	controller_Android.log.write("WLAN verfügbar.");
        	if (controller_Android.connection.getWLAN())
        		controller_Android.log.write("WLAN verbunden.");
        	else
        		controller_Android.log.write("WLAN nicht verbunden.");
        }
        else 
        	controller_Android.log.write("WLAN nicht verfügbar.");
        
        controller_Android.log.save();
    }
}