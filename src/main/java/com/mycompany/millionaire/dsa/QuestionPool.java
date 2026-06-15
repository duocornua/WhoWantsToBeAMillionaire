package com.mycompany.millionaire.dsa;

import com.mycompany.millionaire.model.Question;
import com.mycompany.millionaire.model.QuestionLevel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class QuestionPool {

    private final Map<QuestionLevel, ArrayList<Question>> questionsByLevel = new EnumMap<>(QuestionLevel.class);

    public QuestionPool() {
        for (QuestionLevel level : QuestionLevel.values()) {
            questionsByLevel.put(level, new ArrayList<>());
        }
    }

    public void add(QuestionLevel level, Question question) {
        questionsByLevel.get(level).add(question);
    }

    public List<Question> pickRandom(QuestionLevel level, int count) {
        ArrayList<Question> shuffledQuestions = new ArrayList<>(questionsByLevel.get(level));
        Collections.shuffle(shuffledQuestions);
        return shuffledQuestions.subList(0, Math.min(count, shuffledQuestions.size()));
    }
}
