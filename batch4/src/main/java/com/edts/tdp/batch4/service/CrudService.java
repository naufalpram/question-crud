package com.edts.tdp.batch4.service;

import com.edts.tdp.batch4.bean.crud.CrudPageResponseDTO;
import com.edts.tdp.batch4.bean.crud.CrudResponseDTO;
import com.edts.tdp.batch4.constant.crud.DeskripsiSoal;
import com.edts.tdp.batch4.exception.CustomException;
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

    public<T> CrudResponseDTO createEntry(int number, T input) {
        CrudResponseDTO response = new CrudResponseDTO();
        Crud obj;

        if (number == 2) {
            if (!(input instanceof String))
                throw new CustomException(HttpStatus.BAD_REQUEST, "Input for number 2 must be a String type");
            obj = getAnswer2((String) input);
        } else if (number == 5) {
            if (input instanceof String)
                throw new CustomException(HttpStatus.BAD_REQUEST, "Input for number 5 must be an integer type");
            obj = getAnswer5((int) input);
        } else if (number == 6) {
            if (!(input instanceof String))
                throw new CustomException(HttpStatus.BAD_REQUEST, "Input for number 6 must be a String type");
            obj = getAnswer6((String) input);
        } else {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Case number must be between 2, 5, or 6");
        }
        obj = this.crudRepository.save(obj);
        if (obj.equals(null)) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Entry creation failed");
        }
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage(HttpStatus.CREATED.getReasonPhrase());
        response.setData(obj);

        // Define a DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        response.setTimestamp(obj.getCreatedDate().format(formatter));

        return response;
    }

    public CrudResponseDTO getEntryById(int id) {
        if (id < 0)
            throw new CustomException(HttpStatus.BAD_REQUEST, "Id must be a positive integer");

        CrudResponseDTO response = new CrudResponseDTO();
        Optional<Crud> crud = this.crudRepository.findById(id);

        if (crud.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, String.format("Entry with id: %d is not found in the database", id));
        } else {
            response.setData(crud.get());
            response.setStatus(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());

            // Define a DateTimeFormatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            response.setTimestamp(LocalDateTime.now().format(formatter));
        }

        return response;
    }

    public CrudPageResponseDTO getAllEntriesPagination(int page, int size) {
        if (page < 0 || size < 0)
            throw new CustomException(HttpStatus.BAD_REQUEST, "page or size must be positive integers");

        CrudPageResponseDTO response = new CrudPageResponseDTO();
        Pageable pageable = PageRequest.of(page, size);

        Page<Crud> allCrud = this.crudRepository.findAll(pageable);
        response.setData(allCrud);
        response.setStatus(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.getReasonPhrase());

        // Define a DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        response.setTimestamp(LocalDateTime.now().format(formatter));
        return response;
    }

    public CrudPageResponseDTO getAllNumEntryPagination(int number, int page, int size) {
        if (!(number == 2 || number == 5 || number == 6))
            throw new CustomException(HttpStatus.BAD_REQUEST, "Case number must be between 2, 5, or 6");

        if (page < 0 || size < 0)
            throw new CustomException(HttpStatus.BAD_REQUEST, "Page or size must be positive integers");

        CrudPageResponseDTO response = new CrudPageResponseDTO();
        Pageable pageable = PageRequest.of(page, size);

        Page<Crud> allNum2 = this.crudRepository.findAllByQuestionNumber(number, pageable);
        response.setData(allNum2);
        response.setStatus(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.getReasonPhrase());

        // Define a DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        response.setTimestamp(LocalDateTime.now().format(formatter));
        return response;
    }

    public CrudResponseDTO updateEntry(int number, int id, Crud newCrud) {
        if (!(number == 2 || number == 5 || number == 6))
            throw new CustomException(HttpStatus.BAD_REQUEST, "Case number must be between 2, 5, or 6");

        if (id < 0)
            throw new CustomException(HttpStatus.BAD_REQUEST, "Id must be a positive integer");

        CrudResponseDTO response = new CrudResponseDTO();
        Optional<Crud> toBeUpdated = this.crudRepository.findByIdAndQuestionNumber(id, number);

        if (toBeUpdated.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, String.format("Entry with id: %d is not found in the database", id));
        } else {
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
        }

        return response;
    }

    public CrudResponseDTO deleteEntry(int id) {
        if (id < 0)
            throw new CustomException(HttpStatus.BAD_REQUEST, "Id must be a positive integer");

        CrudResponseDTO response = new CrudResponseDTO();
        Optional<Crud> toBeDeleted = this.crudRepository.findById(id);

        if (toBeDeleted.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, String.format("Entry with id: %d is not found in the database", id));
        } else {
            this.crudRepository.deleteById(id);
            response.setStatus(HttpStatus.NO_CONTENT.value());
            response.setMessage("Deleted");

            // Define a DateTimeFormatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            response.setTimestamp(LocalDateTime.now().format(formatter));
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
