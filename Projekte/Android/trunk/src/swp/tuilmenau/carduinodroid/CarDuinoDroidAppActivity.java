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
        
      //new Thread(new Runnable()
      //{
//          public void run() {
//              Looper.prepare();
//           Network network = new Network(controller_Android);
//              }
      //  }).start();

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
        notification.setLatestEventInfo(getApplicationContext(), "CarduinoDroid", "Active", contentIntent);
        //Pass the Notification to the NotificationManager
        notificationManager.notify(1, notification);
        
    	// zu testzwecken. in der finalen version l�schen
        controller_Android.log.write("App und Service erfolgreich gestartet");

        controller_Android.log.write(controller_Android.gps.getGPS());

        if (controller_Android.connection.getMobileAvailable()){
        	controller_Android.log.write("Mobiles Internet verf�gbar.");
        		if (controller_Android.connection.getMobile())
        			controller_Android.log.write("Mobiles Internet verbunden.");
        		else 
        			controller_Android.log.write("Mobiles Internet nicht verbunden."); 
        }
        else
        	controller_Android.log.write("Mobiles Internet nicht verf�gbar.");

        if (controller_Android.connection.getWLANAvailable()){
        	controller_Android.log.write("WLAN verf�gbar.");
        	if (controller_Android.connection.getWLAN())
        		controller_Android.log.write("WLAN verbunden.");
        	else
        		controller_Android.log.write("WLAN nicht verbunden.");
        }
        else 
        	controller_Android.log.write("WLAN nicht verf�gbar.");

        controller_Android.log.write(controller_Android.connection.getLocalWLANIP());

        controller_Android.sound.horn();

        controller_Android.cam.enableFlash();
        try {
        		Thread.sleep(2000);
        	} catch (InterruptedException e) { }
        controller_Android.cam.disableFlash();
    }
    
    @Override
    public void onPause()
    {
    	wakelock.release();
    	super.onPause();
    }
    	
}



