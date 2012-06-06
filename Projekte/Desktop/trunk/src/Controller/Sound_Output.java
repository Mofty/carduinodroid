package Controller;

public class Sound_Output {
	Controller_Computer controller_computer;
	
	public Sound_Output(Controller_Computer ControllerComputer){
		controller_computer = ControllerComputer;
	}
	
	public void send_output_soundsignal(String SoundID){
		if (controller_computer.network.send_sound("1;"+SoundID))
		{controller_computer.log.writelogfile("Sound signal was sent");}
		else{controller_computer.log.writelogfile("Sound signal wasn't sent");}
	}
}
