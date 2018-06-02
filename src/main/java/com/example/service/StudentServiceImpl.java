package com.example.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

/**
 * 
 * @author Hlaing Win Tun
 *
 */

@Service("studentService")
public class StudentServiceImpl implements StudentService {
	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	List<Student> results = new ArrayList<>();
	
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
	public List<Student> readandSaveStudentInfo(Optional<MultipartFile> multipartFileOpt) throws StudentException {
		
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
					
					Student student = new Student();
					
					if(nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element elem = (Element) nNode;
						
						// Name Validation
						String studentName = elem.getElementsByTagName("studentName").item(0).getTextContent();
						if(studentName.isEmpty()) {
							throw new StudentException("name.null");
						} else if (studentName.length() > 40) {
							throw new StudentException("name.length");
						} else {
							student.setStudentName(studentName);
						}
						
						
						// Date of Birth Validation
						String dob = elem.getElementsByTagName("dob").item(0).getTextContent();
						if(dob.isEmpty()) {
							throw new StudentException("dob.null");
						} else {
							DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
							Date date = df3.parse(dob);
							student.setDob(df3.format(date));
						}
						
						
						//Totalmarks Validation
						int totalmarks = Integer.parseInt(elem.getElementsByTagName("totalMarks").item(0).getTextContent());
						if(totalmarks < 0) {
							throw new StudentException("totalmarks.nagetive");
						} else {
							student.setTotalMarks(totalmarks);
						}
						
						
						logger.info("Parse sutdent data: {}", student);
						
						results.add(student);
					}
					
				}
				
			} catch (SAXException | IOException | ParserConfigurationException | ParseException ex) {
				logger.error("Input multipart file error {}", ex);
			}

		}
		
		saveAllStudent(results);
		
		return results;
	}

	@Override
	public Boolean deleteStudentById(Long id) {
		if(studentRepository.exists(id)) {
			studentRepository.delete(id);
			return true;
		}
		
		return false;
		
	}

}
