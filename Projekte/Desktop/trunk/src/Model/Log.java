package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Log {
	String logfile;
	
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


