package Controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;

public class Socket_Package implements Runnable{
	
	Socket socket_package;
	Network network;
	ObjectInputStream packagestream;
	InetSocketAddress port_package;
	
	Socket_Package(Network n_network)
	{
		socket_package = new Socket();
		network = n_network;
	}

	public void connect(InetSocketAddress n_port_package){
		port_package = n_port_package;
	
		try {
			socket_package.connect(port_package);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			/*
			 * noch überlegen
			 */
			e.printStackTrace();
		}
		
		try {
			packagestream = (ObjectInputStream) socket_package.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		
		public void connect(){
			try {
				socket_package.connect(port_package);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				/*
				 * noch überlegen
				 */
				e.printStackTrace();
			}
			
			try {
				packagestream = (ObjectInputStream) socket_package.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	
	@Override
	public void run() {
		int n = 0;
		String message;
		while(socket_package.isConnected())
		{
			try {
				n = packagestream.available();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(n>0)
			{
				try {
					message = (String)packagestream.readObject();
					network.receive_package(message);
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
		network.start_package_thread();
	}

	public boolean isConnected() {
		return socket_package.isConnected();
	}

}
