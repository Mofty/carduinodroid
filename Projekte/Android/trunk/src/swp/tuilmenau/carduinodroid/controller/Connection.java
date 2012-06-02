package swp.tuilmenau.carduinodroid.controller;

/* momentan nicht lauffähig
 * irgendwas ist mit dem connectionlogger kaputt...
 */

import swp.tuilmenau.carduinodroid.model.LOG;
import android.content.*;
import android.net.*;
import android.net.wifi.*;
import android.text.format.Formatter;

public class Connection 
{
	LOG log = null;
	
	public class ConnectionLogger extends BroadcastReceiver
	{
		public ConnectionLogger()
		{
			super();
		}
		
		@Override
        public void onReceive(Context context, Intent intent) 
        {
			if (log != null)
			{	
				if (getMobileAvailable())
					log.write("Mobile Internet is available");
				else 
					log.write("Mobile Internet is not available");

				if (getMobile())
					log.write("Mobile Internet is connected");
				else 
					log.write("Mobile Internet is not connected");

				if (getWLANAvailable())
					log.write("WLAN is available");
				else 
					log.write("WLAN is not available");

				if (getWLAN())
					log.write("WLAN is connected");
				else 
					log.write("WLAN is not connected");
			}
        }
	}
	
	ConnectivityManager connectivityManager;
	WifiManager wifiManager;
 	NetworkInfo mobileInfo;
 	NetworkInfo WLANInfo;
 	WifiInfo wifiInfo;
 	ConnectionLogger connectionLogger;
 	IntentFilter connectivityFilter;
	
	public Connection (Context context, LOG n_log)
	{
		connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);	
		log = n_log;
		//create and register the connectionLogger
		connectionLogger = new ConnectionLogger();
		connectivityFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
		context.registerReceiver(connectionLogger, connectivityFilter);
	}
	
	public boolean getMobileAvailable()
	{
		mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		return mobileInfo.isAvailable();
	}
	
	public boolean getWLANAvailable()
	{
		WLANInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		return WLANInfo.isAvailable();
	}
	
	public boolean getMobile()
	{
		mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		return mobileInfo.isConnected();
	}
	
	public boolean getWLAN()
	{
		WLANInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		return WLANInfo.isConnected();
	}
	
	public String getLocalWLANIP()
	{
		if (getWLANAvailable())
		{	
			wifiInfo = wifiManager.getConnectionInfo();
			int ipAddress = wifiInfo.getIpAddress(); 
			return Formatter.formatIpAddress(ipAddress);
		} else return null;
			
	} 	
}
