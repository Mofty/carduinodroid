package swp.tuilmenau.carduinodroid.controller;

import swp.tuilmenau.carduinodroid.model.LOG;

import android.app.Activity;
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
	private double longitude, latitude, altitude;

	/**
	 * Gets an Instance of the LocationManager and creates and registers a LocationListener
	 * 
	 * @param activity the current Activity
	 * @param nlog 	The Log
	 * 
	 * @see LocationManager
	 * @see LocationListener
	 */
	public GPS(Activity activity, LOG nlog) 
	{
		log = nlog;
		reset();
		locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
		locationListener = new LocationListener() 
		{
			public void onLocationChanged(Location location) 
			{
				latitude = location.getLatitude();
				longitude = location.getLongitude();
				altitude = location.getAltitude();
			}

			public void onStatusChanged(String provider, int status, Bundle extras)
			{
				int satellites;
				if ((status == LocationProvider.OUT_OF_SERVICE) | (status == LocationProvider.TEMPORARILY_UNAVAILABLE))
				{	
					reset();
				}
				satellites = (Integer) extras.get("satellites");
				if (satellites == 0) reset();
			}

			public void onProviderEnabled(String provider) 
			{
				log.write(LOG.INFO, "GPS reciever activated");
			}

			public void onProviderDisabled(String provider) 
			{
				log.write(LOG.WARNING, "GPS reciever disabled");
				reset();
			}

		};	

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
	}
	
	/**
	 * Sets the Fields to zero.
	 */
	private void reset()
	{
		latitude = 0;
		longitude = 0;
		altitude = 0;
	}

	/**
	 * Collects the GPS-Data and prepares in to by sent by the Controller.
	 *
	 * @return {@link String} containing the GPS-Data.
	 * @see Controller_Android
	 */
	public String getGPS()
	{	
		return longitude + ";" + latitude + ";" + altitude;
	}
}
