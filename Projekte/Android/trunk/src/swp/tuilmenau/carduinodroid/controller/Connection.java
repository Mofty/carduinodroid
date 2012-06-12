package swp.tuilmenau.carduinodroid.controller;

import swp.tuilmenau.carduinodroid.R;
import swp.tuilmenau.carduinodroid.model.LOG;
import android.app.Activity;
import android.content.*;
import android.net.*;
import android.net.wifi.*;
import android.text.format.Formatter;
import android.widget.TextView;

public class Connection 
{
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
	
	public Connection (Activity activity, LOG n_log)
	{
		log = n_log;
		connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		wifiManager = (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);	
		//create and register the connectionLogger
		connectionLogger = new ConnectionLogger(activity);
		connectivityFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
		activity.registerReceiver(connectionLogger, connectivityFilter);
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
