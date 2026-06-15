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

**Who Wants to be a Millionaire Game** is a desktop computer game running on the Java Swing platform, inspired by the famous television quiz show. This is a reimplementation built with **Java Swing** as a group assignment for the CSD201 Data Structures and Algorithms course to practice object-oriented programming, event handling, user interface design, and basic data organization in Java.

In this game, the player answers a sequence of multiple-choice questions and climbs a money ladder consisting of 15 levels. Each question has four potential answers, but only one answer is correct. The difficulty increases as the player moves forward, creating a simple but exciting quiz experience similar to the original game show.

---

## 📷 Screenshots

| | |
|---|---|
| ![1](https://github.com/user-attachments/assets/51de7d80-a3c1-4f85-a366-44efb8cad2fd) | ![2](https://github.com/user-attachments/assets/bb9cb0f4-73d0-4b75-80cf-b7fdd0c7f5da) |
| ![3](https://github.com/user-attachments/assets/684fb948-accf-4a9e-90a7-52e2e667e434) | ![4](https://github.com/user-attachments/assets/08d9eb87-31c8-4acc-96e7-06770bd97979) |

---

## 🕹️ How to Play

Use your mouse to interact and select functions on the game screen.

| Key / Action | Direction / Function |
|---|---|
| Select `PLAY GAME` | Start the game from the main menu |
| Read the question | View the question content displayed on the screen |
| Choose `A`, `B`, `C`, or `D` | Select one of the four answers |
| Select the `50/50` button | Use the lifeline support when needed |
| Answer correctly | Advance to the next question and increase the prize level |
| Answer 15 questions correctly | Win the game and receive the highest prize |
| Select `QUIT` | Exit the game during gameplay to return to the main menu |

---

## 📋 Game Rules

- A complete playthrough includes a total of **15 questions**.
- The system classifies question difficulty according to the following levels:
  - **Questions 1–5**: Selected from the Easy question list.
  - **Questions 6–10**: Selected from the Medium question list.
  - **Questions 11–14**: Selected from the Hard question list.
  - **Question 15**: Selected from the Extra Hard question list.
- Each question always comes with four answer choices: A, B, C, and D.
- The player can only use the **50/50** lifeline **once per playthrough** (removes two wrong answers).
- A correct answer moves the player to the next question.
- A wrong answer will **end the game immediately** and return the player to the main menu.
- Correctly answering question 15 completes and wins the game.

---

## Data Structures and Algorithms

The project is organized so the DSA part is visible in the source code instead of being hidden inside the Swing frame.
| Structure / Algorithm | File | Purpose |
|---|---|---|
| `ArrayList` | `QuestionPool`, `MoneyLadder` | Stores question lists and prize levels. |
| `EnumMap` | `QuestionPool` | Groups questions by difficulty: Easy, Medium, Hard, Extra Hard. |
| `Collections.shuffle()` | `QuestionPool`, `LifelineManager` | Randomizes question selection and 50/50 answer removal. |
| `Queue` | `QuestionQueue` | Stores the selected 15 questions in the order the player will receive them. |
| `Stack` | `GameHistoryStack` | Saves answered rounds so the game has a history of player choices. |
| Linear search | `LifelineManager` | Finds wrong answers before hiding two of them. |

The main game flow is handled by `GameController`. The UI asks the controller for the current question, submits answers, and updates the screen based on the returned result.

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
|   |-- GameRound.java
|   |-- PrizeLevel.java
|   |-- Question.java
|   |-- QuestionBank.java
|   `-- QuestionLevel.java
|-- service
|   |-- AnswerResult.java
|   `-- GameController.java
`-- ui
    |-- AudioPlayer.java
    |-- GameFrame.java
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

## ⚙️ Tech Stack

| Component | Technology |
|---|---|
| Language | Java |
| UI Framework | Java Swing |
| Build Tool | Maven / NetBeans-compatible project structure |
| Data Structure Course | CSD201 |

---

## 🚀 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/duocornua/WhoWantsToBeAMillionaire.git
   ```
2. Open the project in **NetBeans IDE**.
3. Build and run the project (`F6`).

> **Requirements:** JDK 8+ and NetBeans IDE (or any Java-compatible IDE).

---

## 📄 License

This project is developed for educational purposes as part of the CSD201 course at FPT University. All rights reserved by Group 6 — SE2001 SU26.
