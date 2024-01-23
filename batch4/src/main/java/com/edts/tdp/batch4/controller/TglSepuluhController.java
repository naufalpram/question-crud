package com.edts.tdp.batch4.controller;

import com.edts.tdp.batch4.bean.Tgl10ResponseDTO;
import com.edts.tdp.batch4.service.TglSepuluhImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Tgl10ResponseDTO> getAnswer(@PathVariable String code) {
        Tgl10ResponseDTO out = tglSepuluhImpl.resolveAnswer(code);
        if (out.getStatus().equals("200"))
            return new ResponseEntity<>(out, HttpStatus.OK);
        else if (out.getStatus().equals("400"))
            return new ResponseEntity<>(out, HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(out, HttpStatus.NOT_FOUND);
    }
}
