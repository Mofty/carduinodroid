package fassade.android;

public class Controller_Android {
	
	Arduino arduino;
	Connection connection;
	GPS gps;
	Kamera kamera;
	Network network;
	RecordSound recordsound;
	Log_Android log_android;
	Sound sound;
	Controller_Android controller_android;
	int framerate;
	
	public Controller_Android() {
		
		arduino = new Arduino();
		connection = new Connection();
		gps = new GPS();
		kamera = new Kamera();
		network = new Network(controller_android);
		recordsound = new RecordSound();
		log_android = new Log_Android();
		sound = new Sound();
	}
	
	public void SendData() {
	}
	public void receiveSteuerdaten(String Steuerdaten) {
	}
	public void kamerabild() {
	}	
}