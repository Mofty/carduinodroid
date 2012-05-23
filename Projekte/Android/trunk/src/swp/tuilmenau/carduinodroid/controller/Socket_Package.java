package swp.tuilmenau.carduinodroid.controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Socket_Package {
	
	ServerSocket socket_package;
	Socket client;
	ObjectOutputStream stream;
	
	public Socket_Package() {
		try {
			socket_package = new ServerSocket(12346);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void accept()
	{
		try {
			client = socket_package.accept();
			stream = (ObjectOutputStream) client.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public boolean sendInfoPackage(String message){
		if(client.isConnected())
		{
		try {
			stream.writeObject(message);
			stream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
		
		}
		else
			return false;
	}

}
