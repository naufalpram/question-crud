package com.edts.tdp.batch4.bean.crud;

import com.edts.tdp.batch4.bean.BaseResponseDTO;
import com.edts.tdp.batch4.model.Crud;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class CrudPageResponseDTO extends BaseResponseDTO {
    private Page<Crud> data;
}
