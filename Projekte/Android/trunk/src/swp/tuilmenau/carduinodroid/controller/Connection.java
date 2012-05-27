package swp.tuilmenau.carduinodroid.controller;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

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
 	// InetAddress IP;
	
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
	
	/* aus irgendeinem mir unerklärlichen grud führt die methode zum absturz der app
	 * wer langeweile hat kann sich das ja mal ankucken und es evtl reparieren
	 * gar nich erst fragen ws das hier werden soll ^^
	 * im prinzip soll es die lokale wlan ip auslesen aber naja...
	*/ 
	public String getLocalWLANIP()
	{
		int ipAddress;
		/*String temp = null;
		try {
				IP = InetAddress.getLocalHost();
				temp = IP.getHostAddress();
			} catch (UnknownHostException e) { e.printStackTrace(); }
		return temp; */
		
		wifiManager.getConnectionInfo();
		ipAddress = wifiInfo.getIpAddress();
		return Formatter.formatIpAddress(ipAddress);
	} 
	
	/*
	public String getLocalIpAddress() {
	    try {
	        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	            NetworkInterface intf = en.nextElement();
	            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
	                InetAddress inetAddress = enumIpAddr.nextElement();
	                if (!inetAddress.isLoopbackAddress()) {
	                    return inetAddress.getHostAddress().toString();
	                }
	            }
	        }
	    } catch (SocketException e) { e.printStackTrace(); }
	    return null;
	} */
	
}
