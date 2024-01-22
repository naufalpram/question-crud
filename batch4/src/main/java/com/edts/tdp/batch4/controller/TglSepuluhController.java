package com.edts.tdp.batch4.controller;

import com.edts.tdp.batch4.bean.Tgl10ResponseDTO;
import com.edts.tdp.batch4.service.TglSepuluhImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/tgl-sepuluh")
@RestController
public class TglSepuluhController {

    @Autowired
    TglSepuluhImplementation tglSepuluhImpl;

    // test endpoint
    @GetMapping(path = "/abc")
    public String abc() {
        return "bar";
    }

    // endpoint for tgl 10 answers
    @GetMapping("/{code}")
    public Tgl10ResponseDTO getAnswer(@PathVariable String code) {
        return tglSepuluhImpl.resolveAnswer(code);
    }
}
