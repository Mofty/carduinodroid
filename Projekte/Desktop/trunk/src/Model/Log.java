package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.text.*;

import View.GUI_Computer;

public class Log {
	GUI_Computer gui_computer;
	BufferedWriter writer;
	SimpleDateFormat dateformat;
	Date date;
	File file;
	File path;
	
	public Log(GUI_Computer GUI_computer){
		gui_computer = GUI_computer;
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat( "yyyy_MM_dd_HH_mm_ss" );
		String logfile = "Log_"+dateformat.format(date)+".txt";
		
		File path = new File("src/Logs/");
		path.mkdirs();
		file = new File("src/Logs/",logfile);

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
			writer.flush();
			writer.close();
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	private void write_Live_Log(String Text){
		if(gui_computer.Live_Log != null)
		gui_computer.Live_Log.setText(gui_computer.Live_Log.getText()+"\n"+Text);
	}

}


