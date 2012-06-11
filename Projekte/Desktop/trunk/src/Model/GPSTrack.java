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
	
	public void setFile(String configFile) {
		this.configFile = configFile;
	}
	
	private void createNode(XMLEventWriter eventWriter, String name,
			String value) throws XMLStreamException {

		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		XMLEvent tab = eventFactory.createDTD("\t");
		// Create Start node
		StartElement sElement = eventFactory.createStartElement("", "", name);
		eventWriter.add(tab);
		eventWriter.add(sElement);
		// Create Content
		Characters characters = eventFactory.createCharacters(value);
		eventWriter.add(characters);
		// Create End node
		EndElement eElement = eventFactory.createEndElement("", "", name);
		eventWriter.add(eElement);
		eventWriter.add(end);

	}		


	public void addPoint(double longitude ,double legitude){

	}

	public void saveConfig() throws Exception {
		// Create a XMLOutputFactory
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		// Create XMLEventWriter
		XMLEventWriter eventWriter = outputFactory
		.createXMLEventWriter(new FileOutputStream(configFile));
		// Create a EventFactory
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		// Create and write Start Tag
		StartDocument startDocument = eventFactory.createStartDocument();
		eventWriter.add(startDocument);

		// Create config open tag
		StartElement configStartElement = eventFactory.createStartElement("",
				"", "config");
		eventWriter.add(configStartElement);
		eventWriter.add(end);
		// Write the different nodes
		createNode(eventWriter, "mode", "1");
		createNode(eventWriter, "unit", "901");
		createNode(eventWriter, "current", "0");
		createNode(eventWriter, "interactive", "0");

		eventWriter.add(eventFactory.createEndElement("", "", "config"));
		eventWriter.add(end);
		eventWriter.add(eventFactory.createEndDocument());
		eventWriter.close();


	}
}

