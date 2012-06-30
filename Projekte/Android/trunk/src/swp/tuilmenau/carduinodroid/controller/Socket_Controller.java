package swp.tuilmenau.carduinodroid.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import swp.tuilmenau.carduinodroid.model.LOG;

/**
 * This class is used for receiving data from the pc to control the application
 * 
 * @author Robin
 */
public class Socket_Controller implements Runnable {

	Controller_Android controller_Android;
	ServerSocket socket_controller;
	Socket client;

	/**
	 * The constructor of the Socket_Controller
	 * 
	 * @param n_controller_Android
	 */
	public Socket_Controller(Controller_Android n_controller_Android) {
		controller_Android = n_controller_Android;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		while (true) {
			socket_controller = null;
			client = null;
			BufferedReader stream = null;
			long timestamp = System.currentTimeMillis();
			boolean stop = true;
			boolean fail = true;
			try {
				if (socket_controller == null)
					socket_controller = new ServerSocket(12345);
				client = socket_controller.accept();
				stream = new BufferedReader(new InputStreamReader(
						client.getInputStream()));

			} catch (IOException e1) {
				// TODO Auto-generated catch block
			}

			String message;
			while (!client.isClosed() && fail) {
				try {
					if (stream.ready()) {

						message = (String) stream.readLine();
						controller_Android.log.write(LOG.INFO, message);
						controller_Android.receiveData(message);
						if (message.charAt(0) == 1) {
							timestamp = System.currentTimeMillis();
							stop = false;
						}
					}

					else {
						if ((timestamp + 500) < System.currentTimeMillis()
								&& !stop) {
							controller_Android.receiveData("1;0;0;0;0"); // stopp
																			// signal
							stop = true;
						}
						try {
							Thread.sleep(25);
						} catch (InterruptedException e) {

						}
					}
				} catch (IOException e) {
				}
			}
		}
	}

	public void close() {
		// TODO Auto-generated method stub
		try {
			if (client != null)
				client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
