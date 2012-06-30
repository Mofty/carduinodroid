
package swp.tuilmenau.carduinodroid.controller;
import java.io.IOException;

import android.util.Log;


/**
 *	This class is used to start the connection with the pc-programm.
 * @author Robin
 *
 */
public class Network
{

	
	Socket_Package socket_package;
	Socket_Controller socket_controller;
	Controller_Android controller;
	Thread t1;
	Thread t2;

		
	/**
	 * This is the constructor of the class. It creates a instance of Socket_Controller and Socket_Package
	 * @param n_controller A instance of the Controller_Android
	 */
	public Network(Controller_Android n_controller)
	{		
		controller = n_controller;
		socket_controller = new Socket_Controller(controller);
		Log.e("hauptporg","t1 wird gestartet");
		t1 = new Thread(socket_controller, "socketcontroll"); 
		t1.start();
		socket_package = new Socket_Package(controller, this);
		Log.e("hauptporg","t2 wird gestartet");
		t2 = new Thread(socket_package, "socketpackage"); 
		t2.start();
	}
	


	/**
	 * Transfers the message to the controller
	 * @param message The message
	 * @throws IOException 
	 */
	public void receive_controll(String message) throws IOException {
		controller.receiveData(message);
	}



	public void close() {
		// TODO Auto-generated method stub
		Log.e("network", "connection lost...wait for new connection");
		socket_package.close();
		socket_controller.close();
		controller.cam.close();
	}

	
	
}
