package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.*;

public class Signal_ActionListener implements ActionListener{
	
	Sound_Output sound_output;
	
	public Signal_ActionListener (Sound_Output SoundOutput)
	{sound_output = SoundOutput;}
	
	public void actionPerformed(ActionEvent e) {
		sound_output.send_output_soundsignal("1");
	}
}
