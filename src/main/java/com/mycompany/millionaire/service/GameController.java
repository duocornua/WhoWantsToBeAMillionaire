package com.mycompany.millionaire.service;

import com.mycompany.millionaire.dsa.GameHistoryStack;
import com.mycompany.millionaire.dsa.LifelineManager;
import com.mycompany.millionaire.dsa.MoneyLadder;
import com.mycompany.millionaire.dsa.QuestionQueue;
import com.mycompany.millionaire.model.Answer; // IMPORT THÊM ĐỐI TƯỢNG ANSWER
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

    // Lưu ý: Có thể hàm useFiftyFifty bên trong lớp LifelineManager cũng cần cập nhật 
    // nếu nó đang dùng kiểu dữ liệu cũ. Hiện tại giữ nguyên theo file bạn gửi.
    public List<Integer> useFiftyFifty() {
        return lifeline.useFiftyFifty(getCurrentQuestion());
    }

    // SỬA TẠI ĐÂY: Đổi tham số truyền vào từ "int selectedAnswer" sang "Answer selectedAnswer"
    public AnswerResult submitAnswer(Answer selectedAnswer) {
        Question question = getCurrentQuestion();
        int level = getCurrentLevel();
        
        // HỎI TRỰC TIẾP ĐỐI TƯỢNG ĐÁP ÁN: Mày có phải đáp án đúng không?
        boolean correct = (selectedAnswer != null) && selectedAnswer.isCorrect();
        boolean finalQuestion = level == moneyLadder.size();

        // Nạp vào lịch sử theo đúng Constructor mới của GameRound
        history.push(new GameRound(level, question, selectedAnswer));
        
        if (correct) {
            questionQueue.moveToNextQuestion();
        }

        // Đoạn này trả về AnswerResult: Nếu class AnswerResult cũ của bạn hoặc các class khác 
        // vẫn cần cái index đúng (int) để hiển thị màu xanh/đỏ cho nút bấm, 
        // ta có thể tìm index của đáp án đúng trong list để trả về.
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
}