package com.mycompany.millionaire.dsa;

import com.mycompany.millionaire.model.Question;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QuestionQueue {

    private final Queue<Question> waitingQuestions = new LinkedList<>();
    private Question currentQuestion;

    public QuestionQueue(List<Question> gameQuestions) {
        waitingQuestions.addAll(gameQuestions);
        currentQuestion = waitingQuestions.poll();
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void moveToNextQuestion() {
        currentQuestion = waitingQuestions.poll();
    }

    public boolean isFinished() {
        return currentQuestion == null;
    }
}
