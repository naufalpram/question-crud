package com.edts.tdp.batch4.controller;

import com.edts.tdp.batch4.bean.Tgl10ResponseDTO;
import com.edts.tdp.batch4.bean.Tgl9ResponseDTO;
import com.edts.tdp.batch4.service.TglSembilanImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/tgl-sembilan")
@RestController
public class TglSembilanController {

    @Autowired
    TglSembilanImplementation tglSembilanImpl;

    // test endpoint
    @GetMapping("/hello")
    public String hello() {
        return "bang";
    }

    // endpoint for tgl 9 answers
    @GetMapping("/{code}")
    public ResponseEntity<Tgl9ResponseDTO> getAnswer(@PathVariable String code) {
        Tgl9ResponseDTO out = tglSembilanImpl.resolveResponse(code);
        if (out.getStatus().equals("200"))
            return new ResponseEntity<>(out, HttpStatus.OK);
        else if (out.getStatus().equals("400"))
            return new ResponseEntity<>(out, HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
