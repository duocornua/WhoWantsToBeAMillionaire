package com.mycompany.millionaire.service;

import com.mycompany.millionaire.dsa.GameHistoryStack;
import com.mycompany.millionaire.dsa.LifelineManager;
import com.mycompany.millionaire.dsa.MoneyLadder;
import com.mycompany.millionaire.dsa.QuestionQueue;
import com.mycompany.millionaire.model.GameRound;
import com.mycompany.millionaire.model.Question;
import com.mycompany.millionaire.model.QuestionBank;
import java.util.List;

public class GameController {

    private final QuestionQueue questionQueue;
    private final GameHistoryStack history = new GameHistoryStack();
    private final LifelineManager lifeline = new LifelineManager();
    private final MoneyLadder moneyLadder = new MoneyLadder();

    public GameController() {
        questionQueue = new QuestionQueue(QuestionBank.getQuestions());
    }

    public Question getCurrentQuestion() {
        return questionQueue.getCurrentQuestion();
    }

    public int getCurrentLevel() {
        return history.size() + 1;
    }

    public MoneyLadder getMoneyLadder() {
        return moneyLadder;
    }

    public boolean isLifelineUsed() {
        return lifeline.isUsed();
    }

    public List<Integer> useFiftyFifty() {
        return lifeline.useFiftyFifty(getCurrentQuestion());
    }

    public AnswerResult submitAnswer(int selectedAnswer) {
        Question question = getCurrentQuestion();
        int level = getCurrentLevel();
        boolean correct = selectedAnswer == question.getCorrectAnswer();
        boolean finalQuestion = level == moneyLadder.size();

        history.push(new GameRound(level, question, selectedAnswer, correct));
        if (correct) {
            questionQueue.moveToNextQuestion();
        }

        return new AnswerResult(correct, finalQuestion, questionQueue.isFinished(), question.getCorrectAnswer());
    }

    public String getQuestionLoopFile() {
        return getCurrentLevel() <= 5 ? "1to5.wav" : "6to15.wav";
    }
}
