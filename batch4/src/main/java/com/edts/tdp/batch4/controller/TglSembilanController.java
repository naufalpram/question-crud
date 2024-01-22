package com.edts.tdp.batch4.controller;

import com.edts.tdp.batch4.bean.ResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/tgl-sembilan")
@RestController
public class TglSembilanController {


    @GetMapping("/hello")
    public String hello() {
        return "bang";
    }

//    @GetMapping("/{soal}")
//    public ResponseDTO getAnswer(PathVariable String code) {
//
//        return
//    }
}
