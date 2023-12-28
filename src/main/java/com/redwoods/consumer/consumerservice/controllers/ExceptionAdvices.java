package com.redwoods.consumer.consumerservice.controllers;

import com.redwoods.consumer.consumerservice.dtos.ErrorReponseDto;
import com.redwoods.consumer.consumerservice.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorReponseDto> handleNotFoundException(Exception exception) {
		ErrorReponseDto errorReponseDto = new ErrorReponseDto();
		errorReponseDto.setMessage(exception.getMessage());

		return new ResponseEntity<>(errorReponseDto, HttpStatus.NOT_FOUND);
	}
}
