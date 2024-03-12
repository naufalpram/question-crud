package com.edts.tdp.batch4.bean.crud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class CustomExceptionDTO {
    private int errorCode;
    private HttpStatus status;
    private String message;
}
