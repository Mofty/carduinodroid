package com.swp.tuillmenau.carduinodroid;


import java.io.*;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.Time;
import java.lang.String;

public class Log_Android_TESTActivity extends Activity {
	LOG log;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        log = new LOG();
        log.write_log("test");
        log.save_log();
    }
     
}

class LOG{
	
	Time time;
	File file;
	FileWriter filewriter;
	String logpath ="sdcard/carduinodroid/log";
	
	LOG() {
	// ruft datum und zeit ab	
	time = new Time();
	// erstellt datei mit schreibrechten
	file = new File(logpath,"LOG_"+time.month+time.monthDay+"_"+time.hour+time.minute);
	try {
		file.createNewFile();
		} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
	file.canWrite();
	// erstellt den FileWriter zum schreiben von strings in die datei
	try {
		filewriter = new FileWriter(file, true);
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	

	void write_log(String line) {
		try {
			filewriter.write(line);
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	
	void save_log() {
		try {
			filewriter.flush();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		try {
			filewriter.close();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	
}