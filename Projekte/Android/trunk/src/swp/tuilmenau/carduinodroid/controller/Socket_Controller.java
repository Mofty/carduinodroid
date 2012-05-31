package swp.tuilmenau.carduinodroid.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import android.util.Log;

public class Socket_Controller implements Runnable {
	


	Controller_Android controller_Android;

	public Socket_Controller(Controller_Android n_controller_Android) {
		controller_Android = n_controller_Android;
		Log.v("hauptthread","controller erstellt");
	}

	public void run() {
		ServerSocket socket_controller = null;
		Socket client = null;
		BufferedReader stream = null;
		Log.v("thread controller","thread controller gestartet");

		boolean fail = true;
		try {
			socket_controller = new ServerSocket(12345);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
				Log.v("thread controller","serversocket fehlgeschlagen");

			}
			try {
				client = socket_controller.accept();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				Log.v("thread controller","accept fehlgeschlagen");
			}
			try {
				stream = new BufferedReader(new InputStreamReader(client.getInputStream()));
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				Log.v("thread controller","inputstream bekommen fehlgeschlagen");
			}
			
		if(client != null)
			Log.v("thread controller","javaprog gefunden" + client.getInetAddress().toString());


		String message;
		if(fail){
		while(client.isConnected() && fail)
		{
			try {
				if(stream.ready()){
					Log.v("thread controller", "was da zum lesen");

				message = (String) stream.readLine();
				Log.v("thread controller", message);
				controller_Android.log.write(message);
			}
				
				else
				{
					try {
						Thread.sleep(25);
					} catch (InterruptedException e) {
						Log.v("thread controller", "fehler beim schlafen");

					}
				}
			} catch (IOException e) {
				Log.v("thread controller", "fehler beim lesen");
			}
		}}
		Log.v("thread controller", "failor");
	}

}
