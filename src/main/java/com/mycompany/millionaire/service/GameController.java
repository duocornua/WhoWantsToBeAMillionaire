package com.mycompany.millionaire.service;

import com.mycompany.millionaire.dsa.GameHistoryStack;
import com.mycompany.millionaire.dsa.LifelineManager;
import com.mycompany.millionaire.dsa.MoneyLadder;
import com.mycompany.millionaire.dsa.QuestionQueue;
import com.mycompany.millionaire.model.Answer;
import com.mycompany.millionaire.model.GameRound;
import com.mycompany.millionaire.model.PrizeLevel;
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

    public AnswerResult submitAnswer(Answer selectedAnswer) {
        Question question = getCurrentQuestion();
        int level = getCurrentLevel();
        
        boolean correct = (selectedAnswer != null) && selectedAnswer.isCorrect();
        boolean finalQuestion = level == moneyLadder.size();

        history.push(new GameRound(level, question, selectedAnswer));
        
        if (correct) {
            questionQueue.moveToNextQuestion();
        }

        int correctIndex = -1;
        List<Answer> currentAnswers = question.getAnswers();
        for (int i = 0; i < currentAnswers.size(); i++) {
            if (currentAnswers.get(i).isCorrect()) {
                correctIndex = i;
                break;
            }
        }

        return new AnswerResult(correct, finalQuestion, questionQueue.isFinished(), correctIndex);
    }

    public String getQuestionLoopFile() {
        return getCurrentLevel() <= 5 ? "1to5.wav" : "6to15.wav";
    }
    public int getReachedLevel() {
    return history.size();
}

public int getCurrentMoney() {

    PrizeLevel prize = moneyLadder.getPrizeLevel(history.size());

    if (prize == null) {
        return 0;
    }

    return prize.getMoney();
}
}
