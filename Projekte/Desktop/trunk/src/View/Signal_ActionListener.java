package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.Log;

public class Signal_ActionListener implements ActionListener{
	
	Log log;
	String Text;
	
	public Signal_ActionListener (Log LOG, String TEXT)
	{
		log = LOG;
		Text = TEXT;
	}
	
	public void actionPerformed(ActionEvent e) {
		log.writelogfile(Text);
	}
}
