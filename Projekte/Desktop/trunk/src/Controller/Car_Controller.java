package Controller;

import java.util.Timer;
import java.util.TimerTask;

public class Car_Controller {
	Controller_Computer controller_computer;
	
	boolean RunTimer = false;
	Timer timer = new Timer();
	
	TimerTask timerTask = new TimerTask(){
	  public void run() {
		  if(RunTimer){
		  send_controlsignal(20,40);}
	  }
	};
	
	public Car_Controller(Controller_Computer ControllerComputer){
		controller_computer = ControllerComputer;
		timer.schedule(timerTask, 0, 100);
	}
	
	public void send_controlsignal(int speed,int dir){		
		if (controller_computer.network.send_controllsignal(speed+";"+dir))
		{controller_computer.log.writelogfile("Control signal was sent");
		 feedback_output(speed,dir); }
		else{controller_computer.log.writelogfile("Control signal wasn't sent");}
	}
	
	public void feedback_output(int speed,int dir){
			
	}
	
	public void StartTimer(){
		RunTimer = true;
	}
	
	public void StopTimer(){
		RunTimer =false;
	}
	
}
