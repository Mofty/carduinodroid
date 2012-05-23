package swp.tuilmenau.carduinodroid.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Socket_Controller implements Runnable {
	
	Network network;
	ServerSocket socket_controller;
	Socket client;
	ObjectInputStream stream;
	Thread t;

	public Socket_Controller(Network n_network) {
		network = n_network;
		{
			try {
				socket_controller = new ServerSocket(12345);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void accept()
	{
		try {
			client = socket_controller.accept();
			stream = (ObjectInputStream) client.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		t = new Thread(this);
	}

	public void run() {
		// TODO Auto-generated method stub
		int n = 0;
		String message;
		while(client.isConnected())
		{
			try {
				n = stream.available();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(n>0)
			{
				try {
					message = (String)stream.readObject();
					network.receive_controll(message);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
