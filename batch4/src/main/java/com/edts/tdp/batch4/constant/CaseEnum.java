package com.edts.tdp.batch4.constant;

public enum CaseEnum {
    SONG("1"),
    ARTICLE("2"),
    SORTING("3"),
    COUNTWORD("4"),
    MAXNUM("5"),
    MAXCHAR("6"),
    BINARY("7"),
    PALINDROME("8");

    private String caseInput;

    CaseEnum(String caseInput) {
        this.caseInput = caseInput;
    }

    public String getCaseInput() {
        return caseInput;
    }
}
