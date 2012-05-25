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
	
	public LOG() {
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
	// erstellt den BufferedWriter zum schreiben von strings in die datei
	try {
		buffwrite = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	

	public void write(String line) {
		String timestr;
		time.setToNow(); // aktualisiert die in "time" gespeicherte zeit
		timestr = time.hour+":"+time.minute+":"+time.second+" ";
		
		// schreibt die zeit gefolgt vom �bergebenen String "line" in die datei und springt zu n�chsten zeile Leerzeile.
		try {
			buffwrite.write(timestr,0,timestr.length());
			buffwrite.write(line,0,line.length());
			buffwrite.newLine();
			buffwrite.flush();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	
	public void save() {
		// speichert und schliesst die datei
		try {
			buffwrite.flush();
			buffwrite.close();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}	
	}
}