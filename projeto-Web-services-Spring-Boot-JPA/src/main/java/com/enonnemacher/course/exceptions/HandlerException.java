package com.enonnemacher.course.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardErrorException> resourceNotFound(NotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        StandardErrorException standardErrorException = new StandardErrorException(Instant.now(), httpStatus.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(standardErrorException);
    }
}
