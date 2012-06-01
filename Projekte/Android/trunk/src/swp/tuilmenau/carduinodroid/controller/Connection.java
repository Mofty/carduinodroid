package swp.tuilmenau.carduinodroid.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.*;
import android.net.wifi.*;
import android.text.format.Formatter;

public class Connection 
{
	public class ConnectionLogger extends BroadcastReceiver
	{
        @Override
        public void onReceive(Context context, Intent intent) 
        {
        	// fehler verursachender log teil siehe oben !
    		// unötig da listener v erendet werden in gps und connection
    		
    		
    		if (mobile != connection.getMobileAvailable())
    		{
    			mobile = connection.getMobileAvailable();
    			if (mobile)
    				log.write("Mobile Internet is available");
    			else 
    				log.write("Mobile Internet is not available");
    		}
    		
    		if (mobileConnected != connection.getMobile())
    		{
    			mobileConnected = connection.getMobile();
    			if (mobileConnected)
    				log.write("Mobile Internet is connected");
    			else 
    				log.write("Mobile Internet is not connected");
    		}
    		
    		if (wlan != connection.getWLANAvailable())
    		{
    			wlan = connection.getWLANAvailable();
    			if (wlan)
    				log.write("WLAN is available");
    			else 
    				log.write("WLAN is not available");
    		}
    		
    		if (wlanConnected != connection.getWLAN())
    		{
    			wlanConnected = connection.getWLAN();
    			if (wlanConnected)
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
	
	public Connection (Context context)
	{
		connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);	
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
