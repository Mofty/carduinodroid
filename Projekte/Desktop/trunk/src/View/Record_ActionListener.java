package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.*;
import Model.Log;

public class Record_ActionListener implements ActionListener{
	Controller_Computer controller_Computer;
	Log log;
	
	public Record_ActionListener (Controller_Computer controllercomputer)
	{controller_Computer = controllercomputer;}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//If else anhand vom Stand der Buttons - übergabe?
		controller_Computer.soundrecording.send_start_recording();
		log.writelogfile("Recording was started.");
		
		controller_Computer.soundrecording.send_stop_recording();
		log.writelogfile("Recording was stopped.");
		
		
	}

}
