package com.edts.tdp.batch4.controller;

import com.edts.tdp.batch4.bean.crud.CrudPageResponseDTO;
import com.edts.tdp.batch4.bean.crud.CrudResponseDTO;
import com.edts.tdp.batch4.exception.CustomException;
import com.edts.tdp.batch4.model.Crud;
import com.edts.tdp.batch4.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        if (input == null)
            throw new CustomException(HttpStatus.BAD_REQUEST, "Must have an input");
        CrudResponseDTO response = crudService.createEntry(number, input);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<CrudResponseDTO> getEntryById(@RequestParam int id) {
        CrudResponseDTO response = crudService.getEntryById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<CrudPageResponseDTO> getAll(@RequestParam int page, @RequestParam int size) {
        CrudPageResponseDTO response = crudService.getAllEntriesPagination(page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/all-by-num/{number}")
    public ResponseEntity<CrudPageResponseDTO> getAllNumPagination(@PathVariable int number,
                                                                    @RequestParam int page,
                                                                   @RequestParam int size) {
        CrudPageResponseDTO response = crudService.getAllNumEntryPagination(number, page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<CrudResponseDTO> updateEntry(@RequestParam int number,
                                                            @RequestParam int id,
                                                            @RequestBody Crud body) {
        CrudResponseDTO response = crudService.updateEntry(number, id, body);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CrudResponseDTO> deleteEntry(@RequestParam int id) {
        CrudResponseDTO response = crudService.deleteEntry(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
