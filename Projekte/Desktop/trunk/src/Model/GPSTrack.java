package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
//xml writer import
import java.io.FileOutputStream;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class GPSTrack {
	File file;
	Date time;
	private File path;
	private BufferedWriter buffwrite;
	String logpath = "/Desktop/CarDuinoDroid";
	private String configFile;
	
	@SuppressWarnings("deprecation")
	
	
	public GPSTrack(){
		// ruft datum und zeit ab	
		time = new Date();
		// erstellt datei mit schreibrechten
		path = new File(logpath);
		path.mkdirs();
		file = new File(logpath,"Track_"+(time.getMonth()+1)+time.getDay()+"_"+time.getHours()+time.getMinutes()+time.getSeconds()+".gpx");
		try {
				file.createNewFile();
			} catch (IOException e) { }
		file.canWrite();
		file.canRead();
		// erstellt den BufferedWriter zum schreiben von strings in die datei
		try {
				buffwrite = new BufferedWriter(new FileWriter(file));
			} catch (IOException e) { }
		
		//xml schema
		
			
			
	}
	
	public void addPoint(double longitude ,double legitude){
		
	}
	
	public void save(){
		
		try {
				buffwrite.flush();
				buffwrite.close();
			} catch (IOException e) { }	
		//endDocument()
	}
	
}
