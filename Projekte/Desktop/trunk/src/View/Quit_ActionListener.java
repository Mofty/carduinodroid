package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.GPSTrack;
import Model.Log;

public class Quit_ActionListener implements ActionListener {
Log log;
GPSTrack gpstrack;
	
	public Quit_ActionListener (Log LOG,GPSTrack GPS_Track)
	{
		log = LOG;
		gpstrack = GPS_Track;
	}
	
	public void actionPerformed(ActionEvent e) {
		gpstrack.savegpxfile();
		log.writelogfile("CarDuinoDroid closed.");
		System.exit(0);
	}
}
