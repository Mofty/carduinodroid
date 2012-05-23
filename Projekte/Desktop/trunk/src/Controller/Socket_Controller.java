package Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Socket_Controller {
	
	OutputStream controll;
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
			e.printStackTrace();
		}
		
		try {
			controll = socket_controll.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}
	
	public boolean send_controllsignal(String direction)
	{
		if(socket_controll.isConnected())
		{
			String bufferdirection = "1," + direction;
			byte[] buffer = bufferdirection.getBytes();
			try {
				controll.write(buffer);
				controll.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
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
			String buffersettings = "2," + settings;
			byte[] buffer = buffersettings.getBytes();
			try {
				controll.write(buffer);
				controll.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
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
			String buffersound_id = "3," + sound_id;
			byte[] buffer = buffersound_id.getBytes();
			try {
				controll.write(buffer);
				controll.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		else{
		return false;
		}
	}

}
