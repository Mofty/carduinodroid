package swp.tuilmenau.carduinodroid.controller;

import android.content.Context;
import android.net.*;
import android.net.wifi.*;
import android.text.format.Formatter;

public class Connection 
{
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
		int ipAddress;
		
		wifiInfo = wifiManager.getConnectionInfo();
		ipAddress = wifiInfo.getIpAddress(); 
		return Formatter.formatIpAddress(ipAddress);
	} 	
}
