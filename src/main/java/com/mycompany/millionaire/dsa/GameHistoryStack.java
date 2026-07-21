package com.mycompany.millionaire.dsa;

import com.mycompany.millionaire.model.GameRound;
import java.util.Stack;

/**
 * Stores the rounds that the player has already answered.
 *
 * The stack structure makes the most recent answered round easy to inspect if
 * the game later needs to show review or rollback information.
 */
public class GameHistoryStack {

    private final Stack<GameRound> answeredRounds = new Stack<>();

    /**
     * Adds a completed round to the top of the history stack.
     *
     * @param round round that was just answered
     */
    public void push(GameRound round) {
        answeredRounds.push(round);
    }

    /**
     * Returns the number of answered rounds.
     *
     * @return total answered rounds
     */
    public int size() {
        return answeredRounds.size();
    }

    /**
     * Counts how many answered rounds were correct.
     *
     * @return number of correct answers stored in the history
     */
    public int countCorrect() {
        int correctCount = 0;

        for (GameRound round : answeredRounds) {
            if (round.isCorrect()) {
                correctCount++;
            }
        }

        return correctCount;
    }

    /**
     * Checks whether the player has answered any rounds.
     *
     * @return {@code true} when the history has no rounds
     */
    public boolean isEmpty() {
        return answeredRounds.isEmpty();
    }

    /**
     * Reads the latest answered round without removing it.
     *
     * @return most recent answered round
     */
    public GameRound peek() {
        return answeredRounds.peek();
    }
}
