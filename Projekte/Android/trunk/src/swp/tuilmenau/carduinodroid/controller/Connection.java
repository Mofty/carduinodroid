package swp.tuilmenau.carduinodroid.controller;

import swp.tuilmenau.carduinodroid.model.LOG;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.*;
import android.net.wifi.*;
import android.text.format.Formatter;

public class Connection 
{
	LOG log;
	
	public class ConnectionLogger extends BroadcastReceiver
	{
        @Override
        public void onReceive(Context context, Intent intent) 
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
	
	ConnectivityManager connectivityManager;
	WifiManager wifiManager;
 	NetworkInfo mobileInfo;
 	NetworkInfo WLANInfo;
 	WifiInfo wifiInfo;
 	Context context;
	
	public Connection (Context n_context)
	{
		connectivityManager = (ConnectivityManager) n_context.getSystemService(Context.CONNECTIVITY_SERVICE);
		wifiManager = (WifiManager) n_context.getSystemService(Context.WIFI_SERVICE);	
		context = n_context;
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
