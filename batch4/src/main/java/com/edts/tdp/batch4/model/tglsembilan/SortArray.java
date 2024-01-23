package com.edts.tdp.batch4.model.tglsembilan;

import org.springframework.stereotype.Component;

@Component
public class SortArray {
    private int[] input;

    public int[] getInput() {
        return input;
    }

    public void setInput(int[] arrayOfInt) {
        this.input = arrayOfInt;
    }
}
