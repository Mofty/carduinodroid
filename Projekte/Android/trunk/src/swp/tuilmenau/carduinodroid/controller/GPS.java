package swp.tuilmenau.carduinodroid.controller;

import swp.tuilmenau.carduinodroid.model.LOG;

import android.content.Context;
import android.location.*;
import android.os.Bundle;

/**
 * Provides an API for handling GPS data.
 * 
 * @author Paul Thorwirth
 * @version 1.0
 * @see LocationManager
 */
public class GPS 
{
	public LOG log;

	private LocationManager locationManager;
	private LocationListener locationListener;
	private double longitude, latitude;

	public GPS(Context context, LOG nlog) 
	{
		log = nlog;
		longitude = 0;
		latitude = 0;
		// ruft eine Instanz des LocationManagers ab
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

		// erstellt einen LocationListener der auf änderung der GPS Position reagiert
		locationListener = new LocationListener() 
		{
			public void onLocationChanged(Location location) 
			{
				latitude = location.getLatitude();
				longitude = location.getLongitude();
			}

			public void onStatusChanged(String provider, int status, Bundle extras) {}

			public void onProviderEnabled(String provider) 
			{
				log.write(LOG.INFO, "GPS reciever activated");
			}

			public void onProviderDisabled(String provider) 
			{
				log.write(LOG.WARNING, "GPS reciever disabled");
				latitude = 0;
				longitude = 0;
			}

		};	
		// registriert den LocationListener zum erhalten von Updates zur Position jede sekunde (1000 ms)
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
	}

	/**
	 * Collects the GPS-Data and prepares in to by sent by the Controller.
	 *
	 * @return {@link String} containing the GPS-Data.
	 * @see Controller_Android
	 */
	public String getGPS()
	{	
		return longitude+";"+latitude;
	}
}
