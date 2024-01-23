package com.edts.tdp.batch4.bean;

import com.edts.tdp.batch4.model.Crud;
import lombok.Data;

@Data
public class BaseResponseDTO {
    private int status;
    private String message;
    private String timestamp;
}
