package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.Controller_Computer;
import Model.Log;

public class SwitchCameraType_ActionListener implements ActionListener{
	Controller_Computer controller_Computer;
	Log log;
	
	public SwitchCameraType_ActionListener (Controller_Computer controllercomputer, Log LOG)
	{
		controller_Computer = controllercomputer;
		log = LOG;
		}
	
	public void actionPerformed(ActionEvent e) {
		//If Else mit Bedingung auf die ausgewählte Fläche
		controller_Computer.camera_settings.send_change_camera("0");
		log.writelogfile("Front camera has been enabled.");
		
		controller_Computer.camera_settings.send_change_camera("1");
		log.writelogfile("Back camera has been enabled.");
	}
}
