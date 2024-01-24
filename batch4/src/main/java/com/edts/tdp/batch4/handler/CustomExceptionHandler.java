package com.edts.tdp.batch4.handler;

import com.edts.tdp.batch4.bean.crud.CustomExceptionDTO;
import com.edts.tdp.batch4.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customException(CustomException e) {
        return ResponseEntity.status(e.getStatus()).body(new CustomExceptionDTO(
                e.getStatus().value(), e.getStatus(), e.getMessage()));
    }
}
