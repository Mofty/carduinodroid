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
		
        return super.onStartCommand(intent, flags, startId);
    }
	
	@Override
	public IBinder onBind(Intent arg0) 
	{
		return null;
	}	
	
}
