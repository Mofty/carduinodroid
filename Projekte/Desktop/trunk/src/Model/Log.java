package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.text.*;

public class Log {
	BufferedWriter writer;
	BufferedReader reader;
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
		// erstellt den BufferedWriter zum schreiben von strings in die datei
		try {
			writer = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public void readlogfile(){
		String zeile = null; 
		
		try {
            reader = new BufferedReader(new FileReader("C:\\Users\\MCBird\\Desktop\\testen.txt"));          
            while ((zeile = reader.readLine()) != null) {
    			System.out.println("Gelesene Zeile: " + zeile);
    		}
		}
		catch (IOException e) {
            System.err.println("Auslesen Log fehlgeschlagen");}   
	}
	
	public static void writelogfile(String line){
		try {
			writer.write(line);
			writer.newLine();
			writer.flush();
			writer.close();
		} catch (IOException e) { e.printStackTrace(); }
		
		/*try {
			writer = new FileWriter(logfile ,true);
			writer.write(date+":"+line);
			writer.write(System.getProperty("line.separator"));
			writer.write(line);
			writer.flush();
		}catch (IOException e) {
			System.err.println("Schreiben vom Log fehlgeschlagen");}*/
	}

}


