package com.example.service;

import java.util.List;

import com.example.model.Student;

public interface StudentService {
	
	public List<Student> saveAllStudent(List<Student> studentList);

	public List<Student> getAllStudent();

}
