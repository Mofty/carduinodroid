package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.text.*;

import View.GUI_Computer;

public class GPSTrack {
	GUI_Computer gui_computer;
	BufferedWriter writer;
	SimpleDateFormat dateformat;
	Date date;
	File file;
	File path;
	
	public GPSTrack(GUI_Computer GUI_computer){
		gui_computer = GUI_computer;
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat( "yyyy_MM_dd_HH_mm_ss" );
		String logfile = "Track_"+dateformat.format(date)+".gpx";
		
		File path = new File("src/gpx/");
		path.mkdirs();
		file = new File("src/gpx/",logfile);

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
			Date data = new Date();
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss" );
			String entry = df.format(data)+" "+line;
			writer.write(entry,0,entry.length());
			writer.write(System.getProperty("line.separator"));
			writer.flush();
			write_Live_Log(entry);
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public void savelogfile(){
		try {
			//5tags die dann noch fehlen
			writer.flush();
			writer.close();
		} catch (IOException e) { e.printStackTrace(); }
	}

}


