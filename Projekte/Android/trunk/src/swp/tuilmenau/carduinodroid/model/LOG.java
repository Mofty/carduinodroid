package swp.tuilmenau.carduinodroid.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;
import android.text.format.Time;

public class LOG
{	
	public static final int LOG_ALL = 1;
	public static final int LOG_WARNINGS_ONLY = 2;
	public static final int INFO = 3;
	public static final int WARNING = 4;
	private final String logpath = Environment.getExternalStorageDirectory().getPath()+"/carduinodroid/log";

	private Time time;
	private File path;
	private File file;
	private BufferedWriter buffwrite;
	private int logLevel;

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

		write(LOG.INFO, "App gestartet");
	}

	public synchronized void write(int type, String line) 
	{
		String timestr;
		time.setToNow(); // aktualisiert die in "time" gespeicherte zeit
		timestr = time.hour+":"+time.minute+":"+time.second+" ";

		// schreibt die zeit gefolgt vom übergebenen String "line" in die datei und springt zu nächsten zeile Leerzeile.
		try {
			if (type == INFO)
			{
				if (logLevel == LOG_ALL)
				{
					buffwrite.write(timestr,0,timestr.length());
					buffwrite.write("[INFO] ",0,7);
					buffwrite.write(line,0,line.length());
					buffwrite.newLine();
					buffwrite.flush();
				}
			}
			if (type == WARNING)
			{	
				buffwrite.write(timestr,0,timestr.length());
				buffwrite.write("[WARNING] ",0,10);
				buffwrite.write(line,0,line.length());
				buffwrite.newLine();
				buffwrite.flush();
			}
		} catch (IOException e) { }
	}

	public void setLevel(int lvl)
	{
		logLevel = lvl;
	}

	public void save() 
	{
		write(LOG.INFO, "App beendet");
		// speichert und schliesst die datei
		try {
			buffwrite.flush();
			buffwrite.close();
		} catch (IOException e) { }	
	}
}