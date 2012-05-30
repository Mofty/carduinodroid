package swp.tuilmenau.carduinodroid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import swp.tuilmenau.carduinodroid.controller.Controller_Android;


public class CarDuinoDroidService extends Service 
{
	Controller_Android controller_Android;
	
	
	public CarDuinoDroidService()
	{
		super();
	}
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		controller_Android = new Controller_Android(this);
		
		
		
		// zu testzwecken. in der finalen version löschen
		controller_Android.log.write("App und Service erfolgreich gestartet");
        
        controller_Android.log.write(controller_Android.gps.getGPS());
        
        if (controller_Android.connection.getMobileAvailable()){
        	controller_Android.log.write("Mobiles Internet verfügbar.");
        		if (controller_Android.connection.getMobile())
        			controller_Android.log.write("Mobiles Internet verbunden.");
        		else 
        			controller_Android.log.write("Mobiles Internet nicht verbunden."); 
        }
        else
        	controller_Android.log.write("Mobiles Internet nicht verfügbar.");
        
        if (controller_Android.connection.getWLANAvailable()){
        	controller_Android.log.write("WLAN verfügbar.");
        	if (controller_Android.connection.getWLAN())
        		controller_Android.log.write("WLAN verbunden.");
        	else
        		controller_Android.log.write("WLAN nicht verbunden.");
        }
        else 
        	controller_Android.log.write("WLAN nicht verfügbar.");
        
        controller_Android.log.write(controller_Android.connection.getLocalWLANIP());
        
        controller_Android.sound.horn();
	}

	@Override
	public IBinder onBind(Intent arg0) 
	{
		return null;
	}
		
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) 
	{    
        return START_STICKY;
    }
	
	@Override
	public void onDestroy()
	{
		controller_Android.log.save();
		super.onDestroy();
	}
		
}
