package swp.tuilmenau.carduinodroid.controller;

import android.app.Activity;
import android.util.Log;
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

	/**
	 * Calls the Constructor of all other sub-classes.
	 * 
	 * @param activity the current Activity
	 */
	public Controller_Android(Activity activity) 
	{
		log = new LOG();

		arduino = new Arduino(activity, log);
		cam = new Cam(this, activity);
		connection = new Connection(activity, log);
		gps = new GPS(activity, log);
		record_sound = new Record_Sound(log);
		sound = new Sound(activity, log);	

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
	 * 		<li>Quality</>
	 * 	</ol>
	 * 	<li>Sound Signals</li>
	 * 	<ol>
	 * 		<li>Play a Sound by Android phone</li>
	 * 		<li>Start or Stop a Record</li>
	 * 	</ol>
	 * </ol>
	 */
	public void receiveData(String data)
	{
		final String TRUE_STRING = "true";
		
		boolean front, right;
		String[] parts = data.split(";",-1);

		switch (Integer.parseInt(parts[0]))
		{
			case 1: // Everything for control signals
			{
				front = (parts[2].equals(TRUE_STRING));
				right = (parts[4].equals(TRUE_STRING));
				arduino.SendCommand(front, Integer.parseInt(parts[1]), right, Integer.parseInt(parts[3]));
				log.write(LOG.INFO, "data: "+parts[1]+";"+parts[2]+";"+parts[3]+";"+parts[4]);	
			} break;
			
			case 2: // Everything for camera settings
			{
				switch (Integer.parseInt(parts[1]))
				{
					case 1:
					{
						cam.switchCam(Integer.parseInt(parts[2])); //Anpassen mit Robin
						log.write(LOG.INFO, "Switched Camera");
					} break;
					case 2:
					{
						Log.wtf("cam", "reschange" + parts[2]);
						cam.changeRes(Integer.parseInt(parts[2])); //Anpassen mit Robin
					} break;
					
					case 3:
					{
						if (Integer.parseInt(parts[2]) == 1) cam.enableFlash();
						if (Integer.parseInt(parts[2]) == 0) cam.disableFlash();
					} break;
					
					case 4:
					{
						cam.setQuality(Integer.parseInt(parts[2]));
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
						sound.horn();
					} break;
					
					case 2:
					{
						if (Integer.parseInt(parts[2]) == 0) 
						{
							record_sound.stop();
						}
						else 
						{
							record_sound.start();
						}
					} break;
				
					default: log.write(LOG.WARNING, "Unknown Sound command from PC"); break;
				}
			} break;

			default: log.write(LOG.WARNING, "unknown command from PC"); break;
		}
	}

	/**
	 * Sets the Network
	 * 
	 * @param network the Network to set.
	 */
	public void setNetwork(Network network) 
	{
		this.network = network;
	}
}