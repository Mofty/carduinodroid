package Controller;

public class Camera_Settings {
	Controller_Computer controller_computer;

	public Camera_Settings(Controller_Computer ControllerComputer) {
		controller_computer = ControllerComputer;
	}

	public void send_change_camera(String cameratype) {
		if (controller_computer.network.send_camera_settings("1;" + cameratype)) {
			controller_computer.log.writelogfile("Camera was switched");
		} else {
			controller_computer.log.writelogfile("Camera wasn't switched");
		}
	}

	public void send_change_resolution(String setting) {
		if (controller_computer.network.send_camera_settings("2;" + setting)) {
			controller_computer.log.writelogfile("Camera settings were sent");
		} else {
			controller_computer.log
					.writelogfile("Camera settings weren't sent");
		}
	}

	public void send_switch_light(String cameralight) {
		if (controller_computer.network.send_camera_settings("3;" + cameralight)) {
			controller_computer.log.writelogfile("Camera light switched");
		} else {
			controller_computer.log.writelogfile("Camera light wasn't switched");
		}
	}

}
