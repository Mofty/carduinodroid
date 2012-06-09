package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.Controller_Computer;
import Model.Log;

public class Direction_ActionListener implements ActionListener{
	Controller_Computer controller_Computer;
	Log log;
	
	public Direction_ActionListener (Controller_Computer controllercomputer, Log LOG){
		controller_Computer = controllercomputer;
		log = LOG;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
