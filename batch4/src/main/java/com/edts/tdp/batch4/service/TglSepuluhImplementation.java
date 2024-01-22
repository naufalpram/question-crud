package com.edts.tdp.batch4.service;

import com.edts.tdp.batch4.constant.OutputOpener;
import com.edts.tdp.batch4.interfaces.TglSepuluhInterface;
import com.edts.tdp.batch4.model.tglsepuluh.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class TglSepuluhImplementation implements TglSepuluhInterface {

    private final SongSolver songSolver = new SongSolver(); // song case solver object

    private final ArticleSolver articleSolver = new ArticleSolver(); // article case solver object

    @Override
    public void solveSong(Song song) {
        String cleanedLyric = songSolver.clean(song.getFullLyric()); // clean lyric from unique chars
        HashMap<String, Integer> repetition = songSolver.countRepetition(cleanedLyric); // count repetition of words
        ArrayList<Map.Entry<String, Integer>> topThree = songSolver.getTopThree(repetition); // get top three words most repeated

        String output = "";
        for (Map.Entry<String, Integer> entry : topThree) { // concat word counters to output
            output += "\n " + entry.getKey() + ": " + entry.getValue();
        }
        System.out.println(OutputOpener.NOMORSONG + output);
    }

    @Override
    public void solveArticle(Article article) {
        articleSolver.count(article); // count lowercase, uppercase, digit, and others

        System.out.println(OutputOpener.NOMORARTICLE);
        System.out.println("Lowercase: " + articleSolver.getLowerCaseCount());
        System.out.println("Uppercase: " + articleSolver.getUpperCaseCount());
        System.out.println("Digit: " + articleSolver.getDigitCount());
        System.out.println("Other: " + articleSolver.getOtherCount());
    }
}
