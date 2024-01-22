package com.edts.tdp.batch4.service;

import com.edts.tdp.batch4.bean.ResponseDTO;
import com.edts.tdp.batch4.bean.SolverMethodDTO;
import com.edts.tdp.batch4.constant.inputs.*;
import com.edts.tdp.batch4.interfaces.TglSembilanInterface;
import com.edts.tdp.batch4.model.tglsembilan.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class TglSembilanImplementation implements TglSembilanInterface {

    // tanggal 9 cases solver object
    Solver solver = new Solver();
    SortArray sortArray = new SortArray();
    CountWord countWord = new CountWord();
    MaxNum maxNum = new MaxNum();
    MaxChar maxChar = new MaxChar();
    Binary binary = new Binary();
    Palindrome palindrome = new Palindrome();

    @Autowired
    public TglSembilanImplementation() {

        sortArray.setInput(ArrayInputs.INPUT1);
        countWord.setInput(StringInputs.INPUT2);
        maxNum.setInput(ArrayInputs.INPUT3);
        maxChar.setInput(StringInputs.INPUT3);
        binary.setInput(IntegerInputs.INPUT1);
        palindrome.setInput(StringInputs.INPUT1);
    }


    public ResponseDTO resolveResponse(String code) {
        ResponseDTO response = new ResponseDTO();
        SolverMethodDTO data;
        try {
            switch(code) {
                case "sort" -> data = solveSorting(this.sortArray);
                case "countword" -> data = solveCountWord(this.countWord);
                case "maxnum" -> data = solveMaxNum(this.maxNum);
                case "maxchar" -> data = solveMaxChar(this.maxChar);
                case "binary" -> data = solveBinary(this.binary);
                case "palindrome" -> data = solvePalindrome(this.palindrome);
                default -> throw new RuntimeException("Code is not valid");
            }
        } catch (Exception e) {
            response.setInput(code);
            response.setData(e.getMessage());
            response.setMessage("Failed");
            response.setStatus("400");

            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            response.setTimestamp(timeStamp);
            return response;
        }

        response.setInput(data.getInput());
        response.setData(data.getSolution());
        response.setMessage("Success");
        response.setStatus("200");

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        response.setTimestamp(timeStamp);
        return response;
    }

    @Override
    public SolverMethodDTO solveSorting(SortArray number1) {
        SolverMethodDTO sdto = new SolverMethodDTO();
        sdto.setInput(Arrays.toString(number1.getInput()));
        sdto.setSolution(solver.sortAscending(number1.getInput()));
        return sdto;
    }

    @Override
    public SolverMethodDTO solveCountWord(CountWord number2) {
        SolverMethodDTO sdto = new SolverMethodDTO();
        sdto.setInput(number2.getInput());
        sdto.setSolution(solver.countWord(number2.getInput()));
        return sdto;
    }

    @Override
    public SolverMethodDTO solveMaxNum(MaxNum number3) {
        SolverMethodDTO sdto = new SolverMethodDTO();
        sdto.setInput(Arrays.toString(number3.getInput()));
        sdto.setSolution(solver.maxNum(number3.getInput()));
        return sdto;
    }

    @Override
    public SolverMethodDTO solveMaxChar(MaxChar number4) {

        SolverMethodDTO sdto = new SolverMethodDTO();
        sdto.setInput(number4.getInput());
        sdto.setSolution(solver.maxCharOccurrence(number4.getInput()));
        return sdto;
    }

    @Override
    public SolverMethodDTO solveBinary(Binary number5) {
        SolverMethodDTO sdto = new SolverMethodDTO();
        sdto.setInput(Integer.toString(number5.getInput()));
        sdto.setSolution(Integer.toString(solver.toBinary(number5.getInput())));
        return sdto;
    }

    @Override
    public SolverMethodDTO solvePalindrome(Palindrome number6) {
        SolverMethodDTO sdto = new SolverMethodDTO();
        sdto.setInput(number6.getInput());
        sdto.setSolution(Boolean.toString(solver.isPalindrome(number6.getInput())));
        return sdto;
    }
}
