package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Student;
import com.example.service.StudentService;

/**
 * 
 * @author Hlaing Win Tun
 *
 */

@RestController
public class StudentRestController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(path="/students" , method = RequestMethod.GET)
	public List<Student> getAllStudent() {
		return studentService.getAllStudent();
	}

}
