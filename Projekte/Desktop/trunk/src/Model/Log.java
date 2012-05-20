package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.text.*;

public class Log {
	static FileWriter writer;
	static BufferedReader reader;
	static Date dt = new Date();
	static SimpleDateFormat df = new SimpleDateFormat( "yyyy_MM_dd_HH_mm_ss" );
	static String logfile = df.format(dt)+".txt";
	
	public static void readlogfile(){
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
			writer = new FileWriter(logfile ,true);
			writer.write(line);
			writer.write(System.getProperty("line.separator"));
			writer.write(line);
			writer.flush();
		}catch (IOException e) {
		      e.printStackTrace();}
	}
	
	public static void main(String[] args) {
		writelogfile("testfall");
	}
}


