package swp.tuilmenau.carduinodroid.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import android.content.Context;
import android.net.*;

public class Connection 
{
	ConnectivityManager connection;
 	NetworkInfo mobileInfo;
 	NetworkInfo WLANInfo;
 	static InetAddress IP;
	
	public Connection (Context context)
	{
		connection = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	}
	
	public boolean getMobileAvailable()
	{
		mobileInfo = connection.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		return mobileInfo.isAvailable();
	}
	
	public boolean getWLANAvailable()
	{
		WLANInfo = connection.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		return WLANInfo.isAvailable();
	}
	
	public boolean getMobile()
	{
		mobileInfo = connection.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		return mobileInfo.isConnected();
	}
	
	public boolean getWLAN()
	{
		WLANInfo = connection.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		return WLANInfo.isConnected();
	}
	
	public String getIP()
	{
		try {
				IP = InetAddress.getLocalHost();
			} catch (UnknownHostException e) { e.printStackTrace(); }
		return IP.toString();
	}
	
}
