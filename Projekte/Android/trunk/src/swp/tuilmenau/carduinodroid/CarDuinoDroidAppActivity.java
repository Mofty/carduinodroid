package swp.tuilmenau.carduinodroid;

import swp.tuilmenau.carduinodroid.controller.*;
import swp.tuilmenau.carduinodroid.model.LOG;
import android.app.*;
import android.content.*;
import android.content.pm.ActivityInfo;
import android.os.*;
import android.view.View;
import android.widget.*;

/**
 * Defines the view presented to the user when opening the App.
 * 
 * @author Paul Thorwirth
 * @version 1.0
 * @see Activity
 */
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
	private RadioGroup.OnCheckedChangeListener logLevelSwitcherListener;
	
	/**
	 * Called when the activity is first created.
	 * 
	 * @param savedInstanceState holds flags and parameters that were saved when the Android System colses the App.
	 * @see Activity#onCreate()
	 */
	public void onCreate(Bundle savedInstanceState) 
	{
		// call onCreate of superclass
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// prevent the application from switching to landscape-mode
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
		logLevelSwitcherListener = new RadioGroup.OnCheckedChangeListener()
		{
			public void onCheckedChanged(RadioGroup group, int checkedId) 
			{
				if (checkedId == R.id.radio0) controller_Android.log.setLevel(LOG.LOG_ALL);
				if (checkedId == R.id.radio1) controller_Android.log.setLevel(LOG.LOG_WARNINGS_ONLY);
			}
		};
		logLevelSwitch.setOnCheckedChangeListener(logLevelSwitcherListener); 
	}   

	/**
	 * Called when the close button is pressed.
	 * 
	 * @param view The {@link View} of the button that has been pressed.
	 * @see Activity#finish()
	 */
	public void close(View view)
	{
		cleanUp();
		finish();
		System.exit(0);
	}

	/**
	 * Called when the activity comes into the foreground.
	 * 
	 * @see Activity#onResume()
	 */
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
	
	/**
	 * Called when the App is minimized.
	 * 
	 * @see Activity#onPause()
	 */
	public void onPause()
	{
		cleanUp();
		finish();
		System.exit(0);
	}
	
	/**
	 * Called when the App is minimized or the Close-Button is pressed.
	 */
	private void cleanUp()
	{
		notificationManager.cancel(1337);
		controller_Android.log.save();
		wakelock.release();
		controller_Android.cam.disableCamera();
		unregisterReceiver(controller_Android.connection.connectionLogger);
		unregisterReceiver(controller_Android.arduino.mUsbReceiver);
		controller_Android.arduino.closeAccessory();
		controller_Android.sound.resetVolume();
	}
}