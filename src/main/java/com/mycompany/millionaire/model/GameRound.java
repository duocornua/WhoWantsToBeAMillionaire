package com.mycompany.millionaire.model;

/**
 * Stores the question, selected answer, and level for one answered round.
 */
public class GameRound {

    private final int level;
    private final Question question;
    private final Answer selectedAnswer;

    /**
     * Creates a record of one player answer.
     *
     * @param level 1-based question level
     * @param question question that was answered
     * @param selectedAnswer answer selected by the player, or {@code null} when
     * time expires
     */
    public GameRound(int level, Question question, Answer selectedAnswer) {
        this.level = level;
        this.question = question;
        this.selectedAnswer = selectedAnswer;
    }

    /**
     * Gets the level for this round.
     *
     * @return 1-based question level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the question that was answered.
     *
     * @return question for this round
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * Gets the answer chosen by the player.
     *
     * @return selected answer, or {@code null} if no answer was chosen
     */
    public Answer getSelectedAnswer() {
        return selectedAnswer;
    }

    /**
     * Checks whether the selected answer was correct.
     *
     * @return {@code true} only when the player selected the correct answer
     */
    public boolean isCorrect() {
        return selectedAnswer != null && selectedAnswer.isCorrect();
    }
}
