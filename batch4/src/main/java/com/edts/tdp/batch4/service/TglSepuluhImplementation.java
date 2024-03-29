package com.edts.tdp.batch4.service;

import com.edts.tdp.batch4.bean.Tgl10.Tgl10DataDTO;
import com.edts.tdp.batch4.bean.Tgl10.Tgl10ResponseDTO;
import com.edts.tdp.batch4.constant.inputs.ArticleInputs;
import com.edts.tdp.batch4.constant.inputs.SongInputs;
import com.edts.tdp.batch4.interfaces.TglSepuluhInterface;
import com.edts.tdp.batch4.model.tglsepuluh.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class TglSepuluhImplementation implements TglSepuluhInterface {

    // models and solver methods
    SongSolver songSolver; // song case solver object
    ArticleSolver articleSolver; // article case solver object
    Song song;
    Article article;

    @Autowired
    public TglSepuluhImplementation(@Autowired SongSolver songSolver, @Autowired ArticleSolver articleSolver,
                                    @Autowired Song song, @Autowired Article article) {
        this.songSolver = songSolver;
        this.articleSolver = articleSolver;
        this.song = song;
        this.article = article;
        // set inputs
        this.song.setFullLyric(SongInputs.INPUT1);
        this.article.setText(ArticleInputs.INPUT1);
    }

    // response resolver to map which case to solve
    public Tgl10ResponseDTO resolveAnswer(String code) {
        Tgl10ResponseDTO response = new Tgl10ResponseDTO();
        Tgl10DataDTO data;

        try {
            if (code.equals("song")) data = solveSong(song);
            else if (code.equals("article")) data = solveArticle(article);
            else throw new RuntimeException("Code is not valid");
        } catch (Exception e) {
            response.setInput(code);
            response.setData(null);
            response.setMessage("Failed");
            response.setStatus(HttpStatus.BAD_REQUEST.value());

            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            response.setTimestamp(timeStamp);
            return response;
        }
        response.setInput(data.getInput());
        response.setData(data.getCounter());
        response.setMessage("Success");
        response.setStatus(HttpStatus.OK.value());

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        response.setTimestamp(timeStamp);
        return response;
    }

    @Override
    public Tgl10DataDTO solveSong(Song song) {
        String cleanedLyric = songSolver.clean(song.getFullLyric()); // clean lyric from unique chars
        HashMap<String, Integer> repetition = songSolver.countRepetition(cleanedLyric); // count repetition of words
        ArrayList<Map.Entry<String, Integer>> topThree = songSolver.getTopThree(repetition); // get top three words most repeated

        Map<String, Integer> output = new HashMap<>();
        for (Map.Entry<String, Integer> entry : topThree) { // concat word counters to output
            output.put(entry.getKey(), entry.getValue());
        }

        Tgl10DataDTO rdto = new Tgl10DataDTO();
        rdto.setInput(song.getFullLyric());
        rdto.setCounter(output);
        return rdto;
    }

    @Override
    public Tgl10DataDTO solveArticle(Article article) {
        articleSolver.count(article); // count lowercase, uppercase, digit, and others
        Tgl10DataDTO rdto = new Tgl10DataDTO();
        rdto.setInput(article.getText());
        rdto.setCounter(articleSolver.getAllCount());
        return rdto;
    }
}
