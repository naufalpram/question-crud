package com.edts.tdp.batch4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/tgl-sepuluh")
@RestController
public class TglSepuluhController {

    @GetMapping(path = "/abc")
    public String abc() {
        return "bar";
    }
}
