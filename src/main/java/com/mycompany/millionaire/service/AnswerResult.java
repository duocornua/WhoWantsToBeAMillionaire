package com.mycompany.millionaire.service;

public class AnswerResult {

    private final boolean correct;
    private final boolean finalQuestion;
    private final boolean gameFinished;
    private final int correctAnswer;

    public AnswerResult(boolean correct, boolean finalQuestion, boolean gameFinished, int correctAnswer) {
        this.correct = correct;
        this.finalQuestion = finalQuestion;
        this.gameFinished = gameFinished;
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public boolean isFinalQuestion() {
        return finalQuestion;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
