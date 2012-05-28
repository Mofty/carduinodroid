package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.text.*;

public class Log {
	BufferedWriter writer;
	Date date;
	SimpleDateFormat dateformat;
	File file;
	
	public Log(){
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat( "yyyy_MM_dd_HH_mm_ss" );
		String logfile = "Log_"+dateformat.format(date)+".txt";
		
		file = new File("C:\\Users\\MCBird\\Desktop\\",logfile);
		try {
				file.createNewFile();
			} catch (IOException e) { e.printStackTrace(); }
		file.canWrite();
		file.canRead();
		
		try {
			writer = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public void writelogfile(String line){
		try {
			Date datum = new Date();
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss" );
			String eintrag = df.format(datum)+" "+line;
			writer.write(eintrag,0,eintrag.length());;
			writer.write(System.getProperty("line.separator"));
			writer.flush();
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public void savelogfile(){
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) { e.printStackTrace(); }
	}

}


