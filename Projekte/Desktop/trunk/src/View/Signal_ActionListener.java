package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.*;

public class Signal_ActionListener implements ActionListener{
	
	Sound_Output SoundOutput;
	
	public Signal_ActionListener ()
	{}
	
	public void actionPerformed(ActionEvent e) {
		SoundOutput.send_output_soundsignal("1");
	}
}
