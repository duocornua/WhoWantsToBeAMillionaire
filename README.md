# 🎮 Who Wants to be a Millionaire Game — CSD201 Group Assignment

> **FPT University** | SE2001 – SU26 – CSD201  
> **Group 6** | Java Swing Desktop Game
> 
> **Mentor** : Lê Thị Thu Lan

---

## 👥 Group Members

| Student ID | Full Name | Role |
|---|---|---|
| CE200157 | Vương Kiến Hào | Member |
| CE201342 | Nguyễn Trần Phúc Đăng | Member |
| CE201357 | Lê Thuận Thành | Member |
| CE180887 | Nguyễn Thế Vinh | Member |
| CE181696 | Trương Minh Vỹ | Member |

---

## 📖 Game Introduction

**Who Wants to be a Millionaire Game** is a desktop quiz game built with Java Swing and inspired by the well-known television game show. The project is a CSD201 Data Structures and Algorithms group assignment that practices object-oriented programming, event handling, custom Swing UI drawing, file persistence, and basic data organization in Java.

The player answers 15 multiple-choice questions and climbs a money ladder from `$100` to `$1,000,000`. Each question has four answers, only one is correct, and every round has a 60-second timer. Difficulty increases through Easy, Medium, Hard, and Extra Hard question pools.

---

## 📷 Screenshots

| | |
|---|---|
| ![1](https://github.com/user-attachments/assets/f037b849-a384-4bf2-ae72-b19228add288) | ![2](https://github.com/user-attachments/assets/7ea8c9ba-b566-497c-8d3a-0fd6017f170f) |
| ![3](https://github.com/user-attachments/assets/201c9ab9-3abb-4081-a41c-f4c1551ab71b) | ![4](https://github.com/user-attachments/assets/0715c3ce-45c2-4f88-bcb8-903d7a183a2b) |

---

## Current Features

- Main menu with Play Game, Leaderboard, About, Help, Exit, and audio mute controls.
- Gameplay screen with a question area, custom answer buttons, timer, money ladder, 50/50 lifeline, quit confirmation, logo, and sound effects.
- 50/50 lifeline that can be used once per game to hide two wrong answers.
- Leaderboard that saves the top 10 results to `leaderboard.txt`.
- About frame with project information, members, rules, data structures, and tech stack.
- Short goodbye screen shown before exiting the application.

---

## 🕹️ How to Play

| Action | Result |
|---|---|
| Click `PLAY GAME` | Start a new game from the main menu. |
| Read the question | Review the current multiple-choice question. |
| Choose `A`, `B`, `C`, or `D` | Submit one answer. |
| Click `50:50` | Remove two wrong answers once per playthrough. |
| Watch the timer | Answer before the 60-second timer reaches zero. |
| Answer correctly | Move to the next question and climb the money ladder. |
| Answer incorrectly or time out | End the game and save the result. |
| Answer question 15 correctly | Win the game and reach the highest prize. |

---

## 📋 Game Rules

- A complete playthrough contains 15 questions.
- Questions 1-5 are selected from the Easy question list.
- Questions 6-10 are selected from the Medium question list.
- Questions 11-14 are selected from the Hard question list.
- Question 15 is selected from the Extra Hard question list.
- Each question has four answer choices.
- The player has 60 seconds to answer each question.
- The 50/50 lifeline can only be used once per playthrough.
- A wrong answer or timeout ends the game immediately.
- The leaderboard ranks results by money, then level, then fastest play time.

---

## Data Structures and Algorithms

The project keeps the DSA logic visible in the source code instead of hiding it inside the Swing frame.

| Structure / Algorithm | File | Purpose |
|---|---|---|
| `ArrayList` | `QuestionPool`, `MoneyLadder`, `LeaderboardManager` | Stores question lists, prize levels, and leaderboard entries. |
| `EnumMap` | `QuestionPool` | Groups questions by difficulty: Easy, Medium, Hard, Extra Hard. |
| `Collections.shuffle()` | `QuestionPool`, `LifelineManager`, `Question` | Randomizes question selection, answer order, and 50/50 answer removal. |
| `Queue` | `QuestionQueue` | Stores selected questions in gameplay order. |
| `Stack` | `GameHistoryStack` | Saves answered rounds as game history. |
| Linear search | `LifelineManager`, `GameController` | Finds wrong answers for 50/50 and finds the correct answer index for feedback. |
| Sorting | `LeaderboardEntry`, `LeaderboardManager` | Sorts leaderboard entries by score, level, and play time. |

The main game flow is handled by `GameController`. The UI asks the controller for the current question, submits answers, and updates the screen using the returned `AnswerResult`.

---

## Project Structure

```text
src/main/java/com/mycompany/millionaire
|-- Millionaire.java
|-- dsa
|   |-- GameHistoryStack.java
|   |-- LifelineManager.java
|   |-- MoneyLadder.java
|   |-- QuestionPool.java
|   `-- QuestionQueue.java
|-- model
|   |-- Answer.java
|   |-- GameRound.java
|   |-- LeaderboardEntry.java
|   |-- PrizeLevel.java
|   |-- Question.java
|   |-- QuestionBank.java
|   `-- QuestionLevel.java
|-- service
|   |-- AnswerResult.java
|   |-- GameController.java
|   `-- LeaderboardManager.java
`-- ui
    |-- AudioPlayer.java
    |-- GameFrame.java
    |-- GoodbyeScreen.java
    |-- LeaderboardFrame.java
    |-- MainMenu.java
    `-- component
        |-- BackgroundPanel.java
        |-- CircleButton.java
        |-- HexPanel.java
        |-- LogoPanel.java
        |-- MenuBackgroundPanel.java
        |-- MillionaireButton.java
        |-- MoneyLadderPanel.java
        |-- SoundToggleButton.java
        `-- UiTheme.java
```

---

## Resources

```text
src/main/resources/audio
|-- 1to5.wav
|-- 6to15.wav
|-- correct.wav
|-- letsplay.wav
|-- menu.wav
|-- win.wav
`-- wrong.wav
```

The root folder also contains `logo.png`, `MainMenu.png`, and `leaderboard.txt`.

---

## ⚙️ Tech Stack

| Component | Technology |
|---|---|
| Language | Java |
| Runtime target | Java 21 |
| UI framework | Java Swing |
| Build tool | Maven |
| Course focus | Data Structures and Algorithms |

---

## 🚀 How to Run
1. Open the project in NetBeans or another Java IDE.
2. Build the Maven project.
3. Run the main class:

1. Clone the repository:
   ```bash
   git clone https://github.com/duocornua/WhoWantsToBeAMillionaire.git
   ```
2. Open the project in **NetBeans IDE**.
3. Build and run the project (`F6`).

> **Requirements:** JDK 8+ and NetBeans IDE (or any Java-compatible IDE).

---

## 📄 License

This project is developed for educational purposes as part of the CSD201 course at FPT University. All rights reserved by Group 6 - SE2001 SU26.
