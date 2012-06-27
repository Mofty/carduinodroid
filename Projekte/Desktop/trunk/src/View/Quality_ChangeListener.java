package View;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import Model.Log;

public class Quality_ChangeListener implements ChangeListener{
	JSlider quality_slider;
	Log log;
	
	public Quality_ChangeListener(JSlider QUALITY_SLIDER, Log LOG){
		quality_slider = QUALITY_SLIDER;
		log = LOG;
	}

	public void stateChanged(ChangeEvent arg0) {
		log.writelogfile("Sent: Change quality to " + String.valueOf(quality_slider.getValue()) + ".");
	}
}

