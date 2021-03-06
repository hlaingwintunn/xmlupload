package com.example;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.example.model.Student;
import com.example.utils.ReadXMLFile;

@ContextConfiguration(classes = DemoApplication.class)
public class xmlParserTest extends AbstractTestNGSpringContextTests {
	
	ReadXMLFile reader = new ReadXMLFile();
	
	@Test( enabled = false)
	public void parserTest() throws ParserConfigurationException {
		List<Student> result = ReadXMLFile.ParseXmlToStudent("/home/n10/git/xmlupload/src/main/resources/static/data/student.xml");
		System.err.println(result);
	}
	
	@Test( enabled = true)
	public void parseDate() throws ParseException {
		String datestring = "12-Dec-1998";
		
		 DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
		
		 Date date = df3.parse(datestring);
		System.err.println(df3.format(date));
		
	}
	

}
