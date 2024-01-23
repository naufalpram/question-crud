package com.edts.tdp.batch4.model.tglsepuluh;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SongSolver {

    // method for cleaning lyric from unique chars
    public String clean(String fullLyric) {
        String lyric = fullLyric.toLowerCase();
        return lyric.replaceAll("[-+.^:,!]","");
    }

    // method for counting repetition of words
    public HashMap<String, Integer> countRepetition(String cleanLyric) {
        HashMap<String, Integer> repetitions = new HashMap<>();

        if (cleanLyric != null) {
            for (String word : cleanLyric.split("\\s+")) {
                repetitions.put(word, repetitions.getOrDefault(word, 0) + 1); // add counter to hashmap
            }
        }
        return repetitions;
    }

    public ArrayList<Map.Entry<String, Integer>> getTopThree(HashMap<String, Integer> toBeSorted)
    {
        // create a list from elements of HashMap
        ArrayList<Map.Entry<String, Integer> > listOfEntries =
                new ArrayList<>(toBeSorted.entrySet());

        // sort the list ascending
        Collections.sort(listOfEntries, new Comparator<>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // get three most repeated word
        ArrayList<Map.Entry<String, Integer>> topThree = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            // insert last 3 indexed values to topThree
            Map.Entry<String, Integer> item = listOfEntries.get(listOfEntries.size() - i - 1);
            topThree.add(item);
        }

        return topThree;
    }
}
