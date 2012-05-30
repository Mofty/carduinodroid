package swp.tuilmenau.carduinodroid.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class Socket_Controller implements Runnable {
	


	Thread t;
	Handler handler;

	public Socket_Controller(Handler n_handler) {
		handler = n_handler;
		Log.v("hauptthread","controller erstellt");
	}

	public void run() {
		ServerSocket socket_controller = null;
		Socket client = null;
		BufferedReader stream = null;
		Message msg = new Message();
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
