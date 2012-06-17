package Controller;

import java.util.Timer;
import java.util.TimerTask;

/**
* methods for control signals
* @author Lars Vogel
* @version 12.06.2012
*/

public class Car_Controller {
	Controller_Computer controller_computer;
	
	//initiate all used variables
	int delay=0;
	boolean RunTimer = false, up = false, down = false, right = false, left = false;
	
	//constructor of the timer
	Timer controlsignal = new Timer();
	
	//constructor of the timer task
	TimerTask ControlTask = new TimerTask(){
		public void run() {
			//If someone pushes the up or down key, the Controlsignal will be send
			if((!up&&down)||(up&&!down)){
				int Speed = controller_computer.gui_computer.speed_slider.getValue();
				int Dir = controller_computer.gui_computer.angle_slider.getValue();
				send_controlsignal(SpeedCalculation(Speed),DirectionCalculation(Dir));	
			}
			//With a 200ms period the Buttons will be released, if they are not any longer pushed.
			delay++;
			if(delay==2){
				if(!up){controller_computer.gui_computer.UnpressedBorderUp();}
				if(!down){controller_computer.gui_computer.UnpressedBorderDown();}
				if(!right||!(up||down)){controller_computer.gui_computer.UnpressedBorderRight();}
				if(!left||!(up||down)){controller_computer.gui_computer.UnpressedBorderLeft();}
				delay=0;
			}
		}
	};
	
	// ***** Car_Controller Konstruktor ***************************************
	/** 
	 * Needs the Controller_Computer to get access to the log and starts the 
	 * timer without any delay and a 100ms period.
	 */
	public Car_Controller(Controller_Computer ControllerComputer){
		controller_computer = ControllerComputer;
		controlsignal.scheduleAtFixedRate(ControlTask, 0, 100);
	}
	
	// ***** Send Control signal ***************************************
	/** 
	 * Control Signal is the method for all direction commands. It has 2
	 * variables which are already calculated and provides a feedback if 
	 * the sending was successful.
	 */
	private void send_controlsignal(int speed,int dir){		
		if (controller_computer.network.send_controllsignal(speed+";"+dir))
		 feedback_output(speed,dir);
		else{ feedback_output(speed,dir);/*Testweise wegen fehlender Verbindung*/}
	}
	
	// ***** Feedback Output ***************************************
				/** 
				 * It is the method to show which button is pressed.
				 * The feedback works with the timer to check all 200ms
				 * if the button is still pressed.
				 */
	private void feedback_output(int speed,int dir){
		if(up){controller_computer.gui_computer.PressedBorderUp();}
		if(down){controller_computer.gui_computer.PressedBorderDown();}
		if(right){controller_computer.gui_computer.PressedBorderRight();}
		if(left){controller_computer.gui_computer.PressedBorderLeft();}
	}	
	
	// ***** Update Variables ***************************************
			/** 
			 * The timer needs the current settings of the keys you are 
			 * pressing. Without variables you won't be able to see what
			 * is still pressed or not.
			 */
	public void UpdateVariables(boolean Up, boolean Down, boolean Right, boolean Left){
		up = Up; down = Down; right = Right; left = Left;
	}
	
	// ***** Speed Calculation ***************************************
		/** 
		 * It is a method to calculate the speed for a radio controlled
		 * car and its motor. Different types often need a different range
		 * of voltage.
		 */
	private int SpeedCalculation(int speed)
	{	//Hier fehlt noch die Anpassung an die Übergabewerte zu Arduino
		return speed;
	}
	
	// ***** Direction Calculation ***************************************
			/** 
			 * It is a method to calculate the direction for a radio controlled
			 * car and its motor. Different types often need a different range
			 * of voltage.
			 */
	private int DirectionCalculation(int dir)
	{	//Hier fehlt noch die Anpassung an die Übergabewerte zu Arduino
		return dir;
	}
	
}
