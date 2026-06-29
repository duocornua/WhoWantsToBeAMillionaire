package com.mycompany.millionaire.model;

/**
 * Represents one answer option for a multiple-choice question.
 */
public class Answer {

    private final String info;
    private final boolean isCorrect;

    /**
     * Creates an incorrect answer option.
     *
     * @param info text shown on the answer button
     */
    public Answer(String info) {
        this(info, false);
    }

    /**
     * Creates an answer option and marks whether it is correct.
     *
     * @param info text shown on the answer button
     * @param isCorrect {@code true} when this option is the right answer
     */
    public Answer(String info, boolean isCorrect) {
        this.info = info;
        this.isCorrect = isCorrect;
    }

    /**
     * Gets the text of the answer.
     *
     * @return answer text
     */
    public String getInfo() {
        return info;
    }

    /**
     * Checks whether this answer is correct.
     *
     * @return {@code true} if this answer is correct
     */
    public boolean isCorrect() {
        return isCorrect;
    }
}
