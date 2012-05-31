package Controller;

public class Car_Controller {
	Controller_Computer controller_computer;
	
	public Car_Controller(Controller_Computer ControllerComputer) {
		controller_computer = ControllerComputer;
	}
	
	public void send_controllsignal(int speed,int dir){
		
		
		if (controller_computer.network.send_controllsignal(""+""))
		{controller_computer.log.writelogfile("Control signal was sent");}
		else{controller_computer.log.writelogfile("Control signal wasn't sent");}
	}
	
	public void feedback_output(){
			
	}
}
