package swp.tuilmenau.carduinodroid.controller;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class GPS {
	LocationManager locationManager;
	
	LocationListener locationListener;
	double longitude, latitude;
	
	public GPS() 
	{
		// ruft eine Instanz des LocationManagers ab
		// warum genau er da nen fehler haut weiss ich nicht..
		// is genauso von developer.android.com kopiert ...
		locationManager = (LocationManager) Context.getSystemService(Context.LOCATION_SERVICE);
		
		// erstellt einen LocationListener der auf Änderung der GPS Position reagiert
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

				// TODO Auto-generated method stub
				
		};
		// registriert den LocationListener zum erhalten von Updates zur Position jede sekunde (1000 ms)
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
	}
	
	public String getGPS()
	{	
		return longitude+";"+latitude;
	}


}
