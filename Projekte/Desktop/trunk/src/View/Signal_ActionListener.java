package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.Log;

public class Signal_ActionListener implements ActionListener{
	
	Log log;
	
	public Signal_ActionListener (Log LOG)
	{
		super();
		log = LOG;
	}
	
	public void actionPerformed(ActionEvent e) {
		log.writelogfile("Signalton gesendet.");
	}
}
