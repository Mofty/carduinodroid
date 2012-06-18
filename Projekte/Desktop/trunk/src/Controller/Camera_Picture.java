package Controller;

import java.awt.MediaTracker;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * @author Robin
 * This class is used to update the Camera pictures on the GUI
 * 
 */
public class Camera_Picture {

	Controller_Computer controller;
	
	public Camera_Picture(Controller_Computer controller) {
		this.controller = controller;
	}
	
	/**
	 * This method updates the Image
	 * @param imageIcon
	 */
	public void receive_picture(ImageIcon imageIcon) {
		ImageIcon bla = imageIcon;
		System.out.println(bla.getIconHeight()+" " + bla.getIconWidth());
		if(bla.getIconHeight()>0 && bla.getIconWidth() > 0)
		{
			controller.gui_computer.image.setIcon(imageIcon);
		}
	}

}
