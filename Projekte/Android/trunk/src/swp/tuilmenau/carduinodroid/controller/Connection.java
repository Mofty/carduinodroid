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
	private LOG log = null;
	
	public class ConnectionLogger extends BroadcastReceiver
	{
		private TextView IPBox;
		private Activity activity;

		public ConnectionLogger(Activity n_activity)
		{
			super();
			activity = n_activity;	
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
				{
					log.write("WLAN is connected");
					IPBox = (TextView) activity.findViewById(R.id.textView2); 
			        IPBox.setText(getLocalWLANIP());
			        
				}
				else 
					log.write("WLAN is not connected");
			}
        }
	}
	
	private ConnectivityManager connectivityManager;
	private WifiManager wifiManager;
	private NetworkInfo mobileInfo;
	private NetworkInfo WLANInfo;
	private WifiInfo wifiInfo;
	private ConnectionLogger connectionLogger;
	private IntentFilter connectivityFilter;
	
	public Connection (Activity activity, LOG n_log)
	{
		connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		wifiManager = (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);	
		log = n_log;
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
		if (getWLANAvailable())
		{	
			wifiInfo = wifiManager.getConnectionInfo();
			int ipAddress = wifiInfo.getIpAddress(); 
			return Formatter.formatIpAddress(ipAddress);
		} else return null;
			
	} 	
}
