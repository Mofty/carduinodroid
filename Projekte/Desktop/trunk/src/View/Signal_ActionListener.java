package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.Log;

public class Signal_ActionListener implements ActionListener{
	
	Log Signal_log = new Log();
	
	public Signal_ActionListener (Log log){
		Signal_log = log;
	}
	
	public void actionPerformed(ActionEvent e) {
		Signal_log.writelogfile("Signalton gesendet.");
	}
}
