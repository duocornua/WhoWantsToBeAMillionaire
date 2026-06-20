package com.mycompany.millionaire.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {

    private final String questionText;
    private final List<Answer> answers;

    public Question(String questionText, String correctInfo, String wrong1, String wrong2, String wrong3) {
        this.questionText = questionText;
        this.answers = new ArrayList<>();
        
        this.answers.add(new Answer(correctInfo, true));
        this.answers.add(new Answer(wrong1));
        this.answers.add(new Answer(wrong2));
        this.answers.add(new Answer(wrong3));
        
        Collections.shuffle(this.answers);
    }

    public String getQuestion() {
        return questionText;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public boolean isAnswerCorrect(int selectedIndex) {
        if (selectedIndex >= 0 && selectedIndex < answers.size()) {
            return answers.get(selectedIndex).isCorrect();
        }
        return false;
    }
}