package com.edts.tdp.batch4.bean.Tgl9;

import com.edts.tdp.batch4.bean.BaseResponseDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
// Data transfer object for tgl 9 response
public class Tgl9ResponseDTO extends BaseResponseDTO {

    private String input;
    private String data;
}
