package Controller;

public class Soundrecording {
	Controller_Computer controller_computer;
	
	public Soundrecording(Controller_Computer ControllerComputer) {
		controller_computer = ControllerComputer;
	}

	public void send_start_recording(){
		if (controller_computer.network.send_sound("2;1"))
		{controller_computer.log.writelogfile("Recording has started");}
		else{controller_computer.log.writelogfile("Recording signal wasn't sent");}
	}
	
	public void send_stop_recording(){
		if (controller_computer.network.send_sound("2;0"))
		{controller_computer.log.writelogfile("Redording has stopped");}
		else{controller_computer.log.writelogfile("Recording signal wasn't sent");}
	}
}
