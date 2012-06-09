package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

import Model.Log;

public class Socket_Package implements Runnable{
	
	Socket socket_package;
	Network network;
	BufferedReader packagereader;
	InetSocketAddress port_package;
	Log log;
	
	Socket_Package(Network n_network)
	{
		socket_package = new Socket();
		network = n_network;
		//log = new Log();
	}

	public void connect(InetSocketAddress nport_package)
	{
		port_package = nport_package;
		try {
			socket_package.connect(port_package);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			/*
			 * noch überlegen
			 */
			System.out.println("fehler beim connecten");
		}
		
		try {
			packagereader = new BufferedReader(new InputStreamReader(socket_package.getInputStream()));
		} catch (IOException e) {
			System.out.println("fehler beim inputstream");
		}
		System.out.println("ist connected");
	}
		

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
				packagereader = new BufferedReader(new InputStreamReader(socket_package.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	
	@Override
	public void run() {
		String message;
		while(socket_package.isConnected())
		{
			try {
				if(packagereader.ready()){
				System.out.println("was da zum lesen");
				message = (String) packagereader.readLine();
				System.out.println(message);
				}
					
					else
					{
						try {
							Thread.sleep(25);
						} catch (InterruptedException e) {
							System.out.println("fehler beim schlafen");
						}
					}
				} catch (IOException e) {
					System.out.println("fehler beim lesen");
				}
			}
			log.savelogfile();
		}
}
