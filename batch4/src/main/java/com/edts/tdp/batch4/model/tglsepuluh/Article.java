package com.edts.tdp.batch4.model.tglsepuluh;

import org.springframework.stereotype.Component;

@Component
public class Article {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
