package com.example.service;

/**
 * 
 * @author Hlaing Win Tun
 *
 */

public class StudentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8159659419473279412L;
	
	public StudentException(String message) {
		super(message);
	}
	
	public StudentException(String message, Throwable cause) {
		super(message, cause);
	}

}
