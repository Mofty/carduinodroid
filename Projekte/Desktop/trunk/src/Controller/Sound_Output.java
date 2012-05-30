package Controller;

import Model.Log;

public class Sound_Output {
	Network network;
	Log log;
	
	public Sound_Output(Network S_network){
		network = S_network;
	}
	
	public void send_output_soundsignal(String SoundID){
		if (network.send_sound("1"))
		{log.writelogfile("Signal was sent");}
		else{log.writelogfile("Signal wasn't sent");}
	}
}
