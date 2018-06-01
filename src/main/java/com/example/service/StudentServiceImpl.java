package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Student;
import com.example.repository.StudentRepository;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
	
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

}
