package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import Controller.*;
import Model.Log;

/** Description of Light_ActionListener
*
* @author Benjamin L
* @author Lars
* @version 06.06.2012.
*/
public class Light_ActionListener implements ActionListener{
	Controller_Computer controller_Computer;
	Log log;
	JToggleButton status;
	
	/** Description of Light_ActionListener(Contoller_Computer controllercomputer, Log LOG, JToggleButton STATUS) 
	 * 
	 * @param controllercomputer	Used instance of Controller_Computer.
	 * @param LOG					Used log.
	 * @param STATUS					ToggleButton for switching light on/off.
	 */
	public Light_ActionListener (Controller_Computer controllercomputer, Log LOG, JToggleButton STATUS)
	{
		controller_Computer = controllercomputer;
		log = LOG;
		status = STATUS;
	}
	
	/** Description of actionPreformed(ActionEvent e)
	 * @param e			Event by switching the status of ToggleButton for light.
	 */
	public void actionPerformed(ActionEvent e) {
		
		if (status.isSelected()){
			log.writelogfile("Sent: Switch camera light on.");
			controller_Computer.camera_settings.send_switch_light("1");
		}
		else{
			log.writelogfile("Sent: Switch camera light off.");
			controller_Computer.camera_settings.send_switch_light("0");
		}
		
	}

}
