package com.edts.tdp.batch4.bean;

import lombok.Data;

@Data
public class ResponseDTO {
    private String status;
    private String message;
    private String input;
    private String data;
    private String timestamp;
}
