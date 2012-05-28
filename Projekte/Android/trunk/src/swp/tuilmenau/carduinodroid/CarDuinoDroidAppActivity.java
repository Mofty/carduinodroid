package swp.tuilmenau.carduinodroid;

import swp.tuilmenau.carduinodroid.controller.Controller_Android;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
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
   
    
    //String ns = Context.NOTIFICATION_SERVICE;
	//NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
	
	//int icon = R.drawable.carduinodroid;
	//CharSequence tickerText = "active";
	//long when = System.currentTimeMillis();

	//Notification notification = new Notification(icon, tickerText, when);
	//Context context = getApplicationContext();
	//CharSequence contentTitle = "My notification";
	//CharSequence contentText = "Hello World!";
	//Intent notificationIntent = new Intent(this,CarDuinoDroidService.class);
	//PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
    //Intent notificationIntent = new Intent(this, CarDuinoDroidService.class);
    //PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
    //notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
    //private static final int HELLO_ID = 1;

    //mNotificationManager.notify(HELLO_ID, notification);

   

	
}