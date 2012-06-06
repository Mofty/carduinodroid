package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import Controller.*;
import Model.Log;

public class Resolution_ActionListener implements ActionListener{
	Controller_Computer controller_Computer;
	Log log;
	JComboBox status;
	
	public Resolution_ActionListener (Controller_Computer controllercomputer, Log LOG, JComboBox STATUS){
		controller_Computer = controllercomputer;
		log = LOG;
		status = STATUS;
	}
	
	public void actionPerformed(ActionEvent e) {
		String resolution = String.valueOf(status.getSelectedIndex());
		
		log.writelogfile("Sent: Change resolution.");
		controller_Computer.camera_settings.send_change_resolution(resolution);
	}

}
