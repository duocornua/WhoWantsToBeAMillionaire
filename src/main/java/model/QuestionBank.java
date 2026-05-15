package model;

import java.util.ArrayList;

public class QuestionBank {

    public static ArrayList<Questions> getQuestions() {

        ArrayList<Questions> list = new ArrayList<>();

        list.add(new Questions(
                "What is the capital of France?",
                new String[]{"London", "Paris", "Rome", "Berlin"},
                1
        ));

        list.add(new Questions(
                "Which language is used for Android?",
                new String[]{"Python", "Java", "C#", "Swift"},
                1
        ));

        return list;
    }
}