package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.*;
import Model.Log;

public class Light_ActionListener implements ActionListener{
	Controller_Computer controller_Computer;
	Log log;
	
	public Light_ActionListener (Controller_Computer controllercomputer)
	{controller_Computer = controllercomputer;}
	@Override
	public void actionPerformed(ActionEvent e) {
		//If Else durch Stand vom Light Button oder eben durch Menüpunkt
		controller_Computer.camera_settings.send_switch_light("1");
		log.writelogfile("Light was switched on.");
		
		
		controller_Computer.camera_settings.send_switch_light("0");
		log.writelogfile("Light was switched off.");
		
	}

}
