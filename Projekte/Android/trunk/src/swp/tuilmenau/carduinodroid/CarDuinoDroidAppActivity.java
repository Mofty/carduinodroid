package swp.tuilmenau.carduinodroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class CarDuinoDroidAppActivity extends Activity 
{	
    /* Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent carduinodroidservice = new Intent(this, CarDuinoDroidService.class);
        startService(carduinodroidservice);
              
    }
}