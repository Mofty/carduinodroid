package swp.tuilmenau.carduinodroid;

import swp.tuilmenau.carduinodroid.controller.Cam;
import swp.tuilmenau.carduinodroid.controller.Connection;
import swp.tuilmenau.carduinodroid.model.LOG;
import android.app.*;
import android.content.*;
import android.os.Bundle;
import android.widget.TextView;


public class CarDuinoDroidAppActivity extends Activity 
{	
	Connection connection;
	Cam cam;
	LOG log;
	
	TextView IPBox;
	Intent carduinodroidservice;
	NotificationManager notificationManager;
	Notification notification;
	Intent notificationIntent;
	PendingIntent contentIntent;
	
	
    /* Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	// call onCreate of superclass
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // initialize fields
        connection = new Connection(this);
        log = new LOG();
        
        
        IPBox = new TextView(this); 
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notification = new Notification(R.drawable.ic_launcher, "CarduinoDroid running", System.currentTimeMillis());
        notificationIntent = new Intent(this, CarDuinoDroidAppActivity.class);
        contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        
        
        // write local ip into textbox
        IPBox = (TextView) findViewById(R.id.textView2); 
        IPBox.setText(connection.getLocalWLANIP());
        
        //define flags for persistent notification
        //cause Notification.Builder isn't implemented in API lvl 10
        notification.flags = notification.flags | Notification.FLAG_NO_CLEAR;
        //Define the notification's message and PendingIntent
        notification.setLatestEventInfo(getApplicationContext(), "CarduinoDroid", "Active", contentIntent);
        //Pass the Notification to the NotificationManager
        notificationManager.notify(1, notification); 
    }   
    
    @Override
    public void onStart()
    {
    	super.onStart();
    	carduinodroidservice = new Intent(this, CarDuinoDroidService.class);
        startService(carduinodroidservice);
        cam = new Cam(this, log);
        cam.enableFlash();
    }
    


   

	
}