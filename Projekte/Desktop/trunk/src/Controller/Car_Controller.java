package Controller;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

public class Car_Controller {
	Controller_Computer controller_computer;
	
	int delay=0;
	boolean RunTimer = false, up = false, down = false, right = false, left = false;
	Timer controlsignal = new Timer();
	Timer resetButton = new Timer();
	
	TimerTask ControlTask = new TimerTask(){
		public void run() {
			if(RunTimer){
				int Speed = controller_computer.gui_computer.speed_slider.getValue();
				int Dir = controller_computer.gui_computer.angle_slider.getValue();
				send_controlsignal(SpeedCalculation(Speed),DirectionCalculation(Dir));	
			}
			delay++;
			System.out.println(delay);
			if(delay==2){
				if(!up){controller_computer.gui_computer.UnpressedBorderUp();}
				if(!down){controller_computer.gui_computer.UnpressedBorderDown();}
				if(!right){controller_computer.gui_computer.UnpressedBorderRight();}
				if(!left){controller_computer.gui_computer.UnpressedBorderLeft();}
				delay=0;
			}
		}
	};
	
	TimerTask ResetTask = new TimerTask(){
		public void run() {
			if(!up){controller_computer.gui_computer.UnpressedBorderUp();}
			if(!down){controller_computer.gui_computer.UnpressedBorderDown();}
			if(!right){controller_computer.gui_computer.UnpressedBorderRight();}
			if(!left){controller_computer.gui_computer.UnpressedBorderLeft();}
		}
	};
	
	public Car_Controller(Controller_Computer ControllerComputer){
		controller_computer = ControllerComputer;
		controlsignal.scheduleAtFixedRate(ControlTask, 0, 100);
	}
	
	private void send_controlsignal(int speed,int dir){		
		if (controller_computer.network.send_controllsignal(speed+";"+dir))
		 feedback_output(speed,dir);
		else{ feedback_output(speed,dir);/*Testweise wegen fehlender Verbindung*/}
	}
	
	private void feedback_output(int speed,int dir){
		if(up){controller_computer.gui_computer.PressedBorderUp();}
		if(down){controller_computer.gui_computer.PressedBorderDown();}
		if(right){controller_computer.gui_computer.PressedBorderRight();}
		if(left){controller_computer.gui_computer.PressedBorderLeft();}
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
