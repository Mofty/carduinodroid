package swp.tuilmenau.carduinodroid.controller;

import android.content.Context;
import android.location.*;
import android.os.Bundle;
import swp.tuilmenau.carduinodroid.model.LOG;

/* in connection klasse einen connection listener erstellen 
 *,der ins log infos und warnungen schreibt, und registrieren
 * ähnlich zu diesem
 * ergo log-teil in controller_Android.packdata() rausnehmen
 */

public class GPS 
{
	LOG log;
	
	LocationManager locationManager;
	LocationListener locationListener;
	double longitude, latitude;
	
	public GPS(Context context, LOG n_log) 
	{
		log = n_log;
		// ruft eine Instanz des LocationManagers ab
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		
		// erstellt einen LocationListener der auf �nderung der GPS Position reagiert
		locationListener = new LocationListener() 
		{
		    public void onLocationChanged(Location location) 
		    {
		    	latitude = location.getLatitude();
		    	longitude = location.getLongitude();
				log.write("INFO ----- GPS - Erster GPS Fix gefunden um" + location.getTime());
				//mathemathische änderungen an den gps koordinaten wie zu auto steht wenn speed = 0 und/oder long und lat gleich ins lpg schreiben gps info
		    }

		    public void onStatusChanged(String provider, int status, Bundle extras) 
			{
				// änderung der gps daten die status den fahrzeugs beeinflussen ins log schreiben
			}

		    public void onProviderEnabled(String provider) 
			{
				log.write("WARNUNG -- GPS - GPS Empfänger wurde deaktiviert");
			}

		    public void onProviderDisabled(String provider) 
			{
				log.write("INFO ----- GPS - GPS Empfänger wurde aktiviert");	
			}
				
		};	
		// registriert den LocationListener zum erhalten von Updates zur Position jede sekunde (1000 ms)
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
	}
	
	
	public String getGPS()
	{	
		return longitude+","+latitude;
	}
	
}
