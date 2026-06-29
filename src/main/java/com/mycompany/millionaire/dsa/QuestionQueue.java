package com.mycompany.millionaire.dsa;

import com.mycompany.millionaire.model.Question;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Holds the selected questions in the order they will be shown.
 */
public class QuestionQueue {

    private final Queue<Question> waitingQuestions = new LinkedList<>();
    private Question currentQuestion;

    /**
     * Creates a queue and immediately loads the first current question.
     *
     * @param gameQuestions ordered list of questions for this playthrough
     */
    public QuestionQueue(List<Question> gameQuestions) {
        waitingQuestions.addAll(gameQuestions);
        currentQuestion = waitingQuestions.poll();
    }

    /**
     * Gets the question currently displayed to the player.
     *
     * @return current question, or {@code null} after all questions are
     * answered
     */
    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    /**
     * Advances the queue to the next question.
     */
    public void moveToNextQuestion() {
        currentQuestion = waitingQuestions.poll();
    }

    /**
     * Checks whether no current question remains.
     *
     * @return {@code true} when the game has consumed every question
     */
    public boolean isFinished() {
        return currentQuestion == null;
    }
}
