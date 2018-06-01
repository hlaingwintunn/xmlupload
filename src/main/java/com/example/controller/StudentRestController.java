package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.Student;
import com.example.service.StudentService;

@RestController
public class StudentRestController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(path="/students" , method = RequestMethod.GET)
	public List<Student> getAllStudent() {
		return studentService.getAllStudent();
	}
	
	@PostMapping("/") 
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
    		RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
        	redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
        }
        
        studentService.readFile(Optional.of(file));
        redirectAttributes.addFlashAttribute("message", "You Successfully uploaded '" + file.getOriginalFilename() + "'");

        return "redirect:/";
    }

}
