package com.edts.tdp.batch4.interfaces;

import com.edts.tdp.batch4.bean.Tgl9.Tgl9DataDTO;
import com.edts.tdp.batch4.model.tglsembilan.*;

public interface TglSembilanInterface {
    Tgl9DataDTO solveSorting(SortArray number1);
    Tgl9DataDTO solveCountWord(CountWord number2);
    Tgl9DataDTO solveMaxNum(MaxNum number3);
    Tgl9DataDTO solveMaxChar(MaxChar number4);
    Tgl9DataDTO solveBinary(Binary number5);
    Tgl9DataDTO solvePalindrome(Palindrome number6);
}
