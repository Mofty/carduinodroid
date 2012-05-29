package swp.tuilmenau.carduinodroid;

import swp.tuilmenau.carduinodroid.controller.Connection;
import android.app.*;
import android.content.*;
import android.os.Bundle;
import android.widget.TextView;

public class CarDuinoDroidAppActivity extends Activity 
{	
	Connection connection;
	TextView IPBox;
	
    /* Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent carduinodroidservice = new Intent(this, CarDuinoDroidService.class);
        startService(carduinodroidservice);
        
        connection = new Connection(this);
        
        IPBox = new TextView(this); 

        IPBox = (TextView) findViewById(R.id.textView2); 
        IPBox.setText(connection.getLocalWLANIP());
        
        // Get a reference to the NotificationManager 
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
        
        //Instantiate the Notification
        int icon = R.drawable.ic_launcher;
        CharSequence tickerText = "CarduinoDroid running";
        long when = System.currentTimeMillis();
        Notification notification = new Notification(icon, tickerText, when);
        
        //Define the notification's message and PendingIntent
        Context context = getApplicationContext();
        CharSequence contentTitle = "CarduinoDroid";
        CharSequence contentText = "Active";
        Intent notificationIntent = new Intent(this, CarDuinoDroidAppActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
        
        //Pass the Notification to the NotificationManager
        final int ACTIVE_ID = 1;
        mNotificationManager.notify(ACTIVE_ID, notification);
          
    }   
    


   

	
}