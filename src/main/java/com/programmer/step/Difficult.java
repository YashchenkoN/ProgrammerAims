package com.programmer.step;

public enum Difficult {

    EASY("easy"), MEDIUM("medium"), HARD("hard"), NODIFFICULT("nodifficult");

    private String value;

    private Difficult(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

};