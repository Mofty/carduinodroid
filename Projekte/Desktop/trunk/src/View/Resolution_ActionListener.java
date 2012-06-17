package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import Controller.*;
import Model.Log;

/** Description of Resolution_ActionListener
*
* @author Benjamin L
* @author Lars
* @version 12.06.2012.
*/
public class Resolution_ActionListener implements ActionListener{
	Controller_Computer controller_Computer;
	Log log;
	JComboBox status;
	
	/** Description of Resolution_ActionListener(Controller_Computer controllercomputer, Log LOG, JComboBox STATUS)
	 * 
	 * @param controllercomputer 	Used instance of Controller_Computer.
	 * @param LOG					Used log. 
	 * @param STATUS				ComboBox including the different Resultions.
	 */
	public Resolution_ActionListener (Controller_Computer controllercomputer, Log LOG, JComboBox STATUS){
		controller_Computer = controllercomputer;
		log = LOG;
		status = STATUS;
	}
	
	/** Description of actionPreformed(ActionEvent e)
	 * 
	 * With the index of a "JComboBox" you can change your resolution of the camera
	 * to certain one. Sometimes it can be helpful to lower your resolution. Maybe
	 * if your connection rate is limited
	 * 
	 * @param e			Event by changing a resolution.
	 */
	public void actionPerformed(ActionEvent e) {
		String resolution = String.valueOf(status.getSelectedIndex());
		log.writelogfile("Sent: Change resolution to " + status.getSelectedItem() + ".");
		controller_Computer.camera_settings.send_change_resolution(resolution);
	}

}
