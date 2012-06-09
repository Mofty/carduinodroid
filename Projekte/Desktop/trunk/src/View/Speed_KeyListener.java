package View;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JSlider;

public class Speed_KeyListener implements KeyListener{
	JSlider speed;

	public Speed_KeyListener(JSlider SPEED){
		speed = SPEED;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {	
		System.out.println("gedrückt");
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_1) speed.setValue(20);
		if (key == KeyEvent.VK_2){
			speed.setValue(40);
			System.out.println("gedrückt");
		}
		if (key == KeyEvent.VK_3) ;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("gedrückt");

	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("gedrückt");

	}
}

