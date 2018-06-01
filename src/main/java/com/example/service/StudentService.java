package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.example.model.Student;

public interface StudentService {
	
	public List<Student> saveAllStudent(List<Student> studentList);

	public List<Student> getAllStudent();

	public List<Student> readFile(Optional<MultipartFile> multipartFileOpt);

}
