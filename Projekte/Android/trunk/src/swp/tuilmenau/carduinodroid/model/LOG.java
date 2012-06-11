package swp.tuilmenau.carduinodroid.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;
import android.text.format.Time;

public class LOG
{	
	public final int LOG_ALL = 1;
	public final int LOG_WARNINGS_ONLY = 2;
	
	Time time;
	File path;
	File file;
	BufferedWriter buffwrite;
	int logLevel;

	String logpath = Environment.getExternalStorageDirectory().getPath()+"/carduinodroid/log";
	
	public LOG() 
	{
		// ruft datum und zeit ab	
		time = new Time();
		time.setToNow();
		// erstellt datei mit schreibrechten
		path = new File(logpath);
		path.mkdirs();
		file = new File(logpath,"LOG_"+(time.month+1)+time.monthDay+"_"+time.hour+time.minute+time.second+".txt");
		try {
				file.createNewFile();
			} catch (IOException e) { }
		file.canWrite();
		file.canRead();
		// erstellt den BufferedWriter zum schreiben von strings in die datei
		try {
				buffwrite = new BufferedWriter(new FileWriter(file));
			} catch (IOException e) { }
		
		write("App gestartet");
	}
	
	public synchronized void write(String line) 
	{
		String timestr;
		time.setToNow(); // aktualisiert die in "time" gespeicherte zeit
		timestr = time.hour+":"+time.minute+":"+time.second+" ";
		
		// schreibt die zeit gefolgt vom übergebenen String "line" in die datei und springt zu nächsten zeile Leerzeile.
		try {
				buffwrite.write(timestr,0,timestr.length());
				buffwrite.write(line,0,line.length());
				buffwrite.newLine();
				buffwrite.flush();
			} catch (IOException e) { }
	}
	
	public void setLevel(int lvl)
	{
		
	}
	
	public void save() 
	{
		write("App beendet");
		// speichert und schliesst die datei
		try {
				buffwrite.flush();
				buffwrite.close();
			} catch (IOException e) { }	
	}
}