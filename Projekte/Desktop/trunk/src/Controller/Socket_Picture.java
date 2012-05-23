package Controller;

import java.awt.Image;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;

public class Socket_Picture implements Runnable{
	
	Socket socket_picture;
	Network network;
	ObjectInputStream picturestream;
	InetSocketAddress port_picture;
	
	Socket_Picture(Network n_network)
	{
		socket_picture = new Socket();
		network = n_network;
	}

	public void connect(InetSocketAddress n_port_package){
		port_picture = n_port_package;
	
		try {
			socket_picture.connect(port_picture);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			/*
			 * noch überlegen
			 */
			e.printStackTrace();
		}
		
		try {
			picturestream = (ObjectInputStream) socket_picture.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		
		public void connect(){
			try {
				socket_picture.connect(port_picture);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				/*
				 * noch überlegen
				 */
				e.printStackTrace();
			}
			
			try {
				picturestream = (ObjectInputStream) socket_picture.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}
	
	@Override
	public void run() {
		int n = 0;
		Image image;
		while(socket_picture.isConnected())
		{
			try {
				n = picturestream.available();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(n>0)
			{
				try {
					image = (Image)picturestream.readObject();
					network.receive_picture(image);
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
		connect();
		network.start_picture_thread();
	}

	public boolean isConnected() {
		return socket_picture.isConnected();
	}

}
