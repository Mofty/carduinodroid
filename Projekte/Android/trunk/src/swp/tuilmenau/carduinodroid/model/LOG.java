package swp.tuilmenau.carduinodroid.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import android.text.format.Time;

public class LOG{
	
	Time time;
	File path;
	File file;
	BufferedWriter buffwrite;
	String logpath ="/sdcard/carduinodroid/log";
	
	LOG() {
	// ruft datum und zeit ab	
	time = new Time();
	time.setToNow();
	// erstellt datei mit schreibrechten
	path = new File(logpath);
	path.mkdirs();
	file = new File(logpath,"LOG_"+time.month+time.monthDay+"_"+time.hour+time.minute+".txt");
	try {
		file.createNewFile();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	file.canWrite();
	file.canRead();
	// erstellt den FileWriter zum schreiben von strings in die datei
	try {
		buffwrite = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	

	void write_log(String line) {
		try {
			buffwrite.write(line,0,line.length());
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		try {
			buffwrite.newLine();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	
	void save_log() {
		try {
			buffwrite.flush();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		try {
			buffwrite.close();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	
}