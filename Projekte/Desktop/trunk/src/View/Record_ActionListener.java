package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import Controller.*;
import Model.Log;

/** Description of Record_ActionListener
*
* @author Benjamin L
* @author Lars
* @version 06.06.2012.
*/
public class Record_ActionListener implements ActionListener{
	Controller_Computer controller_Computer;
	Log log;
	JToggleButton status;
	
	/** Description of Record_ActionListener(Controller_Computer controllercomputer, Log LOG, JToggleButton STATUS) 
	 * 
	 * @param controllercomputer	used instance of Controller_Computer.
	 * @param LOG					Used log. 
	 * @param STATUS				Record ToggleButton. 
	 */
	public Record_ActionListener (Controller_Computer controllercomputer, Log LOG, JToggleButton STATUS){
		controller_Computer = controllercomputer;
		log = LOG;
		status = STATUS;
	}
	
	/** Description of actionPreformed(ActionEvent e)
	 * @param e			Event by pressing the record ToggleButton.
	 */
	public void actionPerformed(ActionEvent e) {
		if (status.isSelected()){
			log.writelogfile("Sent: Start sound recording.");
			controller_Computer.soundrecording.send_start_recording();
		}
		else{
			log.writelogfile("Sent: Stop sound recording.");
			controller_Computer.soundrecording.send_stop_recording();
		}		
	}
}
