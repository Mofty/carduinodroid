package swp.tuilmenau.carduinodroid.controller;

import android.content.Context;
import android.net.*;

public class Connection 
{
	ConnectivityManager connection;
 	NetworkInfo[] networkInfo;
	
	public Connection (Context context)
	{
		connection = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		networkInfo = new NetworkInfo[1];
	}
	
	public boolean getMobile()
	{
		networkInfo[0] = connection.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		return networkInfo[0].isAvailable();
	}
	
	public boolean getWLAN()
	{
		networkInfo[1] = connection.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		return networkInfo[1].isAvailable();
	}
}
