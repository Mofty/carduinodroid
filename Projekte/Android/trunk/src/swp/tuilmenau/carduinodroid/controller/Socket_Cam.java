
package swp.tuilmenau.carduinodroid.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import android.util.Log;

public class Socket_Cam implements Runnable {
	
	BufferedWriter packagewriter;
	ServerSocket socket_package;
	Socket client;

	Controller_Android controller_Android;

	public Socket_Cam(Controller_Android n_controller_Android) {
		controller_Android = n_controller_Android;
		Log.v("hauptthread","cam erstellt");
	}


	public void run() {
			ServerSocket socket_package = null;
			client = null;
			packagewriter = null;
			Log.v("thread package","thread package gestartet");
			try {
				socket_package = new ServerSocket(12346);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
					Log.v("thread package","serversocket fehlgeschlagen");

				}
				try {
					client = socket_package.accept();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					Log.v("thread package","accept fehlgeschlagen");
				}
				try {
					controller_Android.cam.cameraPreview.setOutputstream(client.getOutputStream());
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					Log.v("thread package","output bekommen fehlgeschlagen");
				}
				
			if(client != null)
				Log.v("thread package","javaprog gefunden" + client.getInetAddress().toString());


				//preview starten
		}
}

