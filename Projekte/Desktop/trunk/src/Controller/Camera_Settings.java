package Controller;

/**
* methods for sending camera changes
* @author Lars Vogel
* @version 12.06.2012
*/

public class Camera_Settings {
	Controller_Computer controller_computer;
	
	public Camera_Settings(Controller_Computer ControllerComputer) {
		controller_computer = ControllerComputer;
	}
	
	// ***** Change Camera Type ***************************************
	/** 
	 * @param cameratype Knows if it is the front or back camera
	 * 
	 * With this method you will be able to change the camera type
	 * between front and back. It is sending a typical signal to the
	 * Android device which will start the "Change command".
	 */
	public void send_change_camera(String cameratype) {
		if (controller_computer.network.send_camera_settings("1;" + cameratype)) {
			controller_computer.log.writelogfile("Camera was switched");
		} else {
			controller_computer.log.writelogfile("Camera wasn't switched");
		}
	}
	
	// ***** Change Camera Resolution ***************************************
		/** 
		 * @param setting Save the chosen Resolution by itemindex
		 * 
		 * The second important part is to change the resolution of 
		 * your camera. This is important for lowering your traffic and
		 * maybe your built-in camera is not able to send in the chosen
		 * resolution.
		 */
	public void send_change_resolution(String setting) {
		if (controller_computer.network.send_camera_settings("2;" + setting)) {
			controller_computer.log.writelogfile("Resolution settings were sent");
		} else {
			controller_computer.log.writelogfile("Resolution settings weren't sent");
		}
	}
	
	// ***** Change Camera Light ***************************************
			/** 
			 * @param cameralight Variable for turning light on or off
			 * 
			 * At a dark night or twilight u need to switch on a light to 
			 * see what is in front of your car. Sometimes it can be helpful
			 * to have an enlightened place.
			 */
	public void send_switch_light(String cameralight) { // cameralight 1 = licht an 0 = licht aus ? Lars: Lesen bevor fragen - Schau in Light_ActionListener!
		if (controller_computer.network.send_camera_settings("3;" + cameralight)) {
			controller_computer.log.writelogfile("Camera light was switched");
		} else {
			controller_computer.log.writelogfile("Camera light wasn't switched");
		}
	}

}
