package com.edts.tdp.batch4.bean;

import lombok.Data;

import java.util.Map;

// Data transfer object for tgl 10 data
@Data
public class Tgl10DataDTO {
    private String input;
    private Map<String, Integer> counter;
}