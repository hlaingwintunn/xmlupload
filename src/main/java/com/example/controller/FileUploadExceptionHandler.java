package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.service.StudentException;

/**
 * 
 * @author Hlaing Win Tun
 *
 */

@ControllerAdvice
public class FileUploadExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadExceptionHandler.class);
	private MessageSource messageSource;
	
	@Autowired
	public FileUploadExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        return "redirect:/";

    }
    
	  @ExceptionHandler(StudentException.class)
	  public String studentException(StudentException ex, RedirectAttributes redirectAttributes) {
		  
		  List<String> errorMessage = new ArrayList<>();
		  errorMessage.add(messageSource.getMessage(ex.getMessage(), null, Locale.getDefault()));
		  
		  redirectAttributes.addFlashAttribute("message", errorMessage.get(0));
		  
		  return "redirect:/" ;

	  }
}
