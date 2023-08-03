package com.amit.blog.exceptions;




import com.amit.blog.payloads.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<ApiResponse> resousrceNotFounDExceptionHandle(ResourceNotFoundException ex){
	String messsage=ex.getMessage();
	ApiResponse api= new ApiResponse(messsage,false);
	return new ResponseEntity<ApiResponse>(api,HttpStatus.NOT_FOUND);
}
}