package com.mycompany.millionaire.dsa;

import com.mycompany.millionaire.model.Question;
import com.mycompany.millionaire.model.QuestionLevel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Groups all available questions by difficulty and selects random subsets.
 */
public class QuestionPool {

    private final Map<QuestionLevel, ArrayList<Question>> questionsByLevel = new EnumMap<>(QuestionLevel.class);

    /**
     * Creates an empty list for every supported question difficulty.
     */
    public QuestionPool() {
        for (QuestionLevel level : QuestionLevel.values()) {
            questionsByLevel.put(level, new ArrayList<>());
        }
    }

    /**
     * Adds a question to the pool for one difficulty level.
     *
     * @param level difficulty bucket that receives the question
     * @param question question to store
     */
    public void add(QuestionLevel level, Question question) {
        questionsByLevel.get(level).add(question);
    }

    /**
     * Randomly selects up to {@code count} questions from one level.
     *
     * @param level difficulty bucket to use
     * @param count maximum number of questions to return
     * @return shuffled subset of questions
     */
    public List<Question> pickRandom(QuestionLevel level, int count) {
        ArrayList<Question> shuffledQuestions = new ArrayList<>(questionsByLevel.get(level));
        Collections.shuffle(shuffledQuestions);
        return shuffledQuestions.subList(0, Math.min(count, shuffledQuestions.size()));
    }
}
