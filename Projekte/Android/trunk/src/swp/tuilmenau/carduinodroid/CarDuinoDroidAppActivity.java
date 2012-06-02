package swp.tuilmenau.carduinodroid;

import swp.tuilmenau.carduinodroid.controller.*;
import android.app.*;
import android.content.*;
import android.os.Bundle;
import android.os.PowerManager;
import android.widget.TextView;


public class CarDuinoDroidAppActivity extends Activity 
{	
	Controller_Android controller_Android;
	
	TextView IPBox;
	Intent carduinodroidservice;
	NotificationManager notificationManager;
	Notification notification;
	Intent notificationIntent;
	PendingIntent contentIntent;
	PowerManager powerManager;
	PowerManager.WakeLock wakelock;
	
    /* Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	// call onCreate of superclass
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // initialize controller field hosting all other sub classes
        controller_Android = new Controller_Android(this);
        // initialize fields for wake_lock
        powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakelock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "CarduinoDroid_Full_Wake_Lock");
        // initialize field for the IP BOX
        IPBox = new TextView(this); 
        
        // initialize fields for status bar notification
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notification = new Notification(R.drawable.ic_launcher, "CarduinoDroid running", System.currentTimeMillis());
        notificationIntent = new Intent(this, CarDuinoDroidAppActivity.class);
        contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0); 
    }   
    
    @Override
    public void onResume()
    {
    	super.onResume();
    	
    	// start full wake_lock
    	wakelock.acquire();
    	
    	// write local ip into textbox
        IPBox = (TextView) findViewById(R.id.textView2); 
        IPBox.setText(controller_Android.connection.getLocalWLANIP());
        
        //define flags for persistent notification by hand
        //cause Notification.Builder isn't implemented in API lvl 10
        notification.flags = notification.flags | Notification.FLAG_NO_CLEAR;
        //Define the notification's message and PendingIntent
        notification.setLatestEventInfo(getApplicationContext(), "CarduinoDroid", "Pressing Home-Button will close the App !", contentIntent);
        //Pass the Notification to the NotificationManager
        notificationManager.notify(1337, notification);
    }
    
    @Override
    public void onPause()
    {
    	wakelock.release();
    	controller_Android.cam.disableCamera();
    	finish();
    	super.onPause();
    }
    
    @Override
    public void onDestroy()
    {
    	controller_Android.log.save();
    	notificationManager.cancel(1337);
    	super.onDestroy();
    }
}



