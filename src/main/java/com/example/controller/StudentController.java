package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.Student;
import com.example.service.StudentException;
import com.example.service.StudentService;

/**
 * 
 * @author Hlaing Win Tun
 *
 */

@Controller
public class StudentController {
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping("/")
	public String goHome(){
		return "index";
	}
	 
	@PostMapping("/") 
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
    		RedirectAttributes redirectAttributes) throws StudentException {

        if (file.isEmpty()) {
        	redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
        	return "redirect:/";
        }
        
			List<Student> success = studentService.readandSaveStudentInfo(Optional.of(file));
			
			if(!success.isEmpty()) {
				redirectAttributes.addFlashAttribute("message", "You have Successfully uploaded '" + file.getOriginalFilename() + "'");
			} else {
				logger.info("Xml file parsing error");
				redirectAttributes.addFlashAttribute("message", "Xml File Parsing error");
			}

        return "redirect:/";
    }
	
    @PostMapping(value = "/delete")
    public String deleteStudentById(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) throws StudentException {
             boolean success = studentService.deleteStudentById(id);
             
             if(success) {
            	 redirectAttributes.addFlashAttribute("message", "Student id = '"+ id +"' was removed from our database.");
            	 logger.info("Student id {} was removed from our database.", id);
             } else {
            	 redirectAttributes.addFlashAttribute("message", "Student id = '"+ id +"' does not exist.");
            	 logger.info("Student id = {} does not exist", id);
             }  
            
            return "redirect:/";

    }
	


}
