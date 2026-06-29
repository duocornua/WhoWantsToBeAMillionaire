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

/**
 * Coordinates the game rules between the UI and the data structures.
 */
public class GameController {

    private final QuestionQueue questionQueue;
    private final GameHistoryStack history = new GameHistoryStack();
    private final LifelineManager lifeline = new LifelineManager();
    private final MoneyLadder moneyLadder = new MoneyLadder();

    /**
     * Creates a new game controller with a fresh randomized question queue.
     */
    public GameController() {
        questionQueue = new QuestionQueue(QuestionBank.getQuestions());
    }

    /**
     * Gets the question currently waiting for an answer.
     *
     * @return current question, or {@code null} when the game is complete
     */
    public Question getCurrentQuestion() {
        return questionQueue.getCurrentQuestion();
    }

    /**
     * Gets the current 1-based question level.
     *
     * @return next level to answer
     */
    public int getCurrentLevel() {
        return history.size() + 1;
    }

    /**
     * Gets the prize ladder used by the game.
     *
     * @return money ladder
     */
    public MoneyLadder getMoneyLadder() {
        return moneyLadder;
    }

    /**
     * Checks whether the 50/50 lifeline is already used.
     *
     * @return {@code true} after the player uses 50/50
     */
    public boolean isLifelineUsed() {
        return lifeline.isUsed();
    }

    /**
     * Applies the 50/50 lifeline to the current question.
     *
     * @return answer indexes that the UI should hide
     */
    public List<Integer> useFiftyFifty() {
        return lifeline.useFiftyFifty(getCurrentQuestion());
    }

    /**
     * Submits the selected answer, records the round, and advances if correct.
     *
     * @param selectedAnswer answer chosen by the player, or {@code null} when
     * time expires
     * @return result details needed by the UI animation
     */
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

    /**
     * Chooses the background loop audio file for the current level range.
     *
     * @return audio file name for the current question loop
     */
    public String getQuestionLoopFile() {
        return getCurrentLevel() <= 5 ? "1to5.wav" : "6to15.wav";
    }

    /**
     * Gets the highest level that has been answered.
     *
     * @return number of answered rounds
     */
    public int getReachedLevel() {
        return history.size();
    }

    /**
     * Gets the money associated with the latest answered level.
     *
     * @return current prize money, or {@code 0} before any answer
     */
    public int getCurrentMoney() {

        PrizeLevel prize = moneyLadder.getPrizeLevel(history.size());

        if (prize == null) {
            return 0;
        }

        return prize.getMoney();
    }
}
