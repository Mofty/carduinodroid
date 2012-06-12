package Controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

import javax.imageio.ImageIO;

import Model.Log;

public class Socket_Picture implements Runnable{
	
	Socket socket_picture;
	Network network;
	InetSocketAddress port_picture;
	Log log;
	private InputStream picturestream;
	
	Socket_Picture(Network n_network)
	{
		socket_picture = new Socket();
		network = n_network;
		//log = new Log();
	}

	public void connect(InetSocketAddress nport_picture)
	{
		port_picture = nport_picture;
		try {
			socket_picture.connect(port_picture);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			/*
			 * noch überlegen
			 */
			System.out.println("fehler beim connecten");
		}
		
		try {
			picturestream = socket_picture.getInputStream();
		} catch (IOException e) {
			System.out.println("fehler beim inputstream");
		}
		System.out.println("ist connected");
	}
		

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
				picturestream = socket_picture.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	
	@Override
	public void run() {
		BufferedImage buffer;
		while(socket_picture.isConnected())
		{
			try {
				if(picturestream.available() > 0){
				System.out.println("was da zum lesen");
				buffer = ImageIO.read(picturestream);
				System.out.println(buffer.getHeight() +" "+ buffer.getWidth());
				network.receive_picture(buffer);
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
		}
}
