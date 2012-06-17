package Controller;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Socket_Picture implements Runnable{
	
	Socket socket_picture;
	Network network;
	InetSocketAddress port_picture;
	private InputStream picturestream;
	
	Socket_Picture(Network n_network)
	{
		socket_picture = new Socket();
		network = n_network;
		//log = new Log();
	}

	public void connect(InetSocketAddress nport_picture)
	{
		System.out.println("cam connection gestartet");
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
			picturestream = socket_picture.getInputStream();
			run();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	

	
	
	@Override
	public void run() {
		BufferedImage buffer;
		int i = 0;
		while(socket_picture.isConnected())
		{
			try {
				i =picturestream.available();
				if(i > 0){
				network.receive_picture(readpicture());
				System.out.println("was da zum lesen" + i);
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
			connect();
		}

	private ImageIcon readpicture() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			int i = picturestream.available();	
			while(i>0){
				byte[] buffer = new byte[i];
				picturestream.read(buffer);
				baos.write(buffer);
				i = picturestream.available();
			}
			byte[] image = baos.toByteArray();
			ImageIcon picture = new ImageIcon(image);
			System.out.println(picture.getIconHeight()+ ":" + picture.getIconWidth()+":::::"+ image.length);
			return picture;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

