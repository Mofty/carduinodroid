package swp.tuilmenau.carduinodroid.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import swp.tuilmenau.carduinodroid.model.LOG;

/**
 * This class is used to send information to the PC
 * 
 * @author Robin
 */
public class Socket_Package implements Runnable {

	private static final String ACKNOWLEDGE = "ACK";
	BufferedWriter packagewriter;
	ServerSocket socket_package;
	Socket client;

	Controller_Android controller_Android;
	BufferedReader packagereader;
	private Network network;

	/**
	 * The constructor
	 * 
	 * @param n_controller_Android
	 */
	public Socket_Package(Controller_Android n_controller_Android, Network nnetwork) {
		controller_Android = n_controller_Android;
		network = nnetwork;
	}

	/**
	 * Sends the information package to the socket
	 * 
	 * @param infopackage
	 *            The information
	 * @return true if successful
	 */
	public boolean sendpackage(String infopackage) {
		if (client.isConnected()) {
			try {
				packagewriter.write(infopackage);
				packagewriter.newLine();
				packagewriter.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
			return true;
		}

		else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		ServerSocket socket_package = null;
		boolean isCameraInfoSend = false;
		while (true) {
			client = null;
			packagewriter = null;
			int i = 0;
			boolean fail = true;
			try {
				if (socket_package == null)
					socket_package = new ServerSocket(12346);
				client = socket_package.accept();
				packagewriter = new BufferedWriter(new OutputStreamWriter(
						client.getOutputStream()));
				packagereader = new BufferedReader(new InputStreamReader(
						client.getInputStream()));

			} catch (IOException e1) {
				// TODO Auto-generated catch block
			}

			 if(client != null && !isCameraInfoSend)
				 sendCameraSizes();

				while (!client.isClosed() && fail) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					i++;
					if (i > 15) {
						sendpackage(ACKNOWLEDGE);
						new Thread(new Runnable() {

							public void run() {
								long currenttime = System.currentTimeMillis();
								boolean getMessage = false;
								while (currenttime + 5000 > System.currentTimeMillis() && client.isConnected() && !getMessage) {
									try {
										if (packagereader.ready()) {
											String msg = packagereader.readLine();
											if (msg.equals(ACKNOWLEDGE)) {
												getMessage = true;
											}
										}
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								if (!getMessage) {
									network.close();
								}
							}
						}).start();
						i = 0;
					}
					String message = controller_Android.packData();
					sendpackage(message);
				}
			} 
		}
	public void sendCameraSizes() {
		String sizes = network.controller.cam.getSupportedSize();
			try {
				packagewriter.write("2;" + sizes);
				packagewriter.newLine();
				packagewriter.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				network.controller.log.write(LOG.WARNING, "Error by writing onto Packagesocket");
			}
	}

	public void close() {
		try {
			if (client != null)
				client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
