package swp.tuilmenau.carduinodroid;

import swp.tuilmenau.carduinodroid.controller.GPS;
import swp.tuilmenau.carduinodroid.model.LOG;
import android.app.Activity;
import android.os.Bundle;

public class CarDuinoDroidAppActivity extends Activity {
	
	LOG log;
	GPS gps;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        log = new LOG();
        gps = new GPS();
    }
}