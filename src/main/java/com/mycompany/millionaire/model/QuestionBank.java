package com.mycompany.millionaire.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionBank {

    public static ArrayList<Question> getQuestions() {
        ArrayList<Question> gameQuestions = new ArrayList<>();
        gameQuestions.addAll(pickQuestions(easyQuestions(), 5));
        gameQuestions.addAll(pickQuestions(mediumQuestions(), 5));
        gameQuestions.addAll(pickQuestions(hardQuestions(), 4));
        gameQuestions.addAll(pickQuestions(extraHardQuestions(), 1));
        return gameQuestions;
    }

    private static List<Question> pickQuestions(List<Question> questions, int count) {
        ArrayList<Question> copy = new ArrayList<>(questions);
        Collections.shuffle(copy);
        return copy.subList(0, Math.min(count, copy.size()));
    }

    private static List<Question> easyQuestions() {
        ArrayList<Question> list = new ArrayList<>();
        list.add(new Question("What is the capital of France?", new String[]{"London", "Paris", "Rome", "Berlin"}, 1));
        list.add(new Question("How many days are there in a week?", new String[]{"5", "6", "7", "8"}, 2));
        list.add(new Question("Which planet is known as the Red Planet?", new String[]{"Mars", "Venus", "Earth", "Jupiter"}, 0));
        list.add(new Question("Which animal is known as man's best friend?", new String[]{"Cat", "Dog", "Horse", "Rabbit"}, 1));
        list.add(new Question("What color do you get by mixing red and white?", new String[]{"Pink", "Green", "Purple", "Orange"}, 0));
        list.add(new Question("Which language is used for Android development?", new String[]{"Python", "Java", "C#", "Swift"}, 1));
        list.add(new Question("What is 5 + 3?", new String[]{"6", "7", "8", "9"}, 2));
        list.add(new Question("Which ocean is the largest?", new String[]{"Atlantic", "Indian", "Arctic", "Pacific"}, 3));
        return list;
    }

    private static List<Question> mediumQuestions() {
        ArrayList<Question> list = new ArrayList<>();
        list.add(new Question("Who wrote Romeo and Juliet?", new String[]{"Charles Dickens", "William Shakespeare", "Mark Twain", "Jane Austen"}, 1));
        list.add(new Question("What is the chemical symbol for gold?", new String[]{"Ag", "Au", "Fe", "Hg"}, 1));
        list.add(new Question("Which country hosted the 2016 Summer Olympics?", new String[]{"China", "Brazil", "Japan", "United Kingdom"}, 1));
        list.add(new Question("How many players are on a football team on the field?", new String[]{"9", "10", "11", "12"}, 2));
        list.add(new Question("What is the square root of 144?", new String[]{"10", "11", "12", "14"}, 2));
        list.add(new Question("Which organ pumps blood through the body?", new String[]{"Brain", "Heart", "Liver", "Lung"}, 1));
        list.add(new Question("Which continent is Egypt in?", new String[]{"Asia", "Africa", "Europe", "South America"}, 1));
        list.add(new Question("What is the hardest natural substance on Earth?", new String[]{"Gold", "Iron", "Diamond", "Quartz"}, 2));
        return list;
    }

    private static List<Question> hardQuestions() {
        ArrayList<Question> list = new ArrayList<>();
        list.add(new Question("Which scientist proposed the theory of general relativity?", new String[]{"Isaac Newton", "Albert Einstein", "Niels Bohr", "Galileo Galilei"}, 1));
        list.add(new Question("What is the smallest prime number?", new String[]{"0", "1", "2", "3"}, 2));
        list.add(new Question("Which year did the first iPhone launch?", new String[]{"2005", "2006", "2007", "2008"}, 2));
        list.add(new Question("What is the capital of Canada?", new String[]{"Toronto", "Vancouver", "Ottawa", "Montreal"}, 2));
        list.add(new Question("Which element has the atomic number 6?", new String[]{"Carbon", "Oxygen", "Nitrogen", "Helium"}, 0));
        list.add(new Question("In computing, what does CPU stand for?", new String[]{"Central Processing Unit", "Computer Power Utility", "Core Program Unit", "Control Process User"}, 0));
        list.add(new Question("Which novel begins with the line 'Call me Ishmael'?", new String[]{"Moby-Dick", "The Great Gatsby", "Dracula", "The Odyssey"}, 0));
        list.add(new Question("Which country has the city of Kyoto?", new String[]{"China", "Japan", "South Korea", "Thailand"}, 1));
        return list;
    }

    private static List<Question> extraHardQuestions() {
        ArrayList<Question> list = new ArrayList<>();
        list.add(new Question("Which mathematician is associated with the incompleteness theorems?", new String[]{"Alan Turing", "Kurt Godel", "John von Neumann", "David Hilbert"}, 1));
        list.add(new Question("What is the SI base unit of luminous intensity?", new String[]{"Candela", "Lux", "Lumen", "Tesla"}, 0));
        list.add(new Question("Which ancient library was famously located in Egypt?", new String[]{"Library of Pergamum", "Library of Alexandria", "Library of Nineveh", "Library of Celsus"}, 1));
        list.add(new Question("Which protocol is primarily used to translate domain names into IP addresses?", new String[]{"FTP", "DNS", "SMTP", "SSH"}, 1));
        return list;
    }
}
