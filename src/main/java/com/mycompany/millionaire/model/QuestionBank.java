package com.mycompany.millionaire.model;

import java.util.ArrayList;

public class QuestionBank {

    public static ArrayList<Question> getQuestions() {

        ArrayList<Question> list = new ArrayList<>();

        list.add(new Question(
                "What is the capital of France?",
                new String[]{"London", "Paris", "Rome", "Berlin"},
                1
        ));

        list.add(new Question(
                "Which language is used for Android?",
                new String[]{"Python", "Java", "C#", "Swift"},
                1
        ));

        return list;
    }
}
