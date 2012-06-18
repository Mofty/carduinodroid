package swp.tuilmenau.carduinodroid.controller;

import swp.tuilmenau.carduinodroid.model.LOG;

import swp.tuilmenau.carduinodroid.R;

import android.app.Activity;
import android.content.*;
import android.net.*;
import android.net.wifi.*;
import android.text.format.Formatter;
import android.widget.TextView;

/**
 * Provides an API for handling and changing connection data.
 * 
 * @author Paul Thorwirth
 * @version 1.0
 * @see ConnectionManager
 */
public class Connection 
{
	/**
	 * Provides the callback for logging and reacting to changes in the connection data.
	 * 
	 * @see BroadcastReceiver
	 */
	public class ConnectionLogger extends BroadcastReceiver
	{
		private TextView ipBox;
		private Activity activity;

		public ConnectionLogger(Activity activity)
		{
			super();
			this.activity = activity;	
			ipBox = new TextView(activity);
		}

		@Override
		public void onReceive(Context context, Intent intent) 
		{
			if (log != null)
			{	
				if (getMobileAvailable()) log.write(LOG.INFO, "Mobile Internet is available");
				else log.write(LOG.WARNING, "Mobile Internet is not available");

				if (getMobile()) log.write(LOG.INFO, "Mobile Internet is connected");
				else log.write(LOG.WARNING, "Mobile Internet is not connected");

				if (getWLANAvailable()) log.write(LOG.INFO, "WLAN is available");
				else log.write(LOG.WARNING, "WLAN is not available");

				if (getWLAN())
				{
					log.write(LOG.INFO, "WLAN is connected");
					ipBox = (TextView) activity.findViewById(R.id.textView2); 
					ipBox.setText(getLocalWLANIP());
				}
				else log.write(LOG.WARNING, "WLAN is not connected");
			}
		}
	}

	private LOG log = null;
	private ConnectivityManager connectivityManager;
	private WifiManager wifiManager;
	private NetworkInfo mobileInfo;
	private NetworkInfo WLANInfo;
	private ConnectionLogger connectionLogger;
	private IntentFilter connectivityFilter;

	public Connection (Activity activity, LOG log)
	{
		this.log = log;
		connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		wifiManager = (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);	
		//create and register the connectionLogger
		connectionLogger = new ConnectionLogger(activity);
		connectivityFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
		activity.registerReceiver(connectionLogger, connectivityFilter);
	}

	/**
	 * Returns the status on the mobile Internet connection.
	 * 
	 * @return true if available false if else.
	 */
	public boolean getMobileAvailable()
	{
		mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		return mobileInfo.isAvailable();
	}

	/**
	 * Returns true if there is a WLAN available.
	 * 
	 * @return true if available false if else.
	 */
	public boolean getWLANAvailable()
	{
		WLANInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		return WLANInfo.isAvailable();
	}
	/**
	 * Returns true if there is a mobile Internet is connected.
	 * 
	 * @return true if connected false if else.
	 */

	public boolean getMobile()
	{
		mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		return mobileInfo.isConnected();
	}

	/**
	 * Returns true if there is a WLAN is connected.
	 * 
	 * @return true if connected false if else.
	 */	public boolean getWLAN()
	 {
		 WLANInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		 return WLANInfo.isConnected();
	 }

	 /**
	  * Returns a String representing the current local IP of the Phone.
	  * 
	  * @return {@link String} representing the current local WLAN IP of the Phone.
	  */
	 public String getLocalWLANIP()
	 {
		 int ipAddress;
		 WifiInfo wifiInfo;

		 if (getWLANAvailable())
		 {	
			 wifiInfo = wifiManager.getConnectionInfo();
			 ipAddress = wifiInfo.getIpAddress(); 
			 return Formatter.formatIpAddress(ipAddress);
		 } else return null;		
	 } 	
}
