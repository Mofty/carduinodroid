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
        
        if (controller_Android.connection.getMobile()) controller_Android.log.write("Mobiles Internet verf�gbar.");
        else controller_Android.log.write("Mobiles Internet nicht verf�gbar.");
        
        if (controller_Android.connection.getWLAN()) controller_Android.log.write("WLAN verf�gbar.");
        else controller_Android.log.write("WLAN nicht verf�gbar.");
        
        controller_Android.log.save();
    }
}