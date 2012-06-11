package View;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JSlider;

public class Speed_KeyListener implements KeyListener{
	JSlider speed;

	public Speed_KeyListener(){
	}
	
	@Override
	public void keyPressed(KeyEvent e) {	
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_1) System.out.println("1");
		if (key == KeyEvent.VK_2) System.out.println("2");
		if (key == KeyEvent.VK_3) System.out.println("3");
		if (key == KeyEvent.VK_4) System.out.println("4");
		if (key == KeyEvent.VK_5) System.out.println("5");
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}

