package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.Controller_Computer;

public class Signal_ActionListener implements ActionListener{
	
	Sound_Output test;
	
	public Signal_ActionListener ()
	{}
	
	public void actionPerformed(ActionEvent e) {
		test.send_output_soundsignal("1");
	}
}
