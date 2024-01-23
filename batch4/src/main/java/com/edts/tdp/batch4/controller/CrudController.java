package com.edts.tdp.batch4.controller;

import com.edts.tdp.batch4.bean.crud.CrudResponseDTO;
import com.edts.tdp.batch4.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/crud")
public class CrudController {

    @Autowired
    CrudService crudService;

    @PostMapping("/create")
    public ResponseEntity<CrudResponseDTO> createEntry(@RequestParam int number, @RequestBody Map<String, Object> request) {
        Object input = request.get("input");
        CrudResponseDTO response = crudService.createEntry(number, input);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
