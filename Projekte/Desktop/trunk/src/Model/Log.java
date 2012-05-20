package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.text.*;

public class Log {
	static Date dt = new Date();
	static SimpleDateFormat df = new SimpleDateFormat( "yyyy_MM_dd_HH_mm_ss" );
	static String logfile = "Date = " + df.format(dt);
	
	public static void readlogfile(){
		BufferedReader reader;
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
	}
	
	public static void main(String[] args) {
		readlogfile();
	}
}


