package swp.tuilmenau.carduinodroid;

import swp.tuilmenau.carduinodroid.controller.Controller_Android;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class CarDuinoDroidService extends Service 
{
	Controller_Android controller_Android;
	
	public CarDuinoDroidService()
	{
		super();
	}
	
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Diese Methode wird beim Starten aufgerufen!
		
		controller_Android = new Controller_Android(this);
		
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
        
        // methode kaputt siehe connection klasse
        //controller_Android.log.write(controller_Android.connection.getIP());
 
		
        return super.onStartCommand(intent, flags, startId);
    }
	
	@Override
	public IBinder onBind(Intent arg0) 
	{
		return null;
	}	
	
}
