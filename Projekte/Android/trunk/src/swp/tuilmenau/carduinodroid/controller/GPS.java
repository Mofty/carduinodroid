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
	boolean newerFixAvailable;
	
	public GPS(Context context,/* zu testzwecken. in der finalen version löschen */ final Controller_Android controller_Android) 
	{
		newerFixAvailable = false;
		// ruft eine Instanz des LocationManagers ab
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

		
		// erstellt einen LocationListener der auf Änderung der GPS Position reagiert
		locationListener = new LocationListener() 
		{
		    public void onLocationChanged(Location location) 
		    {
		      // Called when a new location is found by the network location provider.
		    	newerFixAvailable = true;
		    	latitude = location.getLatitude();
		    	longitude = location.getLongitude();
		    	// zu testzwecken. in der finalen version löschen
		    	controller_Android.log.write(controller_Android.gps.getGPS());
		    	controller_Android.log.save();
		    }

		    public void onStatusChanged(String provider, int status, Bundle extras) {}

		    public void onProviderEnabled(String provider) {}

		    public void onProviderDisabled(String provider) {}
				
		};
		
		// registriert den LocationListener zum erhalten von Updates zur Position jede sekunde (1000 ms)
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
	}
	
	
	public String getGPS()
	{	
		newerFixAvailable = false;
		return longitude+";"+latitude;
	}
	
	public boolean newerFixAvailable()
	{
		return newerFixAvailable;
	}

}
