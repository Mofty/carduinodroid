package swp.tuilmenau.carduinodroid.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;
import android.text.format.Time;

/**
 * Provides the global LOG-functionality used by all other subclasses.
 * 
 * @author Paul Thorwirth
 * @version 1.0
 * @see File
 * @see FileWriter
 * @see BufferedWriter
 */
public class LOG
{	
	public static final int LOG_ALL = 1;
	public static final int LOG_WARNINGS_ONLY = 2;
	public static final int INFO = 3;
	public static final int WARNING = 4;
	private final String LOGPATH = Environment.getExternalStorageDirectory().getPath()+"/carduinodroid/log";

	private Time time;
	private File path;
	private File file;
	private BufferedWriter buffwrite;
	private int logLevel;

	/**
	 * Creates the directories and the file required for logging.
	 */
	public LOG() 
	{
		logLevel = LOG_ALL; 
		time = new Time();
		time.setToNow();
		path = new File(LOGPATH);
		path.mkdirs();
		file = new File(path,"LOG_"+time.format("%d%m_%H%M%S")+".txt");
		try 
		{
			file.createNewFile();
		} catch (IOException e) { }
		file.canWrite();
		file.canRead();
		try {
			buffwrite = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) { }

		write(LOG.INFO, "App gestartet");
	}

	/**
	 * Writes a new line to the LOG-File.
	 *
	 * @param type Tag the line to be either WARNING or INFO
	 * @param line The String to be written to the LOG
	 * @see setLevel()
	 */
	public synchronized void write(int type, String line) 
	{
		final String INFO_STR = "[INFO] ";
		final String WARNING_STR = "[WARNING] ";
		
		String timestr;
		time.setToNow();
		timestr = time.format("%H:%M:%S ");
		try {
			if (type == INFO)
			{
				if (logLevel == LOG_ALL)
				{
					buffwrite.write(timestr,0,timestr.length());
					buffwrite.write(INFO_STR,0,INFO_STR.length());
					buffwrite.write(line,0,line.length());
					buffwrite.newLine();
					buffwrite.flush();
				}
			}
			if (type == WARNING)
			{	
				buffwrite.write(timestr, 0, timestr.length());
				buffwrite.write(WARNING_STR, 0, WARNING_STR.length());
				buffwrite.write(line,0,line.length());
				buffwrite.newLine();
				buffwrite.flush();
			}
		} catch (IOException e) { }
	}

	/**
	 * Sets the LOG-Level to either <em>LOG_ALL</em> or <em>LOG_WARNINGS_ONLY</em> .
	 *
	 * @param lvl Contains the LOG-Level to be set
	 */
	public void setLevel(int lvl)
	{
		logLevel = lvl;
	}
	
	/**
	 * Closes and saves the LOG-File.
	 *
	 * @param lvl Contains the LOG-Level to be set
	 */
	public void save() 
	{
		write(LOG.INFO, "App beendet");
		try {
			buffwrite.flush();
			buffwrite.close();
		} catch (IOException e) { }	
	}
}