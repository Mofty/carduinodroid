package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.*;

public class Map_ActionListener implements ActionListener{
	Controller_Computer controller_Computer;
	
	public Map_ActionListener (Controller_Computer controllercomputer)
	{controller_Computer = controllercomputer;}
	
	public void actionPerformed(ActionEvent e) {
		controller_Computer.gps_map.open_map("15,2", "15,2");
	}

}
