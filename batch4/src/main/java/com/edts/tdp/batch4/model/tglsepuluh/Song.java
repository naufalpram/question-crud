package com.edts.tdp.batch4.model.tglsepuluh;

import org.springframework.stereotype.Component;

@Component
public class Song {
    private String fullLyric;

    public String getFullLyric() {
        return this.fullLyric;
    }
    public void setFullLyric(String lyric) {
        this.fullLyric = lyric;
    }
}
