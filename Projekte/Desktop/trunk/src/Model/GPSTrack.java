package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.text.*;

public class GPSTrack {
	
	BufferedWriter writer;
	SimpleDateFormat dateformat;
	Date date;
	File file;
	File path;
	
	public GPSTrack(){
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat( "yyyy_MM_dd_HH_mm_ss" );
		String trackfile = "Track_"+dateformat.format(date)+".gpx";
		
		File path = new File("src/gpx/");
		path.mkdirs();
		file = new File("src/gpx/",trackfile);

		try {
				file.createNewFile();
			} catch (IOException e) { e.printStackTrace(); }
		file.canWrite();
		file.canRead();
		
		try {
			writer = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) { e.printStackTrace(); }
		
		try {
			writer.write("\u003C"+"?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?"+"\u003E");writer.write(System.getProperty("line.separator"));
			writer.write("\t\u003C"+"gpx version=\"1.1\" creator=\"CarDuinoDroid\""+"\u003E");writer.write(System.getProperty("line.separator"));
			writer.write("\t\u003C"+"trk"+"\u003E"+"\u003C"+"name"+"\u003E"+"Aktueller Track: 11 JUN 2012 18:28"+"\u003C"+"/name"+"\u003E"+"\n\t\t\u003C"+"trkseg"+"\u003E");writer.write(System.getProperty("line.separator"));
			
			writer.write("\t\t\t\u003C"+"trkpt lat=\"15.341\" lon=\"34.342\""+"\u003E");writer.write(System.getProperty("line.separator"));
			writer.write("\t\t\u003C/trkseg\u003E\n\t\u003C/trk\u003E\n\u003C/gpx\u003E");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writelogfile(String line){
		try {
			Date data = new Date();
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss" );
			String entry = df.format(data)+" "+line;
			writer.write(entry,0,entry.length());
			writer.write(System.getProperty("line.separator"));
			writer.flush();
			
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


