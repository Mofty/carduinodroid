package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.GPSTrack;
import Model.Log;

/** Description of Quit_ActionListener
*
* @author Benjamin L
* @version 12.06.2012.
*/
public class Quit_ActionListener implements ActionListener {
	Log log;
	GPSTrack gpstrack;
	
	/** Description of Quit_ActionListener(Log LOG, GPSTrack GPS_Track) 
	 * 
	 * @param LOG					Used log. 
	 * @param GPS_Track				Used gpstrack.
	 */
	public Quit_ActionListener (Log LOG, GPSTrack GPS_Track)
	{
		log = LOG;
		gpstrack = GPS_Track;
	}
	
	/** Description of actionPreformed(ActionEvent e)
	 * @param e			Event by pressing the quit menuitem.
	 */
	public void actionPerformed(ActionEvent e) {
		gpstrack.savegpxfile();
		log.writelogfile("CarDuinoDroid closed.");
		System.exit(0);
	}
}
