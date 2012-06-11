package View;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Direction_KeyListener implements KeyListener{
	GUI_Computer gui_computer;
	boolean up = false, left = false, right = false, down = false;
	
	public Direction_KeyListener(GUI_Computer GUI_computer){
		gui_computer = GUI_computer;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {	
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP) {up=true;System.out.println(up+","+right+","+down+","+left);};
		if (key == KeyEvent.VK_RIGHT) {right=true;System.out.println(up+","+right+","+down+","+left);};
		if (key == KeyEvent.VK_LEFT) {left=true;System.out.println(up+","+right+","+down+","+left);};
		if (key == KeyEvent.VK_DOWN) {down=true;System.out.println(up+","+right+","+down+","+left);};
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP) {up=false;};
		if (key == KeyEvent.VK_RIGHT) {right=false;};
		if (key == KeyEvent.VK_LEFT) {left=false;};
		if (key == KeyEvent.VK_DOWN) {down=false;};
		if (key == KeyEvent.VK_1) {gui_computer.speed_slider.setValue(20);}
		if (key == KeyEvent.VK_2) {gui_computer.speed_slider.setValue(40);}
		if (key == KeyEvent.VK_3) {gui_computer.speed_slider.setValue(60);}
		if (key == KeyEvent.VK_4) {gui_computer.speed_slider.setValue(80);}
		if (key == KeyEvent.VK_5) {gui_computer.speed_slider.setValue(100);}
		if (key == KeyEvent.VK_W) {gui_computer.angle_slider.setValue(gui_computer.angle_slider.getValue()+10);}
		if (key == KeyEvent.VK_Q) {gui_computer.angle_slider.setValue(gui_computer.angle_slider.getValue()-10);}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}


