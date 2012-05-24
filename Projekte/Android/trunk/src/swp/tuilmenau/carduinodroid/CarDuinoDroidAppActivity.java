package swp.tuilmenau.carduinodroid;

import swp.tuilmenau.carduinodroid.controller.*;
import swp.tuilmenau.carduinodroid.model.*;
import swp.tuilmenau.carduinodroid.view.*;
import android.app.Activity;
import android.os.Bundle;

public class CarDuinoDroidAppActivity extends Activity {
	
	Arduino arduino;
	Camera camera;
	Connection connection;
	Controller_Android controller_android;
	GPS gps;
	Network network;
	Record_Sound record_sound;
	Sound sound;
	
	LOG log;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        log = new LOG();
        gps = new GPS(this);
        connection = new Connection(this);
        network = new Network(controller_android);
        
        log.write(gps.getGPS());
        
        if (connection.getMobile()) log.write("Mobiles Internet verf�gbar.");
        else log.write("Mobiles Internet nicht verf�gbar.");
        
        if (connection.getWLAN()) log.write("WLAN verf�gbar.");
        else log.write("WLAN nicht verf�gbar.");
    }
}