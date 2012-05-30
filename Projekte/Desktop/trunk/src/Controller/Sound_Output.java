package Controller;

import Model.Log;

public class Sound_Output {
	Controller_Computer controller_computer;
	
	public Sound_Output(Controller_Computer ControllerComputer){
		controller_computer = ControllerComputer;
	}
	
	public void send_output_soundsignal(String SoundID){
		if (controller_computer.network.send_sound("1"))
		{controller_computer.log.writelogfile("Signal was sent");}
		else{controller_computer.log.writelogfile("Signal wasn't sent");}
	}
}
