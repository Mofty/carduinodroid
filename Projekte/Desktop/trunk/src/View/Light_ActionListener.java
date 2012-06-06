package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import Controller.*;
import Model.Log;

public class Light_ActionListener implements ActionListener{
	Controller_Computer controller_Computer;
	Log log;
	JToggleButton status;
	
	public Light_ActionListener (Controller_Computer controllercomputer, Log LOG, JToggleButton STATUS)
	{
		controller_Computer = controllercomputer;
		log = LOG;
		status = STATUS;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (status.isSelected()){
			log.writelogfile("Sent: Switch camera light on.");
			controller_Computer.camera_settings.send_switch_light("1");
		}
		else{
			log.writelogfile("Sent: Switch camera light off.");
			controller_Computer.camera_settings.send_switch_light("0");
		}
		
	}

}
