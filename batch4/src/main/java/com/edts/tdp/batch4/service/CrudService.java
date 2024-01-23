package com.edts.tdp.batch4.service;

import com.edts.tdp.batch4.bean.crud.CrudResponseDTO;
import com.edts.tdp.batch4.constant.crud.DeskripsiSoal;
import com.edts.tdp.batch4.model.Crud;
import com.edts.tdp.batch4.model.tglsembilan.Solver;
import com.edts.tdp.batch4.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;

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
            } else if (qnumber == 3) {
                obj = getAnswer3((int[]) input);
            } else if (qnumber == 5) {
                obj = getAnswer5((int) input);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {

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

    private Crud getAnswer2(String sentence) {
        Crud obj = new Crud();
        try {
            String res = solveNum2(sentence);
            obj.setQuestionNumber(2);
            obj.setDeskripsiSoal(DeskripsiSoal.NUMBER2);
            obj.setInput(sentence);
            obj.setResult(res);
        } catch (Exception e) {

        }
        return obj;
    }

    private Crud getAnswer3(int[] arrInt) {
        Crud obj = new Crud();
        try {
            String res = solveNum3(arrInt);
            obj.setQuestionNumber(3);
            obj.setDeskripsiSoal(DeskripsiSoal.NUMBER3);
            obj.setInput(Arrays.toString(arrInt));
            obj.setResult(res);
        } catch (Exception e) {

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

        }
        return obj;
    }

    private String solveNum2(String sentence) {
        return this.solver.countWord(sentence);
    }

    private String solveNum3(int[] arrInt) {
        return this.solver.maxNum(arrInt);
    }

    private String solveNum5(int decimal) {
        return Integer.toString(this.solver.toBinary(decimal));
    }
}
