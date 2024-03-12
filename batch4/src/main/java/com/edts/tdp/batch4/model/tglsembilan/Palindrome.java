package com.edts.tdp.batch4.model.tglsembilan;

import org.springframework.stereotype.Component;

@Component
public class Palindrome {
    private String input;

    public String getInput() {
        return input;
    }

    public void setInput(String word) {
        this.input = word;
    }
}
