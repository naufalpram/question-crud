package com.edts.tdp.batch4.bean;

import lombok.Data;

import java.util.Map;

// Data transfer object for tgl 10 response
@Data
public class Tgl10ResponseDTO {
    private String status;
    private String message;
    private String input;
    private Map<String, Integer> data;
    private String timestamp;
}
