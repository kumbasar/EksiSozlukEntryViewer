import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class EksiEntryViewer  {

	private final DateFormat dfm = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	
	private Entry[] entry;

	public void parseEksiXML(File xmlFile) {

		//suserName = xmlFile.getName().toLowerCase();

		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder;
		try {

			docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();

			NodeList listOfEntries = doc.getElementsByTagName("entry");

			entry = new Entry[listOfEntries.getLength()];

			for (int s = 0; s < entry.length; ++s) {

				entry[s] = new Entry();

				Element firstEntryNode = (Element) listOfEntries.item(s);

				entry[s].setEntryTitle(firstEntryNode.getAttribute("title")
						.trim());
				entry[s].setEntryID(firstEntryNode.getAttribute("id").trim());
				entry[s].setEntryBody(firstEntryNode.getTextContent().trim());

				try {
					entry[s].setEntryDate(dfm.parse(firstEntryNode
							.getAttribute("date").trim()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				/*
				System.out
						.println("---------------------------------------------");
				System.out.println("Entry Id: " + entry[s].getEntryID());
				System.out.println("Entry Date: "
						+ dfm.format(entry[s].getEntryDate()));
				System.out.println("Entry Title: " + entry[s].getEntryTitle());
				System.out.println("Entry Body: \n" + entry[s].getEntryBody());
				*/

			}// end of for loop with s var

			// System.out.println(listOfEntries.getLength());

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public Entry[] getEntry(){
		return entry;
	}
}
