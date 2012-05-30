/*
 * NOCH NICHT FERTIG BZW EPRÜFT MUSS NOCH ÜBERARBEITET WERDEN
 */

/*
 *ip wo hin?
 * 
 */
package swp.tuilmenau.carduinodroid.controller;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class Network{
	
	Socket_Package socket_package;
	Socket_Controller socket_controller;
	Controller_Android controller;
	Handler handler;
	Handler handlerpackage;
	Thread t1;
		
	public Network(Controller_Android n_controller)
	{
		//socket_package = new Socket_Package(n_handler);
		
		controller = n_controller;
		handler = new Handler(){
			public void handleMessage(Message msg){
				switch(msg.arg1)
				{
				case 0:
					Log.v("hauptporg","message received"+ msg.obj);
					receive_controll((String) msg.obj);
					break;
				case 1:
					handlerpackage = (Handler) msg.obj;
					break;
				case 2:
					Log.v("hauptporg","controllerfehler"+ msg.obj);

					controller.log.write("Controllerfehler" + (String)msg.obj);
					break;
				case 3:
					controller.log.write("Packagefehler" + (String)msg.obj);

				}
			}
		};
		socket_controller = new Socket_Controller(handler);
		Log.v("hauptporg","t1 wird gestartet");
		t1 = new Thread(socket_controller, "socketcontroll"); 
		t1.start();
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

	
	
}
