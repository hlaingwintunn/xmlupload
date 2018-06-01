package com.example.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.example.model.Student;
import com.example.repository.StudentRepository;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> saveAllStudent(List<Student> studentList) {
			return studentRepository.save(studentList);
	}

	@Override
	public List<Student> getAllStudent() {
		return studentRepository.findAll();
	}

	@Override
	public List<Student> readFile(Optional<MultipartFile> multipartFileOpt) {
		List<Student> results = new ArrayList<>();
		
		if(multipartFileOpt.isPresent()) {
			
			try {
				
				MultipartFile file = multipartFileOpt.get();
				
		        InputStream is = file.getInputStream();
		        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();                 
		        Document doc = dBuilder.parse(is);
		        
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
								.setTotalMarks(Float.parseFloat(elem.getElementsByTagName("totalMarks").item(0).getTextContent()));
						
						logger.debug("Parse sutdent data:", student);
						
						results.add(student);
					}
					
				}
				
			} catch (SAXException | IOException | ParserConfigurationException ex) {
				logger.error("Input multipart file error {}", ex);
			}

		}
		
		return results;
	}

}
