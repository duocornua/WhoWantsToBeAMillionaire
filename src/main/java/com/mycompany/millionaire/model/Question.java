package com.mycompany.millionaire.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents one quiz question and its shuffled answer options.
 */
public class Question {

    private final String questionText;
    private final List<Answer> answers;

    /**
     * Creates a question with one correct answer and three wrong answers.
     *
     *
     * The answers are shuffled during construction so the correct option is not
     * always in the same button position.
     *
     * @param questionText question shown to the player
     * @param correctInfo correct answer text
     * @param wrong1 first wrong answer text
     * @param wrong2 second wrong answer text
     * @param wrong3 third wrong answer text
     */
    public Question(String questionText, String correctInfo, String wrong1, String wrong2, String wrong3) {
        this.questionText = questionText;
        this.answers = new ArrayList<>();

        this.answers.add(new Answer(correctInfo, true));
        this.answers.add(new Answer(wrong1));
        this.answers.add(new Answer(wrong2));
        this.answers.add(new Answer(wrong3));

        Collections.shuffle(this.answers);
    }

    /**
     * Gets the question text.
     *
     * @return question prompt
     */
    public String getQuestion() {
        return questionText;
    }

    /**
     * Gets the shuffled answer options.
     *
     * @return answer list in display order
     */
    public List<Answer> getAnswers() {
        return answers;
    }

    /**
     * Checks whether the answer at a display index is correct.
     *
     * @param selectedIndex index chosen by the player
     * @return {@code true} when the index exists and contains the right answer
     */
    public boolean isAnswerCorrect(int selectedIndex) {
        if (selectedIndex >= 0 && selectedIndex < answers.size()) {
            return answers.get(selectedIndex).isCorrect();
        }
        return false;
    }
}
