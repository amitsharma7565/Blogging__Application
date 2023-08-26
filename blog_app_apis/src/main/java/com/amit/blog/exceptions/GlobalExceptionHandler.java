package com.amit.blog.exceptions;




import com.amit.blog.payloads.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Map<String, String>> httpMessageNotReadableException(HttpMessageNotReadableException ex){
	Map<String,String> resq= new HashMap<>();
	String key="bad";
	String value="check the request its is not correct";
	resq.put(key, value);
		return new ResponseEntity <Map<String,String>>(resq,HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(ApiException.class)
public ResponseEntity<ApiResponse> handleApiException(ApiException ex){
	String messsage=ex.getMessage();
	ApiResponse api= new ApiResponse(messsage,true);
	return new ResponseEntity<ApiResponse>(api,HttpStatus.BAD_REQUEST);
}
	
}