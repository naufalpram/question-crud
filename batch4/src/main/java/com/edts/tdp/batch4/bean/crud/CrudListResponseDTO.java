package com.edts.tdp.batch4.bean.crud;

import com.edts.tdp.batch4.bean.BaseResponseDTO;
import com.edts.tdp.batch4.model.Crud;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CrudListResponseDTO extends BaseResponseDTO {
    private List<Crud> data;
}
