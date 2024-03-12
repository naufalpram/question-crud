package com.edts.tdp.batch4.bean.Tgl10;

import com.edts.tdp.batch4.bean.BaseResponseDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
// Data transfer object for tgl 10 response
public class Tgl10ResponseDTO extends BaseResponseDTO {
    private String input;
    private Map<String, Integer> data;
}
