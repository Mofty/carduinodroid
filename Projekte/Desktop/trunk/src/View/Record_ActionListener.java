package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import Controller.*;
import Model.Log;

public class Record_ActionListener implements ActionListener{
	Controller_Computer controller_Computer;
	Log log;
	JToggleButton status;
	
	public Record_ActionListener (Controller_Computer controllercomputer, Log LOG, JToggleButton STATUS){
		controller_Computer = controllercomputer;
		log = LOG;
		status = STATUS;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (status.isSelected()){
			log.writelogfile("Sent: Start sound recording.");
			controller_Computer.soundrecording.send_start_recording();
		}
		else{
			log.writelogfile("Sent: Stop sound recording.");
			controller_Computer.soundrecording.send_stop_recording();
		}		
	}
}
