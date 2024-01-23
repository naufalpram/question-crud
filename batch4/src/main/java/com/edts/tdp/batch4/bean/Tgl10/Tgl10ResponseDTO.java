package com.edts.tdp.batch4.bean.Tgl10;

import com.edts.tdp.batch4.bean.BaseResponseDTO;
import lombok.Data;

import java.util.Map;

// Data transfer object for tgl 10 response
public class Tgl10ResponseDTO extends BaseResponseDTO {
    private String input;
    private Map<String, Integer> data;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public Map<String, Integer> getData() {
        return data;
    }

    public void setData(Map<String, Integer> data) {
        this.data = data;
    }
}
