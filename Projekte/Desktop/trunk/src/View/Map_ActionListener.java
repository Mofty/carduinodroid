package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import Controller.*;

/** Description of Map_ActionListener
*
* @author Benjamin L
* @author Lars
* @version 09.06.2012.
*/
public class Map_ActionListener implements ActionListener{
	Controller_Computer controller_Computer;
	JLabel latitude;
	JLabel longitude;
	
	/** Description of Map_ActionListener(Contoller_Computer controllercomputer, JLabel LATITUDE, JLabel LONGITUDE) 
	 * 
	 * @param controllercomputer	Used instance of Controller_Computer.
	 * @param LATITUDE				Latitude of actual position of the car.
	 * @param LONGITUDE				LONGITUDE of actual position of the car. 
	 */
	public Map_ActionListener (Controller_Computer controllercomputer, JLabel LATITUDE, JLabel LONGITUDE){
		controller_Computer = controllercomputer;
		latitude = LATITUDE;
		longitude = LONGITUDE;
	}
	
	/** Description of actionPreformed(ActionEvent e)
	 * 
	 * This method will open the default browser of your computer
	 * and show the coordinates on google maps.
	 * 
	 * @param e			Event by pressing the map button.
	 */
	public void actionPerformed(ActionEvent e) {
		controller_Computer.gps_map.open_map(latitude.getText(), longitude.getText());
	}

}