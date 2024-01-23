package com.edts.tdp.batch4.bean.crud;

import com.edts.tdp.batch4.bean.BaseResponseDTO;
import com.edts.tdp.batch4.model.Crud;

public class CrudResponseDTO extends BaseResponseDTO {
    private Crud data;

    public Crud getData() {
        return data;
    }

    public void setData(Crud data) {
        this.data = data;
    }
}
