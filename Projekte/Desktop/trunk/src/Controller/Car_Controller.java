package Controller;

import java.util.Timer;
import java.util.TimerTask;

public class Car_Controller {
	Controller_Computer controller_computer;
	
	boolean RunTimer = false, up = false, down = false, right = false, left = false;
	Timer controlsignal = new Timer();
	
	TimerTask ControlTask = new TimerTask(){
	  public void run() {
		  if(RunTimer){
			  int Speed = controller_computer.gui_computer.speed_slider.getValue();
			  int Dir = controller_computer.gui_computer.angle_slider.getValue();
			  send_controlsignal(SpeedCalculation(Speed),DirectionCalculation(Dir));}
	  }
	};
	
	public Car_Controller(Controller_Computer ControllerComputer){
		controller_computer = ControllerComputer;
		controlsignal.schedule(ControlTask, 0, 100);
	}
	
	private void send_controlsignal(int speed,int dir){		
		if (controller_computer.network.send_controllsignal(speed+";"+dir))
		 feedback_output(speed,dir);
	}
	
	private void feedback_output(int speed,int dir){
		//Hier fehlt text	
	}
	
	public void StartTimer(boolean Up, boolean Down, boolean Right, boolean Left){
		up = Up; down = Down; right = Right; left = Left;
		RunTimer = true;
	}
	
	public void StopTimer(boolean Up, boolean Down, boolean Right, boolean Left){
		up = Up; down = Down; right = Right; left = Left;
		RunTimer = false;
	}
	
	private int SpeedCalculation(int speed)
	{	//Hier fehlt noch die Anpassung an die Übergabewerte zu Arduino
		return speed;
	}
	
	private int DirectionCalculation(int dir)
	{	//Hier fehlt noch die Anpassung an die Übergabewerte zu Arduino
		return dir;
	}
	
}
