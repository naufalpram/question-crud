package com.edts.tdp.batch4.model.tglsembilan;

import org.springframework.stereotype.Component;

@Component
public class CountWord {
    private String input;

    public String getInput() {
        return input;
    }

    public void setInput(String sentence) {
        this.input = sentence;
    }
}
