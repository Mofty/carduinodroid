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
		data = "1;";
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
	 * @param data A String containing the compressed Data as follows:
	 * <ol>
	 * 	<li>Control Signals with settings</li>
	 * 	<li>Camera Settings</li>
	 *	<ol>
	 *		<li>Front or Back Camera</li>
	 * 		<li>Camera Resolution</li>
	 * 		<li>Camera Light</li>
	 * 	</ol>
	 * 	<li>Sound Signals</li>
	 * 	<ol>
	 * 		<li>Play a Sound by Android phone</li>
	 * 		<li>Start or Stop a Record</li>
	 * 	</ol>
	 * </ol>
	 */
	//TODO It work but it looks like shit and is hardly called "easily expandable".
	public void receiveData(String data)
	{
		boolean front, right;
		String trueString = "true";
		String[] parts = data.split(";",-1);

		switch (Integer.parseInt(parts[0]))
		{
			case 1: // Everything for control signals
			{
				front = (parts[2].equals(trueString));
				right = (parts[4].equals(trueString));
				arduino.SendCommand(front, Integer.parseInt(parts[1]), right, Integer.parseInt(parts[3])); //anpassen von lars im arduino in car controller java
				log.write(LOG.INFO, "data: "+parts[1]+";"+parts[2]+";"+parts[3]+";"+parts[4]);	
			} break;
			
			case 2: // Everything for camera settings
			{
				switch (Integer.parseInt(parts[1]))
				{
					case 1:
					{
						cam.switchCam(Integer.parseInt(parts[2])); //Anpassen mit Robin
					} break;
					case 2:
					{
						cam.changeRes(Integer.parseInt(parts[2]), Integer.parseInt(parts[3])); //Anpassen mit Robin
					} break;
					
					case 3:
					{
						if (Integer.parseInt(parts[2]) == 1) cam.enableFlash();
						if (Integer.parseInt(parts[2]) == 0) cam.disableFlash();
					} break;
			
					default: log.write(LOG.WARNING, "Unknown camera command from PC"); break;
				}
			} break;
			
			case 3: // Everything with sounds
			{
				switch (Integer.parseInt(parts[1]))
				{
					case 1: 
					{	
						sound.horn(Integer.parseInt(parts[2]));
						log.write(LOG.INFO, "Signal was played");
					} break;
					
					case 2:
					{
						if (Integer.parseInt(parts[2]) == 0) 
						{
							record_sound.stopRecord();
						}
						else 
						{
							record_sound.startRecord();
						}
					} break;
				
					default: log.write(LOG.WARNING, "Unknown Sound command from PC"); break;
				}
			} break;

			default: log.write(LOG.WARNING, "unknown command from PC"); break;
		}
	}
}