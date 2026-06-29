package com.mycompany.millionaire.dsa;

import com.mycompany.millionaire.model.Answer;
import com.mycompany.millionaire.model.Question;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Controls the 50/50 lifeline for one game session.
 */
public class LifelineManager {

    private boolean used;

    /**
     * Uses the 50/50 lifeline and returns two wrong answer positions to hide.
     *
     * @param question current question shown to the player
     * @return indexes of wrong answers that should be hidden, or an empty list
     * if the lifeline has already been used
     */
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

        // Return the two incorrect answer positions so the interface can hide them.
        return wrongAnswers.subList(0, Math.min(2, wrongAnswers.size()));
    }

    /**
     * Checks whether this lifeline has already been used.
     *
     * @return {@code true} after the player uses 50/50
     */
    public boolean isUsed() {
        return used;
    }
}
