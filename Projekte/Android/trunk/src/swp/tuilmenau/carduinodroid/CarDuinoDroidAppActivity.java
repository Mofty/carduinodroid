package swp.tuilmenau.carduinodroid;

import swp.tuilmenau.carduinodroid.controller.Controller_Android;
import android.app.Activity;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.widget.TextView;

public class CarDuinoDroidAppActivity extends Activity 
{	
    /* Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	TextView IPBox; 
    	
    	Controller_Android controller_Android;
    	
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent carduinodroidservice = new Intent(this, CarDuinoDroidService.class);
        startService(carduinodroidservice);
        
        controller_Android = new Controller_Android(this);
        
        IPBox = new TextView(this); 

        IPBox =(TextView)findViewById(R.id.textView2); 
        IPBox.setText(controller_Android.connection.getLocalWLANIP());
              
    }
}