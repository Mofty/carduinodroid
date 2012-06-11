package swp.tuilmenau.carduinodroid;

import swp.tuilmenau.carduinodroid.controller.*;
import android.app.*;
import android.content.*;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.*;

public class CarDuinoDroidAppActivity extends Activity 
{	
	private Controller_Android controller_Android;
	
	private NotificationManager notificationManager;
	private Notification notification;
	private Intent notificationIntent;
	private PendingIntent contentIntent;
	private PowerManager powerManager;
	private PowerManager.WakeLock wakelock;

	private RadioGroup logLevelSwitch;
	private LogLevelSwitcherListener logLevelSwitcherListener;
	
	class LogLevelSwitcherListener implements RadioGroup.OnCheckedChangeListener
	{

		public void onCheckedChanged(RadioGroup group, int checkedId) 
		{
			if (checkedId == R.id.radio0) controller_Android.log.setLevel(controller_Android.log.LOG_ALL);
			if (checkedId == R.id.radio1) controller_Android.log.setLevel(controller_Android.log.LOG_WARNINGS_ONLY);
		}
		
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	// call onCreate of superclass
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // prevent the app from switching to landscape-mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // initialize controller field hosting all other sub classes
        controller_Android = new Controller_Android(this);
        // initialize fields for wake_lock
        powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakelock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "CarduinoDroid_Full_Wake_Lock");
        // initialize fields for status bar notification
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notification = new Notification(R.drawable.ic_launcher, "CarduinoDroid running", System.currentTimeMillis());
        notificationIntent = new Intent(this, CarDuinoDroidAppActivity.class);
        contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        // Link LogSwitcher
        // Close-Button is linked in main.xml
        logLevelSwitch = (RadioGroup) findViewById(R.id.radioGroup1);
        logLevelSwitch.setOnCheckedChangeListener(logLevelSwitcherListener); 
    }   
    
    public void close(View view)
    //called when the close button is pressed;
    {
    	finish();
    }
    
    @Override
    public void onResume()
    {
    	super.onResume();    	
    	// start full wake_lock
    	wakelock.acquire();
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