package swp.tuilmenau.carduinodroid.controller;


import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;

public class NetworkTestActivity extends Activity {
    /** Called when the activity is first created. */
	Controller_Android controller_Android;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        controller_Android = new Controller_Android(this);
        
        // in service onCreate ewinfügen
        new Thread(new Runnable() {
            public void run() {
                Looper.prepare();
             Network network = new Network(controller_Android);
                }
          }).start();
    }
}