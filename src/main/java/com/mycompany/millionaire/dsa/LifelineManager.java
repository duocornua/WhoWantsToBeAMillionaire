package com.mycompany.millionaire.dsa;

import com.mycompany.millionaire.model.Answer; 
import com.mycompany.millionaire.model.Question;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LifelineManager {

    private boolean used;

    public List<Integer> useFiftyFifty(Question question) {
        if (used) {
            return Collections.emptyList();
        }

        ArrayList<Integer> wrongAnswers = new ArrayList<>();

        List<Answer> answers = question.getAnswers();

        for (int i = 0; i < answers.size(); i++) {
            if (!answers.get(i).isCorrect()) {
                wrongAnswers.add(i);
            }
        }

        Collections.shuffle(wrongAnswers);
        used = true;
        
        // Trả về 2 vị trí đáp án sai để giao diện tiến hành ẩn đi
        return wrongAnswers.subList(0, Math.min(2, wrongAnswers.size()));
    }

    public boolean isUsed() {
        return used;
    }
}