package com.edts.tdp.batch4.bean;

import lombok.Data;


// Data transfer object for tgl 9 response
@Data
public class Tgl9ResponseDTO {
    private String status;
    private String message;
    private String input;
    private String data;
    private String timestamp;
}
