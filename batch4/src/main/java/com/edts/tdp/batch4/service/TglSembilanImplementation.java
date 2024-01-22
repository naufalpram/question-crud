package com.edts.tdp.batch4.service;

import com.edts.tdp.batch4.bean.ResponseDTO;
import com.edts.tdp.batch4.constant.OutputOpener;
import com.edts.tdp.batch4.constant.inputs.*;
import com.edts.tdp.batch4.interfaces.TglSembilanInterface;
import com.edts.tdp.batch4.model.tglsembilan.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TglSembilanImplementation implements TglSembilanInterface {

    // tanggal 9 cases solver object
    Solver solver;
    SortArray sortArray;
    CountWord countWord;
    MaxNum maxNum;
    MaxChar maxChar;
    Binary binary;
    Palindrome palindrome;

    @Autowired
    public TglSembilanImplementation(@Autowired Solver solver, @Autowired SortArray sortArray,
                                     @Autowired CountWord countWord, @Autowired MaxNum maxNum,
                                     @Autowired MaxChar maxChar, @Autowired Binary binary,
                                     @Autowired Palindrome palindrome) {
        this.solver = solver;
        this.sortArray = sortArray;
        this.countWord = countWord;
        this.maxNum = maxNum;
        this.maxChar = maxChar;
        this.binary = binary;
        this.palindrome = palindrome;

        sortArray.setInput(ArrayInputs.INPUT1);
        countWord.setInput(StringInputs.INPUT2);
        maxNum.setInput(ArrayInputs.INPUT3);
        maxChar.setInput(StringInputs.INPUT3);
        binary.setInput(IntegerInputs.INPUT1);
        palindrome.setInput(StringInputs.INPUT1);
    }


//    public ResponseDTO resolveResponse(String code) {
//        ResponseDTO response = new ResponseDTO();
//        String data;
//        try {
//            if (code == "sort") data = solveSorting(sortArray);
//        } catch (Exception e) {
//
//        }
//    }

    @Override
    public void solveSorting(SortArray number1) {
        System.out.println("Input: " + Arrays.toString(number1.getInput()));
        System.out.println(solver.sortAscending(number1.getInput()));
    }

    @Override
    public void solveCountWord(CountWord number2) {
        System.out.println(OutputOpener.NOMOR2);
        System.out.println("Input: " + number2.getInput());
        System.out.println(solver.countWord(number2.getInput()));
    }

    @Override
    public void solveMaxNum(MaxNum number3) {
        System.out.println(OutputOpener.NOMOR3);
        System.out.println("Input: " + Arrays.toString(number3.getInput()));
        System.out.println(solver.maxNum(number3.getInput()));
    }

    @Override
    public void solveMaxChar(MaxChar number4) {
        System.out.println(OutputOpener.NOMOR4);
        System.out.println("Input: " + number4.getInput());
        System.out.println(solver.maxCharOccurrence(number4.getInput()));
    }

    @Override
    public void solveBinary(Binary number5) {
        System.out.println(OutputOpener.NOMOR5);
        System.out.println("Input: " + number5.getInput());
        System.out.println(solver.toBinary(number5.getInput()));
    }

    @Override
    public void solvePalindrome(Palindrome number6) {
        System.out.println(OutputOpener.NOMOR6);
        System.out.println("Input: " + number6.getInput());
        System.out.println(solver.isPalindrome(number6.getInput()));
    }
}
