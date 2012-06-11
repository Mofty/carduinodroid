package View;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JSlider;

import Controller.Controller_Computer;

public class Speed_KeyListener implements KeyListener{
	GUI_Computer gui_computer;
	
	public Speed_KeyListener(GUI_Computer GUI_computer){
		gui_computer = GUI_computer;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {	
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_1) gui_computer.speed_slider.setValue(20);
		if (key == KeyEvent.VK_2) gui_computer.speed_slider.setValue(40);
		if (key == KeyEvent.VK_3) gui_computer.speed_slider.setValue(60);
		if (key == KeyEvent.VK_4) gui_computer.speed_slider.setValue(80);
		if (key == KeyEvent.VK_5) gui_computer.speed_slider.setValue(100);
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}

