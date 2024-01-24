package com.edts.tdp.batch4.service;

import com.edts.tdp.batch4.bean.crud.CrudPageResponseDTO;
import com.edts.tdp.batch4.bean.crud.CrudResponseDTO;
import com.edts.tdp.batch4.bean.crud.CrudListResponseDTO;
import com.edts.tdp.batch4.constant.crud.DeskripsiSoal;
import com.edts.tdp.batch4.model.Crud;
import com.edts.tdp.batch4.model.tglsembilan.Solver;
import com.edts.tdp.batch4.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class CrudService {

    CrudRepository crudRepository;
    Solver solver;

    @Autowired
    public CrudService(@Autowired CrudRepository crudRepository, @Autowired Solver solver) {
        this.crudRepository = crudRepository;
        this.solver = solver;
    }

    public<T> CrudResponseDTO createEntry(int qnumber, T input) {
        CrudResponseDTO response = new CrudResponseDTO();
        Crud obj = new Crud();
        try {
            if (qnumber == 2) {
                obj = getAnswer2((String) input);
            } else if (qnumber == 5) {
                obj = getAnswer5((int) input);
            } else if (qnumber == 6) {
                obj = getAnswer6((String) input);
            } else {
                throw new Exception("error");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        obj = this.crudRepository.save(obj);

        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage(HttpStatus.CREATED.getReasonPhrase());
        response.setData(obj);

        // Define a DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        response.setTimestamp(obj.getCreatedDate().format(formatter));

        return response;
    }

    public CrudResponseDTO getEntryByIdNum2(int id) throws Exception {
        CrudResponseDTO response = new CrudResponseDTO();
        Optional<Crud> crud = this.crudRepository.findByIdAndQuestionNumber(id, 2);

        if (crud.isPresent()) {
            response.setData(crud.get());
            response.setStatus(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());

            // Define a DateTimeFormatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            response.setTimestamp(LocalDateTime.now().format(formatter));
        } else {
            throw new Exception(String.format("Entry with id: %d is not present in the database", id));
        }

        return response;
    }

    public CrudPageResponseDTO getAllEntryPagination(int page) throws Exception {
        CrudPageResponseDTO response = new CrudPageResponseDTO();
        Pageable pageable = PageRequest.of(page,2);

        try {
            Page<Crud> allNum2 = this.crudRepository.findAllByQuestionNumber(2, pageable);
            response.setData(allNum2);
            response.setStatus(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());

            // Define a DateTimeFormatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            response.setTimestamp(LocalDateTime.now().format(formatter));
        } catch (Exception e) {
            throw new Exception(String.format("There are no entry of question number %d", 2));
        }
        return response;
    }

    public CrudResponseDTO updateNum2(int id, Crud newCrud) throws Exception{
        CrudResponseDTO response = new CrudResponseDTO();
        Optional<Crud> toBeUpdated = this.crudRepository.findByIdAndQuestionNumber(id, 2);

        if (toBeUpdated.isPresent()) {
            Crud oldCrud = toBeUpdated.get();
            newCrud.setId(id);
            newCrud.setCreatedDate(newCrud.getCreatedDate() == null ? oldCrud.getCreatedDate() : newCrud.getCreatedDate());
            newCrud.setDeskripsiSoal(newCrud.getDeskripsiSoal() == null ? oldCrud.getDeskripsiSoal() : newCrud.getDeskripsiSoal());
            newCrud.setQuestionNumber(newCrud.getQuestionNumber() == 0 ? oldCrud.getQuestionNumber() : newCrud.getQuestionNumber());
            newCrud.setInput(newCrud.getInput() == null ? oldCrud.getInput() : newCrud.getInput());
            newCrud.setResult(newCrud.getResult() == null ? oldCrud.getResult() : newCrud.getResult());

            response.setData(this.crudRepository.save(newCrud));
            response.setStatus(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());

            // Define a DateTimeFormatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            response.setTimestamp(LocalDateTime.now().format(formatter));
        } else {
            throw new Exception(String.format("Entry with id: %d is not present in the database", id));
        }

        return response;
    }

    public CrudResponseDTO deleteNum2(int id) throws Exception {
        CrudResponseDTO response = new CrudResponseDTO();
//        Optional<Crud> toBeDeleted = this.crudRepository.findByIdAndQuestionNumber(id, 2);
        Optional<Crud> toBeDeleted = this.crudRepository.findById(id);

        if (toBeDeleted.isPresent()) {
            this.crudRepository.deleteById(id);
            response.setStatus(HttpStatus.NO_CONTENT.value());
            response.setMessage("Deleted");

            // Define a DateTimeFormatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            response.setTimestamp(LocalDateTime.now().format(formatter));
        } else {
            throw new Exception(String.format("Entry with id: %d is not present in the database", id));
        }
        return response;
    }

    private Crud getAnswer2(String sentence) {
        Crud obj = new Crud();
        try {
            String res = solveNum2(sentence);
            obj.setQuestionNumber(2);
            obj.setDeskripsiSoal(DeskripsiSoal.NUMBER2);
            obj.setInput(sentence);
            obj.setResult(res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }


    private Crud getAnswer5(int decimal) {
        Crud obj = new Crud();
        try {
            String res = solveNum5(decimal);
            obj.setQuestionNumber(5);
            obj.setDeskripsiSoal(DeskripsiSoal.NUMBER5);
            obj.setInput(Integer.toString(decimal));
            obj.setResult(res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }
    private Crud getAnswer6(String word) {
        Crud obj = new Crud();
        try {
            String res = solveNum6(word);
            obj.setQuestionNumber(6);
            obj.setDeskripsiSoal(DeskripsiSoal.NUMBER6);
            obj.setInput(word);
            obj.setResult(res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    private String solveNum2(String sentence) {
        return this.solver.countWord(sentence);
    }

    private String solveNum5(int decimal) {
        return Integer.toString(this.solver.toBinary(decimal));
    }

    private String solveNum6(String str) {
        return Boolean.toString(this.solver.isPalindrome(str));
    }
}
