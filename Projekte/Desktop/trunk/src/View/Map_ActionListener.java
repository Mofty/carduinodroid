package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import Controller.*;

public class Map_ActionListener implements ActionListener{
	Controller_Computer controller_Computer;
	JLabel latitude;
	JLabel longitude;
	
	public Map_ActionListener (Controller_Computer controllercomputer, JLabel LATITUDE, JLabel LONGITUDE){
		controller_Computer = controllercomputer;
		latitude = LATITUDE;
		longitude = LONGITUDE;
	}
	
	public void actionPerformed(ActionEvent e) {
		controller_Computer.gps_map.open_map(latitude.getText(), longitude.getText());
	}

}