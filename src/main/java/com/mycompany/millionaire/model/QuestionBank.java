package com.mycompany.millionaire.model;

import com.mycompany.millionaire.dsa.QuestionPool;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains the built-in question data and builds randomized game sets.
 */
public class QuestionBank {

    /**
     * Creates one complete 15-question game set.
     *
     * @return questions ordered by Easy, Medium, Hard, and Extra Hard levels
     */
    public static ArrayList<Question> getQuestions() {
        QuestionPool pool = createQuestionPool();
        ArrayList<Question> gameQuestions = new ArrayList<>();
        gameQuestions.addAll(pool.pickRandom(QuestionLevel.EASY, 5));
        gameQuestions.addAll(pool.pickRandom(QuestionLevel.MEDIUM, 5));
        gameQuestions.addAll(pool.pickRandom(QuestionLevel.HARD, 4));
        gameQuestions.addAll(pool.pickRandom(QuestionLevel.EXTRA_HARD, 1));
        return gameQuestions;
    }

    /**
     * Creates and fills the question pool by difficulty.
     *
     * @return populated question pool
     */
    private static QuestionPool createQuestionPool() {
        QuestionPool pool = new QuestionPool();
        addQuestions(pool, QuestionLevel.EASY, easyQuestions());
        addQuestions(pool, QuestionLevel.MEDIUM, mediumQuestions());
        addQuestions(pool, QuestionLevel.HARD, hardQuestions());
        addQuestions(pool, QuestionLevel.EXTRA_HARD, extraHardQuestions());
        return pool;
    }

    /**
     * Adds a list of questions to one difficulty level.
     *
     * @param pool destination question pool
     * @param level difficulty level for all questions in the list
     * @param questions questions to add
     */
    private static void addQuestions(QuestionPool pool, QuestionLevel level, List<Question> questions) {
        for (Question question : questions) {
            pool.add(level, question);
        }
    }

    /**
     * Builds the easy question list used for levels 1 through 5.
     *
     * @return easy questions
     */
    private static List<Question> easyQuestions() {
        ArrayList<Question> list = new ArrayList<>();
        // "Question", "CORRECT ANSWER", "Incorrect Answer 1", "Incorrect Answer 2", "Incorrect Answer 3"
        list.add(new Question("What is the capital of France?", "Paris", "London", "Rome", "Berlin"));
        list.add(new Question("How many days are there in a week?", "7", "5", "6", "8"));
        list.add(new Question("Which planet is known as the Red Planet?", "Mars", "Venus", "Earth", "Jupiter"));
        list.add(new Question("Which animal is known as man's best friend?", "Dog", "Cat", "Horse", "Rabbit"));
        list.add(new Question("What color do you get by mixing red and white?", "Pink", "Green", "Purple", "Orange"));
        list.add(new Question("Which language is used for Android development?", "Java", "Python", "C#", "Swift"));
        list.add(new Question("What is 5 + 3?", "8", "6", "7", "9"));
        list.add(new Question("Which ocean is the largest?", "Pacific", "Atlantic", "Indian", "Arctic"));

        list.add(new Question("How many oceans are there in the world?", "5", "3", "4", "6"));
        list.add(new Question("Which of the following rays has the highest penetrating power?", "Gamma rays", "Infrared rays", "X-rays", "Violet rays"));
        list.add(new Question("How many stars are there on the national flag of the United States?", "50", "48", "52", "51"));
        list.add(new Question("In Norse mythology, which god is associated with the power of lightning?", "Thor", "Hades", "Odin", "Zeus"));
        list.add(new Question("Who painted the famous artwork 'The Starry Night'?", "Vincent van Gogh", "Leonardo da Vinci", "Pablo Picasso", "Claude Monet"));
        return list;
    }

    /**
     * Builds the medium question list used for levels 6 through 10.
     *
     * @return medium questions
     */
    private static List<Question> mediumQuestions() {
        ArrayList<Question> list = new ArrayList<>();
        list.add(new Question("Who wrote Romeo and Juliet?", "William Shakespeare", "Charles Dickens", "Mark Twain", "Jane Austen"));
        list.add(new Question("What is the chemical symbol for gold?", "Au", "Ag", "Fe", "Hg"));
        list.add(new Question("Which country hosted the 2016 Summer Olympics?", "Brazil", "China", "Japan", "United Kingdom"));
        list.add(new Question("How many players are on a football team on the field?", "11", "9", "10", "12"));
        list.add(new Question("What is the square root of 144?", "12", "10", "11", "14"));
        list.add(new Question("Which organ pumps blood through the body?", "Heart", "Brain", "Liver", "Lung"));
        list.add(new Question("Which continent is Egypt in?", "Africa", "Asia", "Europe", "South America"));
        list.add(new Question("What is the hardest natural substance on Earth?", "Diamond", "Gold", "Iron", "Quartz"));

        list.add(new Question("Which continent has its mainland situated in all four hemispheres?", "Africa", "Eurasia", "North America", "Australia"));
        list.add(new Question("In which year did the famous Titanic sink?", "1912", "1911", "1913", "1914"));
        list.add(new Question("As of 2020, which video was the most-viewed on the YouTube platform?", "Baby Shark", "Despacito", "Johny Johny Yes Papa", "Bath Song"));
        list.add(new Question("Which of the following elements possesses the hardest natural allotrope?", "Carbon", "Crom", "Vonfram", "Osmi"));
        list.add(new Question("Which of the following animals does NOT have red blood?", "Octopus", "Earthworm", "Chimpanzee", "Shark"));
        return list;
    }

    /**
     * Builds the hard question list used for levels 11 through 14.
     *
     * @return hard questions
     */
    private static List<Question> hardQuestions() {
        ArrayList<Question> list = new ArrayList<>();
        list.add(new Question("Which scientist proposed the theory of general relativity?", "Albert Einstein", "Isaac Newton", "Niels Bohr", "Galileo Galilei"));
        list.add(new Question("What is the smallest prime number?", "2", "0", "1", "3"));
        list.add(new Question("Which year did the first iPhone launch?", "2007", "2005", "2006", "2008"));
        list.add(new Question("What is the capital of Canada?", "Ottawa", "Toronto", "Vancouver", "Montreal"));
        list.add(new Question("Which element has the atomic number 6?", "Carbon", "Oxygen", "Nitrogen", "Helium"));
        list.add(new Question("In computing, what does CPU stand for?", "Central Processing Unit", "Computer Power Utility", "Core Program Unit", "Control Process User"));
        list.add(new Question("Which novel begins with the line 'Call me Ishmael'?", "Moby-Dick", "The Great Gatsby", "Dracula", "The Odyssey"));
        list.add(new Question("Which country has the city of Kyoto?", "Japan", "China", "South Korea", "Thailand"));

        list.add(new Question("The largest pagoda ever built is currently located in which country?", "Vietnam", "India", "China", "Thailand"));
        list.add(new Question("In the novel 'Don Quixote' by Miguel de Cervantes, what does the main character mistake for giants?", "Windmills", "Castles", "Flocks of sheep", "Inns"));
        list.add(new Question("What was the name of the first programmable, electronic, general-purpose digital computer, completed in 1945?", "ENIAC", "UNIVAC", "EDVAC", "Colossus"));
        list.add(new Question("In the human body, which organ is responsible for producing the majority of cholesterol?", "Liver", "Gallbladder", "Pancreas", "Small intestine"));
        list.add(new Question("How many distinct colors can be seen on the official Google Chrome desktop icon?", "5", "4", "6", "7"));
        return list;
    }

    /**
     * Builds the extra hard question list used for level 15.
     *
     * @return extra hard questions
     */
    private static List<Question> extraHardQuestions() {
        ArrayList<Question> list = new ArrayList<>();
        list.add(new Question("Which mathematician is associated with the incompleteness theorems?", "Kurt Godel", "Alan Turing", "John von Neumann", "David Hilbert"));
        list.add(new Question("What is the SI base unit of luminous intensity?", "Candela", "Lux", "Lumen", "Tesla"));
        list.add(new Question("Which ancient library was famously located in Egypt?", "Library of Alexandria", "Library of Pergamum", "Library of Nineveh", "Library of Celsus"));
        list.add(new Question("Which protocol is primarily used to translate domain names into IP addresses?", "DNS", "FTP", "SMTP", "SSH"));

        list.add(new Question("Which of the following is the world's largest island located within a lake?", "Manitoulin Island", "Samosir", "Rene-Levasseur Island", "Ometepe"));
        list.add(new Question("As of 2020, which song by the late musician Michael Jackson has the highest number of views?", "Billie Jean", "Beat It", "Smooth Criminal", "Thriller"));
        list.add(new Question("In the famous Google Chrome offline dinosaur game, what obstacle appears only after the player scores over 400 points?", "Pterodactyls", "Low cacti", "Tall cacti", "Double cacti"));
        return list;
    }
}
