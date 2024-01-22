package com.edts.tdp.batch4.model.tglsepuluh;

public class ArticleSolver {

    private int upperCaseCount = 0;
    private int lowerCaseCount = 0;
    private int digitCount = 0;
    private int otherCount = 0;
    // method for converting string to array of chars
    private char[] getChars(String text) {
        return text.toCharArray();
    }

    // method to count lowercase, uppercase, digit, and other
    public void count(Article article) {
        char[] chars = getChars(article.getText());

        for (char ch : chars) { // check each char
            if (Character.isLowerCase(ch)) lowerCaseCount++;
            else if (Character.isUpperCase(ch)) upperCaseCount++;
            else if (Character.isDigit(ch)) digitCount++;
            else otherCount++;
        }
    }

    public int getLowerCaseCount() {
        return lowerCaseCount;
    }

    public int getUpperCaseCount() {
        return upperCaseCount;
    }

    public int getDigitCount() {
        return digitCount;
    }

    public int getOtherCount() {
        return otherCount;
    }
}
