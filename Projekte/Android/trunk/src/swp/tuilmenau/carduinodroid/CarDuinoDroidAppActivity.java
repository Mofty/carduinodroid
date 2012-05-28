package swp.tuilmenau.carduinodroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.content.ServiceConnection;

public class CarDuinoDroidAppActivity extends Activity 
{	
	private ServiceConnection serviceconnetion;
	
    /* Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent carduinodroidservice = new Intent(this, CarDuinoDroidService.class);
        startService(carduinodroidservice);
              
    }
}