package swp.tuilmenau.carduinodroid.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import swp.tuilmenau.carduinodroid.model.LOG;
import android.util.Log;

/**
 * @author Robin
 * This class is used for receiving data from the pc to control the application
 */
public class Socket_Controller implements Runnable {
	


	Controller_Android controller_Android;

	/**
	 * The constructor of the Socket_Controller
	 * @param n_controller_Android
	 */
	public Socket_Controller(Controller_Android n_controller_Android) {
		controller_Android = n_controller_Android;
		Log.e("hauptthread","controller erstellt");
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		ServerSocket socket_controller = null;
		Socket client = null;
		BufferedReader stream = null;
		Log.e("thread controller","thread controller gestartet");

		boolean fail = true;
		try {
			socket_controller = new ServerSocket(12345);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
				Log.e("thread controller","serversocket fehlgeschlagen");

			}
			try {
				client = socket_controller.accept();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				Log.e("thread controller","accept fehlgeschlagen");
			}
			try {
				stream = new BufferedReader(new InputStreamReader(client.getInputStream()));
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				Log.e("thread controller","inputstream bekommen fehlgeschlagen");
			}
			
		if(client != null)
			Log.e("thread controller","javaprog gefunden" + client.getInetAddress().toString());


		String message;
		if(fail){
		while(client.isConnected() && fail)
		{
			try {
				if(stream.ready()){
					Log.e("thread controller", "was da zum lesen");

				message = (String) stream.readLine();
				Log.e("thread controller", message);
				controller_Android.log.write(LOG.INFO, message);
				controller_Android.receiveData(message);
			}
				
				else
				{
					try {
						Thread.sleep(25);
					} catch (InterruptedException e) {
						Log.e("thread controller", "fehler beim schlafen");

					}
				}
			} catch (IOException e) {
				Log.e("thread controller", "fehler beim lesen");
			}
		}}
		Log.e("thread controller", "failor");
	}

}
