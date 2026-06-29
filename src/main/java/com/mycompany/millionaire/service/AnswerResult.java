package com.mycompany.millionaire.service;

/**
 * Describes the result after the player submits an answer.
 */
public class AnswerResult {

    private final boolean correct;
    private final boolean finalQuestion;
    private final boolean gameFinished;
    private final int correctAnswer;

    /**
     * Creates an immutable answer result for the UI.
     *
     * @param correct {@code true} when the submitted answer is correct
     * @param finalQuestion {@code true} when the answered question is level 15
     * @param gameFinished {@code true} when there are no questions left
     * @param correctAnswer index of the correct answer in the displayed list
     */
    public AnswerResult(boolean correct, boolean finalQuestion, boolean gameFinished, int correctAnswer) {
        this.correct = correct;
        this.finalQuestion = finalQuestion;
        this.gameFinished = gameFinished;
        this.correctAnswer = correctAnswer;
    }

    /**
     * Checks whether the answer was correct.
     *
     * @return {@code true} for a correct answer
     */
    public boolean isCorrect() {
        return correct;
    }

    /**
     * Checks whether the answered question was the final question.
     *
     * @return {@code true} on level 15
     */
    public boolean isFinalQuestion() {
        return finalQuestion;
    }

    /**
     * Checks whether the game has no more questions.
     *
     * @return {@code true} when the player has completed the question queue
     */
    public boolean isGameFinished() {
        return gameFinished;
    }

    /**
     * Gets the display index of the correct answer.
     *
     * @return zero-based correct answer index
     */
    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
