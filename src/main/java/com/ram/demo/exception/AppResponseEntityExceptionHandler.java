package com.ram.demo.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class AppResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public final ExceptionResponse handleNotFoundException(EmployeeNotFoundException ex, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true));
		return  exceptionResponse;
	}
	
   @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Request validation failed",ex.getBindingResult().toString());
		return  new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public final ExceptionResponse handleAllException(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return  exceptionResponse;
	}
}
