package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.*;

/** Methods for working with signal button.
*
* @author Benjamin L
* @author Lars
* @version 11.06.2012.
*/
public class Signal_ActionListener implements ActionListener{
	
	Controller_Computer controller_Computer;
	
	/** 
	 * @param controllercomputer	Used instance of Controller_Computer.
	 */
	public Signal_ActionListener (Controller_Computer controllercomputer)
	{
		controller_Computer = controllercomputer;
	}
	
	/** 
	 * With the help of this event you would be able to signal someone
	 * that your car is on the way or maybe pay attention to it. Later 
	 * you could change the number of the sound and get something like 
	 * a bark, a frog or applause.
	 * 
	 * @param e			Event by pressing the signal button.
	 */
	public void actionPerformed(ActionEvent e) {
		controller_Computer.sound_output.send_output_soundsignal("1");
		controller_Computer.packagedata.receive_package("2;640x852;52x5720;572x67307;76622x57");
	}
}
