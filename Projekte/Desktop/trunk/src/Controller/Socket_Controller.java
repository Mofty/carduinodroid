package Controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Socket_Controller {
	
	BufferedWriter controll;
	Socket socket_controll;
	String mobilephone_ip;


	Socket_Controller(){
		socket_controll = new Socket();
	}
	
	public void connect(InetSocketAddress port_controll)
	{
		try {
			socket_controll.connect(port_controll);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			/*
			 * noch überlegen
			 */
			System.out.println("fehler beim connecten");
		}
		
		try {
			controll = new BufferedWriter(new OutputStreamWriter(socket_controll.getOutputStream()));
		} catch (IOException e) {
			System.out.println("fehler beim outputstream");
		}
		
		
		

	}
	
	public boolean send_controllsignal(String direction)
	{
		if(socket_controll.isConnected())
		{
			String bufferdirection = "1;" + direction;
			try {
				controll.write(bufferdirection);
				controll.newLine();
				controll.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("fail");
			}
			return true;
		}
		
		else{
		return false;
		}
	}
	
	public boolean send_camera_settings(String settings)
	{
		if(socket_controll.isConnected())
		{
			String buffersettings = "2;" + settings;
			try {
				controll.write(buffersettings);
				controll.newLine();
				controll.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("fail");
			}
			return true;
		}
		
		else{
		return false;
		}
	}
	
	public boolean send_sound(String sound_id)
	{
		if(socket_controll.isConnected())
		{
			String buffersound_id = "3;" + sound_id;
			try {
				controll.write(buffersound_id);
				controll.newLine();
				controll.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("fail");
			}
			return true;
		}
		
		else{
		return false;
		}
	}

}
