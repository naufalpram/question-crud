package com.edts.tdp.batch4.model.tglsembilan;

import java.util.Arrays;
import java.util.HashMap;

public class Solver {

    // solver method sorting
    public String sortAscending(int[] arr) {
        int temp; // temporary variable for swapping
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                // if current number bigger than next number, then swap
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        return Arrays.toString(arr);
    }

    // solver method countword
    public String countWord(String sentence) {
        String[] words = sentence.split(" "); // split string by whitespace separator
        return Integer.toString(words.length);
    }

    // solver method maxnum
    public String maxNum(int[] arr) {
        int max = Integer.MIN_VALUE; // assign smallest integer number
        for (int num : arr) {
            if (num > max) max = num; // check if current num is bigger than max
        }
        return Integer.toString(max);
    }

    // solver method maxchar
    public String maxCharOccurrence(String word) {
        // Hashmap with Character as key and Integer as value pair
        HashMap<Character, Integer> occurrences = new HashMap<>();

        // Insert characters counter
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            // getOrDefault -> if value exist, return value. otherwise, return default value (0)
            occurrences.put(letter, occurrences.getOrDefault(letter, 0) + 1);
        }

        Character maxChar = null;
        for (char c : occurrences.keySet()) { // loop every key from occurrences keySet
            if (maxChar == null) {
                maxChar = c;
            }
            else {
                // check if current char occurrence is bigger than maxChar occurrence
                maxChar = (occurrences.get(maxChar) < occurrences.get(c)) ? c : maxChar;
            }
        }
        return maxChar.toString();
    }

    // solver method tobinary
    public int toBinary(int decimal) {
        if (decimal == 0) return 0;
        // modulo 2 of decimal + 10 * recursion modulo 2 of decimal/2
        // concat every modulo from bottom to top
        return (decimal % 2 + 10 * toBinary(decimal/2));
    }

    // solver method palindrome
    public boolean isPalindrome(String word) {
        String lowerCaseWord = word.toLowerCase();
        String reversed = "";
        for (int i = lowerCaseWord.length()-1; i >= 0; i--) { // reverse word
            reversed += lowerCaseWord.charAt(i);
        }
        return lowerCaseWord.equals(reversed); // check if word is still the same when reversed
    }
}
