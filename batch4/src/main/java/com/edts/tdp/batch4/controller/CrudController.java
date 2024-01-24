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
    public ResponseEntity<CrudPageResponseDTO> getAllNum2Pagination(@RequestParam int number,
                                                                    @RequestParam int page) {
        try {
            CrudPageResponseDTO response = crudService.getAllEntryPagination(page);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<CrudResponseDTO> updateEntryNum2(@RequestParam int number,
                                                            @RequestParam int id,
                                                            @RequestBody Crud body) {
        try {
            CrudResponseDTO response = crudService.updateNum2(id, body);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CrudResponseDTO> deleteEntryNum2(@RequestParam int id) {
        try {
            CrudResponseDTO response = crudService.deleteNum2(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
