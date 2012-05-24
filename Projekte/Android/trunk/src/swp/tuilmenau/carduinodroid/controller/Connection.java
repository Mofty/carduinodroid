package swp.tuilmenau.carduinodroid.controller;

import android.content.Context;
import android.net.*;

public class Connection 
{
	ConnectivityManager connection;
 	NetworkInfo mobileInfo;
 	NetworkInfo WLANInfo;
	
	public Connection (Context context)
	{
		connection = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	}
	
	public boolean getMobile()
	{
		mobileInfo = connection.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		return mobileInfo.isAvailable();
	}
	
	public boolean getWLAN()
	{
		WLANInfo = connection.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		return WLANInfo.isAvailable();
	}
}
