# 🎮 Who Wants to be a Millionaire Game — CSD201 Group Assignment

> **FPT University** | SE2001 – SU26 – CSD201  
> **Group 6** | Java Swing Desktop Game

---

## 👥 Group Members

| Student ID | Full Name | Role |
|---|---|---|
| CE200157 | Vương Kiến Hào | Member |
| CE201342 | Nguyễn Trần Phúc Đăng | Member |
| CE201357 | Lê Thuận Thành | Member |
| CE180887 | Nguyễn Thế Vinh | Member |
| CE181696 | Trương Minh Vỹ | Member |
| — | Lê Thị Thu Lan | Mentor |

---

## 📖 Game Introduction

**Who Wants to be a Millionaire Game** is a desktop computer game running on the Java Swing platform, inspired by the famous television quiz show. This is a reimplementation built with **Java Swing** as a group assignment for the CSD201 Data Structures and Algorithms course to practice object-oriented programming, event handling, user interface design, and basic data organization in Java.

In this game, the player answers a sequence of multiple-choice questions and climbs a money ladder consisting of 15 levels. Each question has four potential answers, but only one answer is correct. The difficulty increases as the player moves forward, creating a simple but exciting quiz experience similar to the original game show.

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

## 🖼️ Frame Interfaces

The game consists of **3 main frames**:

1. **Main Menu** — Contains Play Game, About, Exit functions, and menu background music.
2. **About** — Project introduction, team member information, gameplay guide, rules, interface summary, and tech stack.
3. **Gameplay** — Question display, four answer buttons, 50/50 lifeline button, Quit button, money ladder, logo display, and gameplay audio.

---

## ⚙️ Tech Stack

| Component | Technology |
|---|---|
| Language | Java |
| UI Framework | Java Swing |
| Build Tool | Maven / NetBeans-compatible project structure |
| Data Structure Course | CSD201 |

---

## 🗂️ Project Structure
