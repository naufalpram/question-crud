package com.edts.tdp.batch4.interfaces;

import com.edts.tdp.batch4.bean.SolverMethodDTO;
import com.edts.tdp.batch4.model.tglsembilan.*;

public interface TglSembilanInterface {
    SolverMethodDTO solveSorting(SortArray number1);
    SolverMethodDTO solveCountWord(CountWord number2);
    SolverMethodDTO solveMaxNum(MaxNum number3);
    SolverMethodDTO solveMaxChar(MaxChar number4);
    SolverMethodDTO solveBinary(Binary number5);
    SolverMethodDTO solvePalindrome(Palindrome number6);
}
