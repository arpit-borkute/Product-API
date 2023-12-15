package com.jbk.exception;

import java.util.Date;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public HashMap<String, Object> handlerMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("Time", new Date());
		ex.getBindingResult().getFieldErrors().forEach(error -> { // java8
			map.put(error.getField(), error.getDefaultMessage());
		});

		// or by normal way
		// BindingResult bindingResult =ex.getBindingResult();
		// List<FieldError> fieldError = bindingResult.getFieldErrors();
		// for loop
		return map;

	}

	@ResponseStatus(code = HttpStatus.ALREADY_REPORTED)
	@ExceptionHandler(ResourceAlreadyExistException.class)
	public ResponseEntity<String> resourceAlreadyExistException(ResourceAlreadyExistException ex) {
		return ResponseEntity.ok(ex.getMessage());
	}

}
