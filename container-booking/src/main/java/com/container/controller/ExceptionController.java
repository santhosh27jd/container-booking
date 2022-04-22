package com.container.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.container.exception.ParameterException;
import com.container.exception.ServiceException;
import com.container.exception.BookingException;
/**
 * Class ExceptionController
 * @author santhoshkumardurairaj
 *
 */
@ControllerAdvice
public class ExceptionController {

	/**
	 * Handle Service Exception
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = ServiceException.class)
	public ResponseEntity<Object> exception(ServiceException exception) {
		return new ResponseEntity<>("Sorry there was a problem processing your request", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Handle ParamException
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = ParameterException.class)
	public ResponseEntity<Object> exception(ParameterException exception) {
		return new ResponseEntity<>("Please check the Input", HttpStatus.PARTIAL_CONTENT);
	}
	
	/**
	 * Hanle BookingException
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = BookingException.class)
	public ResponseEntity<Object> exception(BookingException exception) {
		return new ResponseEntity<>("Unable to Book the container", HttpStatus.INSUFFICIENT_STORAGE);
	}
	
}
