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
	file = new File(logpath,"LOG_"+time.month+time.monthDay+"_"+time.hour+time.minute+time.second+".txt");
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
		String timestr;
		time.setToNow(); // aktualisiert die in "time" gespeicherte zeit
		timestr = time.hour+":"+time.minute+":"+time.second+" ";
	
		try {// schreibt die zeit gefolgt vom übergebenen String "line in die datei ung fügt eine Leerzeile an.
			buffwrite.write(timestr,0,timestr.length());
			buffwrite.write(line,0,line.length());
			buffwrite.newLine();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	
	void save_log() {
		try {
			buffwrite.flush();
			buffwrite.close();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}	
	}
}