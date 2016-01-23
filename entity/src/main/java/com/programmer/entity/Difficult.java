package com.programmer.entity;

public enum Difficult {

    EASY("easy"), MEDIUM("medium"), HARD("hard"), NODIFFICULT("nodifficult");

    private String value;

    private Difficult(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Difficult parse(String difficult) {
        switch (difficult) {
            case "easy":
                return EASY;
            case "medium":
                return MEDIUM;
            case "hard":
                return HARD;
            default:
                return NODIFFICULT;
        }
    }

}