package swp.tuilmenau.carduinodroid;

import swp.tuilmenau.carduinodroid.controller.*;
import swp.tuilmenau.carduinodroid.model.*;
import swp.tuilmenau.carduinodroid.view.*;
import android.app.Activity;
import android.os.Bundle;

public class CarDuinoDroidAppActivity extends Activity {
	
	LOG log;
	GPS gps;
	Connection connection;
	Network network;
	Controller_Android controller_android;
	Arduino arduino;
	Camera camera;
	Record_Sound record_sound;
	Sound sound;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        log = new LOG();
        gps = new GPS();
        connection = new Connection();
        network = new Network(controller_android);
    }
}