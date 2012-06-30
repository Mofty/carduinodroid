package Controller;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


/**
 * This class provides methods for receiving images from the Android-Application
 * @author Robin
 * @version 18.06.2012
 */
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

	/**
	 * Connect the Socket to the Android-Application
	 * @param nport_picture The Socketaddress
	 */
	public void connect(InetSocketAddress nport_picture)
	{
		port_picture = nport_picture;
		try {
			socket_picture = new Socket();
			socket_picture.connect(port_picture);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			/*
			 * noch �berlegen
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
		

	
	/**
	 * Connect the Socket to the Android-Application
	 */
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
	

	
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		BufferedImage buffer;
		int i = 0;
		while(!socket_picture.isClosed())
		{
			try {
				i =picturestream.available();
				if(i > 0){
				network.receive_picture(readpicture());
				System.out.println("was da zum lesen" + i + "      " + System.currentTimeMillis());
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

	/**
	 * reads the imagedata from the Socket
	 * @return returns the Image
	 */
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
			return picture;
		} catch (IOException e) {
		}
		return null;
	}

	public void close() {
		try {
			socket_picture.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

