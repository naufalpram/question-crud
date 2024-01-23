package com.edts.tdp.batch4.bean.Tgl9;

import com.edts.tdp.batch4.bean.BaseResponseDTO;
import lombok.Data;


// Data transfer object for tgl 9 response
public class Tgl9ResponseDTO extends BaseResponseDTO {

    private String input;
    private String data;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
