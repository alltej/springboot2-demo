package com.tleaf.demo.exceptions.handler;

import com.tleaf.demo.services.exceptions.ResultNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Allan Tejano
 * 4/23/2018
 */
@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler( value = ResultNotFoundException.class )
    @ResponseBody
    @ResponseStatus( value = HttpStatus.NOT_FOUND )
    public ResponseEntity handleException( final ResultNotFoundException exception,
            final HttpServletRequest request ) {
        return new ResponseEntity<>( exception.getMessage(), HttpStatus.NOT_FOUND );
    }
}
