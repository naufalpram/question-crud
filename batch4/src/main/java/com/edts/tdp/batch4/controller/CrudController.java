package com.edts.tdp.batch4.controller;

import com.edts.tdp.batch4.bean.crud.CrudPageResponseDTO;
import com.edts.tdp.batch4.bean.crud.CrudResponseDTO;
import com.edts.tdp.batch4.bean.crud.CrudListResponseDTO;
import com.edts.tdp.batch4.model.Crud;
import com.edts.tdp.batch4.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<CrudResponseDTO> getEntryByIdNum2(@RequestParam int number, @RequestParam int id) {
        try {
            CrudResponseDTO response = crudService.getEntryByIdNum2(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<CrudPageResponseDTO> getAll(@RequestParam int page, @RequestParam int size) {
        try {
            CrudPageResponseDTO response = crudService.getAllEntriesPagination(page, size);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/all-by-num/{number}")
    public ResponseEntity<CrudPageResponseDTO> getAllNumPagination(@PathVariable int number,
                                                                    @RequestParam int page,
                                                                   @RequestParam int size) {
        try {
            CrudPageResponseDTO response = crudService.getAllNumEntryPagination(number, page, size);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<CrudResponseDTO> updateEntry(@RequestParam int number,
                                                            @RequestParam int id,
                                                            @RequestBody Crud body) {
        try {
            CrudResponseDTO response = crudService.updateEntry(number, id, body);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CrudResponseDTO> deleteEntry(@RequestParam int id) {
        try {
            CrudResponseDTO response = crudService.deleteEntry(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
