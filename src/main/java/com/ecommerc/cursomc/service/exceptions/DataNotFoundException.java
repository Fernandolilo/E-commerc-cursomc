package com.ecommerc.cursomc.service.exceptions;

public class DataNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DataNotFoundException(String msg) {
		
		super(msg);
	}
	public DataNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
		
	}
	
}
