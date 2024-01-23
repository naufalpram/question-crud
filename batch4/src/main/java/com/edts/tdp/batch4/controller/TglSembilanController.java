package com.edts.tdp.batch4.controller;

import com.edts.tdp.batch4.bean.Tgl9.Tgl9ResponseDTO;
import com.edts.tdp.batch4.service.TglSembilanImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    // choose code
    // codes: sort, countword, maxnum, maxchar, binary, palindrome
    @GetMapping("/{code}")
    public ResponseEntity<Tgl9ResponseDTO> getAnswer(@PathVariable String code) {
        Tgl9ResponseDTO out = tglSembilanImpl.resolveResponse(code);
        if (out.getStatus() == 200)
            return new ResponseEntity<>(out, HttpStatus.OK);
        else if (out.getStatus() == 400)
            return new ResponseEntity<>(out, HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
