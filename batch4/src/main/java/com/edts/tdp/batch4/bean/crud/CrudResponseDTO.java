package com.edts.tdp.batch4.bean.crud;

import com.edts.tdp.batch4.bean.BaseResponseDTO;
import com.edts.tdp.batch4.model.Crud;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrudResponseDTO extends BaseResponseDTO {
    private Crud data;
}
