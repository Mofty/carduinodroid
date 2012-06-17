package Controller;

import java.awt.MediaTracker;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Camera_Picture {

	Controller_Computer controller;
	
	public Camera_Picture(Controller_Computer controller) {
		this.controller = controller;
	}

	public void receive_picture(ImageIcon imageIcon) {
		ImageIcon bla = imageIcon;
		System.out.println(bla.getIconHeight()+" " + bla.getIconWidth());
		if(bla.getIconHeight()>0 && bla.getIconWidth() > 0)
		{
			controller.gui_computer.image.setIcon(imageIcon);
		}
	}

}
