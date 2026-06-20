package com.mycompany.millionaire.model;

public class Answer {
    private final String info;
    private final boolean isCorrect; 

    public Answer(String info) {
        this(info, false);
    }
    
    public Answer(String info, boolean isCorrect) {
        this.info = info;
        this.isCorrect = isCorrect;
    }

    public String getInfo() {
        return info;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}