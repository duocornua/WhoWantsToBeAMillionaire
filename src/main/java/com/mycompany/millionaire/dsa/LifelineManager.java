package com.mycompany.millionaire.dsa;

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
        for (int i = 0; i < question.getOptions().length; i++) {
            if (i != question.getCorrectAnswer()) {
                wrongAnswers.add(i);
            }
        }

        Collections.shuffle(wrongAnswers);
        used = true;
        return wrongAnswers.subList(0, Math.min(2, wrongAnswers.size()));
    }

    public boolean isUsed() {
        return used;
    }
}
