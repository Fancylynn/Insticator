package com.fancylynn.insticator.controller;

import com.fancylynn.insticator.exception.NoMoreQuestionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

/**
 * Created by Lynn on 2018/9/16.
 */
@ControllerAdvice
public class ExceptionHandlingController {
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            NoMoreQuestionException.class
    })
    public ResponseEntity<String> badRequestError(Exception ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            EntityNotFoundException.class
    })
    public ResponseEntity<String> notFoundError(Exception ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
