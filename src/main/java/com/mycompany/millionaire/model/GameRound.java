package com.mycompany.millionaire.model;

public class GameRound {

    private final int level;
    private final Question question;
    private final int selectedAnswer;
    private final boolean correct;

    public GameRound(int level, Question question, int selectedAnswer, boolean correct) {
        this.level = level;
        this.question = question;
        this.selectedAnswer = selectedAnswer;
        this.correct = correct;
    }

    public int getLevel() {
        return level;
    }

    public Question getQuestion() {
        return question;
    }

    public int getSelectedAnswer() {
        return selectedAnswer;
    }

    public boolean isCorrect() {
        return correct;
    }
}
