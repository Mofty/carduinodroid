/*
 * NOCH NICHT FERTIG BZW EPRÜFT MUSS NOCH ÜBERARBEITET WERDEN
 */

/*
 *ip wo hin?
 * 
 */
package swp.tuilmenau.carduinodroid.controller;
import java.net.*;
import java.io.*;

public class Network {
	
	Socket_Package socket_package;
	Socket_Controller socket_controller;
	Thread t1;
	Thread t2;
	Controller controller;
		
	public Network(Controller n_controller)
	{
		socket_package = new Socket_Package();
		socket_controller = new Socket_Controller(this);
		controller = n_controller;
	}
	
	/*
	 * ports = ???????
	 */
	

	public boolean sendInfoPackage(String message)
	{
		return socket_package.sendInfoPackage(message);
	}

	public void receive_controll(String message) {
		// TODO Auto-generated method stub
		controller.receiveSteuerdaten(message);
	}


	public void start(){
		socket_package.accept();
		socket_controller.accept();
	}
	
	
}
