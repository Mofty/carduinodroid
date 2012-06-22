package swp.tuilmenau.carduinodroid.controller;

import android.app.Activity;
import swp.tuilmenau.carduinodroid.model.LOG;

/**
 * Wraps all other classes into this class to be used by the activity.
 * 
 * @author Paul Thorwirth
 * @author Lars
 * @version 1.0
 */
public class Controller_Android 
{
	public LOG log;

	public Arduino arduino;
	public Cam cam;
	public Connection connection;
	public GPS gps;
	public Network network;
	public Record_Sound record_sound;
	public Sound sound;

	public int framerate;

	public Controller_Android(Activity activity) 
	{
		log = new LOG();

		arduino = new Arduino(activity, log);
		cam = new Cam(this, activity);
		connection = new Connection(activity, log);
		gps = new GPS(activity, log);
		record_sound = new Record_Sound(log);
		sound = new Sound(activity);	

		final Controller_Android temp = this;
		new Thread(new Runnable()
		{
			public void run() 
			{
				@SuppressWarnings("unused")
				Network network = new Network(temp);
			}
		}).start();
	}

	/**
	 * Collects all Info and prepares the Info-Package to be sent to the PC-Client.
	 *
	 * @return A {@link String} containing the compressed Data.
	 */
	public String packData() 
	{
		String data;
		data = "";
		if (connection.getMobileAvailable()) data = data + 1 + ";"; 
										else data = data + 0 + ";";
		if (connection.getWLANAvailable())   data = data + 1 + ";"; 
										else data = data + 0 + ";";	
		if (connection.getMobile())			 data = data + 1 + ";"; 
										else data = data + 0 + ";";
		if (connection.getWLAN()) 			 data = data + 1 + ";"; 
										else data = data + 0 + ";";	
		
		data = data + gps.getGPS() + ";";

		return data;
	}

	/**
	 * Used to decode a packed command String received from the PC. 
	 * After the decode the commands are executed.
	 *
	 * @param data A String containing the compressed Data.
	 */
	public void receiveData(String data) 
	{
		String[] parts = data.split(";",-1);

		switch (Integer.parseInt(parts[0]))
		{
			case 1: // alles mit control signals
			{
				//arduino.SendCommand() anpassen von lars im arduino in car controller java
				log.write(LOG.INFO, "data: "+parts[1]+";"+parts[2]+";"+parts[3]+";"+parts[4]);	
			} break;
			
			case 2: // alle mit camera settings
			{
				switch (Integer.parseInt(parts[1]))
				{
					case 1:
					{
						cam.switchCam(Integer.parseInt(parts[2]));
					} break;
					case 2:
					{
						cam.changeRes(Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
					} break;
					
					case 3:
					{
						if (Integer.parseInt(parts[2]) == 1) cam.enableFlash();
						if (Integer.parseInt(parts[2]) == 0) cam.disableFlash();
					} break;
			
					default: log.write(LOG.WARNING, "Unknown camera command from PC"); break;
				}
			} break;
			
			case 3: // alles mit sounds
			{
				log.write(LOG.INFO, "Sound Signal: "+parts[1]+";"+parts[2]);
				switch (Integer.parseInt(parts[1]))
				{
					case 1: 
					{
					//sound.horn(); hier sollte noch parts[2] übergeben werden, für mehrere Sounds später und stimmt das bei der Klasse Sound so mit "onLoad" Listener???
					log.write(LOG.INFO, "Signal was played");
					} break;
					case 2:
					{
						if (Integer.parseInt(parts[2]) == 0) {
							//record_sound.stopRecord();
							log.write(LOG.INFO, "Sound Recording has stopped");
						}
						else {
							//record_sound.startRecord();
							log.write(LOG.INFO, "Sound Recording has started");
						}
					} break;
				
					default: log.write(LOG.WARNING, "Unknown camera command from PC"); break;
				}
			} break;

			default: log.write(LOG.WARNING, "unknown command from PC"); break;
		}
	}
	
}