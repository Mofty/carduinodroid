package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.*;

public class Signal_ActionListener implements ActionListener{
	
	Controller_Computer controller_Computer;
	
	
	public Signal_ActionListener (Controller_Computer controllercomputer)
	{controller_Computer = controllercomputer;}
	
	public void actionPerformed(ActionEvent e) {
		controller_Computer.sound_output.send_output_soundsignal("1");
		controller_Computer.packagedata.updateInformationbox();
	}
}
