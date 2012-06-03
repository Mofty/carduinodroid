package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.*;
import Model.Log;

public class Resolution_ActionListener implements ActionListener{
	Controller_Computer controller_Computer;
	Log log;
	
	public Resolution_ActionListener (Controller_Computer controllercomputer)
	{controller_Computer = controllercomputer;}
	
	public void actionPerformed(ActionEvent e) {
		
		controller_Computer.camera_settings.send_change_resolution("");
		log.writelogfile("Resolution has been changed.");
		
	}

}
