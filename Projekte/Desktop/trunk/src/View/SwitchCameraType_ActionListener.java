package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.Controller_Computer;
import Model.Log;

public class SwitchCameraType_ActionListener implements ActionListener{
	Controller_Computer controller_Computer;
	Log log;
	boolean camera;
	
	public SwitchCameraType_ActionListener (Controller_Computer controllercomputer, Log LOG, boolean CAMERA)
	{
		controller_Computer = controllercomputer;
		log = LOG;
		camera = CAMERA;
		}
	
	public void actionPerformed(ActionEvent e) {
		if (camera) {
			log.writelogfile("Sent: Enable frontcamera.");
			controller_Computer.camera_settings.send_change_camera("0");
		}
		else {
			log.writelogfile("Sent: Enable backcamera.");
			controller_Computer.camera_settings.send_change_camera("1");
		}
	}
}
