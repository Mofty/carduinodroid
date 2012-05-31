package swp.tuilmenau.carduinodroid.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import android.util.Log;

public class Socket_Package implements Runnable {
	
	BufferedWriter packagewriter;
	ServerSocket socket_package;
	Socket client;

	Controller_Android controller_Android;

	public Socket_Package(Controller_Android n_controller_Android) {
		controller_Android = n_controller_Android;
		Log.v("hauptthread","package erstellt");
	}
	

	public boolean sendpackage(String infopackage)
	{
		if(client.isConnected())
		{
			try {
				packagewriter.write(infopackage);
				packagewriter.newLine();
				packagewriter.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.v("thread package","fehler beim senden");
			}
			return true;
		}
		
		else{
		return false;
		}
	}


	public void run() {
			ServerSocket socket_package = null;
			client = null;
			packagewriter = null;
			Log.v("thread package","thread package gestartet");

			boolean fail = true;
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
					packagewriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					Log.v("thread package","output bekommen fehlgeschlagen");
				}
				
			if(client != null)
				Log.v("thread package","javaprog gefunden" + client.getInetAddress().toString());


			if(fail){
				Log.v("thread package","schleife wird gestartet");
				while(client.isConnected() && fail){
					Log.v("thread package","schleife gestartet");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						Log.v("thread package","fehler beim warten");
					}
					String message = controller_Android.packData();
					Log.v("thread package", message);
					sendpackage(message);
					Log.v("thread package", "message gesendet");
				}
			}
			else{
				Log.v("thread package","schleife wurde nicht gestartet");
			}
	}
}
