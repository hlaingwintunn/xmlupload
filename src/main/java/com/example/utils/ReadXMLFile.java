package com.example.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.example.model.Student;

/**
 * 
 * @author Hlaing Win Tun
 *
 */

public class ReadXMLFile {
	private static final Logger logger = LoggerFactory.getLogger(ReadXMLFile.class);
	
	public static List<Student> ParseXmlToStudent(final String path) throws ParserConfigurationException {
		List<Student> results = new ArrayList<>();
		

		try {
			File xmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(xmlFile);
			
			doc.getDocumentElement().normalize();
			
			logger.debug("Root Element:", doc.getDocumentElement().getNodeName());
			logger.info("------------------------------");
			
			NodeList nList = doc.getElementsByTagName("student");
			
			for(int i=0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				
				logger.info("\nCurrent Element:"+ nNode.getNodeName());
				
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) nNode;
					
					Student student = new Student()
							.setStudentName(elem.getElementsByTagName("studentName").item(0).getTextContent())
							.setDob(elem.getElementsByTagName("dob").item(0).getTextContent())
							.setTotalMarks(Integer.parseInt(elem.getElementsByTagName("totalMarks").item(0).getTextContent()));
					
					logger.debug("Parse sutdent data:", student);
					
					results.add(student);
				}
				
			}
			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return results;
	}

}
