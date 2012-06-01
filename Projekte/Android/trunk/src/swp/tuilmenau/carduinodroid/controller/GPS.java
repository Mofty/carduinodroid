package swp.tuilmenau.carduinodroid.controller;

import android.content.Context;
import android.location.*;
import android.os.Bundle;
import swp.tuilmenau.carduinodroid.model.LOG;

public class GPS {
	LocationManager locationManager;
	
	LocationListener locationListener;
	double longitude, latitude;
	
	public GPS(Context context) 
	{
		// ruft eine Instanz des LocationManagers ab
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

		
		// erstellt einen LocationListener der auf �nderung der GPS Position reagiert
		locationListener = new LocationListener() 
		{
		    public void onLocationChanged(Location location) 
		    {
		      // Called when a new location is found by the network location provider.
		    	latitude = location.getLatitude();
		    	longitude = location.getLongitude();
		    }

		    public void onStatusChanged(String provider, int status, Bundle extras) {}

		    public void onProviderEnabled(String provider) {}

		    public void onProviderDisabled(String provider) {}
				
		};
		
		// registriert den LocationListener zum erhalten von Updates zur Position jede sekunde (1000 ms)
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
	}
	
	
	public String getGPS()
	{	
		return longitude+","+latitude;
	}
	
}
