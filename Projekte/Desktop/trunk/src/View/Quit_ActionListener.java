package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.Log;

public class Quit_ActionListener implements ActionListener {
Log log;
	
	public Quit_ActionListener (Log LOG)
	{
		log = LOG;
	}
	
	public void actionPerformed(ActionEvent e) {
		log.writelogfile("CarDuinoDroid closed.");
		System.exit(0);
	}
}
