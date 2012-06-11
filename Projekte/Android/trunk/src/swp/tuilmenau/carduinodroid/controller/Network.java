/*
 * NOCH NICHT FERTIG BZW EPRÜFT MUSS NOCH ÜBERARBEITET WERDEN
 */

/*
 *ip wo hin?
 * 
 */
package swp.tuilmenau.carduinodroid.controller;
import android.util.Log;


public class Network
{

	
	Socket_Package socket_package;
	Socket_Controller socket_controller;
	Controller_Android controller;
	Thread t1;
	Thread t2;

		
	public Network(Controller_Android n_controller)
	{		
		controller = n_controller;
		socket_controller = new Socket_Controller(controller);
		Log.v("hauptporg","t1 wird gestartet");
		t1 = new Thread(socket_controller, "socketcontroll"); 
		t1.start();
		socket_package = new Socket_Package(controller);
		Log.v("hauptporg","t2 wird gestartet");
		t2 = new Thread(socket_package, "socketpackage"); 
		t2.start();
	}
	
	/*
	 * ports = ???????
	 */
	


	public void receive_controll(String message) {
		// TODO Auto-generated method stub
		controller.receiveData(message);
	}

	
	
}
