package com.mycompany.millionaire.model;

public class GameRound {

    private final int level;
    private final Question question;
    private final Answer selectedAnswer;

    public GameRound(int level, Question question, Answer selectedAnswer) {
        this.level = level;
        this.question = question;
        this.selectedAnswer = selectedAnswer;
    }

    public int getLevel() {
        return level;
    }

    public Question getQuestion() {
        return question;
    }

    public Answer getSelectedAnswer() {
        return selectedAnswer;
    }

    public boolean isCorrect() {
        return selectedAnswer != null && selectedAnswer.isCorrect();
    }
}