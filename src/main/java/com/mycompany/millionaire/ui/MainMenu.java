package com.mycompany.millionaire.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainMenu extends javax.swing.JFrame {

    public MainMenu() {
        initComponents();
        AudioPlayer.playLoop("menu.wav");
    }

    private void initComponents() {
        setTitle("Who Wants To Be A Millionaire");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Custom panel for authentic background scaling
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("MainMenu.png");
                Image image = imageIcon.getImage();

                int pWidth = getWidth();
                int pHeight = getHeight();
                int iWidth = image.getWidth(this);
                int iHeight = image.getHeight(this);

                if (iWidth > 0 && iHeight > 0) {
                    double scale = Math.max((double) pWidth / iWidth, (double) pHeight / iHeight);
                    int drawWidth = (int) (iWidth * scale);
                    int drawHeight = (int) (iHeight * scale);

                    int x = (pWidth - drawWidth) / 2;
                    int y = (pHeight - drawHeight) / 2;

                    g.drawImage(image, x, y, drawWidth, drawHeight, this);
                }
            }
        };
        // Using GridBagLayout for precise component positioning
        backgroundPanel.setLayout(new GridBagLayout());
        setContentPane(backgroundPanel);

        // Panel for menu buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 0, 15)); // 15px vertical gap between buttons
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(320, 190)); // Elegant button cluster size

        // Theme colors matching the real TV show (Dark Blue & Gold)
        Color btnBgColor = new Color(0, 0, 64);
        Color btnTextColor = new Color(255, 204, 0);
        LineBorder btnBorder = new LineBorder(btnTextColor, 2, true);
        Font btnFont = new Font("Segoe UI", Font.BOLD, 18);

        // Play Game button configuration
        JButton btnPlay = new JButton("PLAY GAME");
        btnPlay.setFont(btnFont);
        btnPlay.setBackground(btnBgColor);
        btnPlay.setForeground(btnTextColor);
        btnPlay.setBorder(btnBorder);
        btnPlay.setFocusPainted(false);
        btnPlay.addActionListener(e -> {
            btnPlay.setEnabled(false);
            AudioPlayer.stopLoop();
            AudioPlayer.playOnce("letsplay.wav");
            Timer startGameTimer = new Timer(3000, event -> {
                new GameFrame().setVisible(true);
                this.dispose();
            });
            startGameTimer.setRepeats(false);
            startGameTimer.start();
        });
        buttonPanel.add(btnPlay);

        // About button configuration
        JButton btnAbout = new JButton("ABOUT");
        btnAbout.setFont(btnFont);
        btnAbout.setBackground(btnBgColor);
        btnAbout.setForeground(btnTextColor);
        btnAbout.setBorder(btnBorder);
        btnAbout.setFocusPainted(false);
        btnAbout.addActionListener(e -> showAboutFrame());
        buttonPanel.add(btnAbout);

        // Exit button configuration
        JButton btnExit = new JButton("EXIT");
        btnExit.setFont(btnFont);
        btnExit.setBackground(btnBgColor);
        btnExit.setForeground(btnTextColor);
        btnExit.setBorder(btnBorder);
        btnExit.setFocusPainted(false);
        btnExit.addActionListener(e -> {
            AudioPlayer.stopAll();
            System.exit(0);
        });
        buttonPanel.add(btnExit);

        // GridBagConstraints to align the button panel neatly under the central logo
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(300, 0, 0, 0); // Margin top to push buttons downward
        gbc.anchor = GridBagConstraints.CENTER;

        backgroundPanel.add(buttonPanel, gbc);
    }

    private void showAboutFrame() {
        JFrame aboutFrame = new JFrame("About This Project");
        aboutFrame.setSize(780, 720);
        aboutFrame.setLocationRelativeTo(this);
        aboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(18, 18));
        panel.setBackground(new Color(0, 0, 64));
        panel.setBorder(BorderFactory.createEmptyBorder(28, 34, 22, 34));

        JLabel title = new JLabel("Who Wants To Be A Millionaire", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 25));
        title.setForeground(new Color(255, 204, 0));
        panel.add(title, BorderLayout.NORTH);

        JTextArea description = new JTextArea(getAboutDescription());
        description.setEditable(false);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setOpaque(true);
        description.setBackground(new Color(0, 0, 64));
        description.setForeground(Color.WHITE);
        description.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        description.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(description);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 0), 1));
        scrollPane.getViewport().setBackground(new Color(0, 0, 64));
        panel.add(scrollPane, BorderLayout.CENTER);

        JLabel madeBy = new JLabel("Made by Group 6", SwingConstants.CENTER);
        madeBy.setFont(new Font("Segoe UI", Font.BOLD, 15));
        madeBy.setForeground(new Color(255, 204, 0));
        panel.add(madeBy, BorderLayout.SOUTH);

        aboutFrame.setContentPane(panel);
        aboutFrame.setVisible(true);
    }

    private String getAboutDescription() {
        return """
                Who Wants to be a Millionaire Game — CSD201 Group Assignment

               FPT University | SE2001 – SU26 – CSD201
               Group 6 | Java Swing Desktop Game
               Lê Thị Thu Lan | Mentor
               ------------------------------------------------------------

               1. Group Members

               Student ID | Full Name             
               CE200157   | Vương Kiến Hào        
               CE201342   | Nguyễn Trần Phúc Đăng 
               CE201357   | Lê Thuận Thành        
               CE180887   | Nguyễn Thế Vinh       
               CE181696   | Trương Minh Vỹ        
               ------------------------------------------------------------

               2. Game Introduction

               Who Wants to be a Millionaire Game is a Java Swing desktop game inspired by the famous television quiz show. The project was developed as a CSD201 group assignment to practice object-oriented programming, event handling, user interface design, and basic data organization in Java.

               In this game, the player answers a sequence of multiple-choice questions and climbs a 15-level money ladder. Each question has four possible answers, but only one answer is correct. The difficulty increases as the player moves forward, creating a simple but exciting quiz experience similar to the original game show.

               ------------------------------------------------------------

               ️3. How to Play

               1. Open the game and choose PLAY GAME from the main menu.
               2. Read the question shown on the screen.
               3. Choose one answer from A, B, C, or D.
               4. Use the 50/50 support button when needed. It removes two wrong answers from the current question.
               5. Answer correctly to move to the next question and highlight the next prize level.
               6. Answer all 15 questions correctly to win the top prize.
               7. Choose QUIT during gameplay if you want to return to the main menu.

               ------------------------------------------------------------

               4. Game Rules

               - The game has 15 questions in one playthrough.
               - Questions 1–5 are selected from the Easy question list.
               - Questions 6–10 are selected from the Medium question list.
               - Questions 11–14 are selected from the Hard question list.
               - Question 15 is selected from the Extra Hard question list.
               - Each question has four answer choices: A, B, C, and D.
               - The player can use 50/50 only once per playthrough.
               - A correct answer moves the player to the next question.
               - A wrong answer ends the game and returns the player to the main menu.
               - Winning question 15 completes the game.

               ------------------------------------------------------------

               ️5. Frame Interfaces

               The game consists of 3 main frames:

               1. Main Menu — Play Game, About, Exit, and menu background music.
               2. About — Project introduction, group member information, gameplay guide, rules, interface summary, and tech stack.
               3. Gameplay — Question display, four answer buttons, 50/50 support button, Quit button, money ladder, logo display, and gameplay audio.

               ------------------------------------------------------------

               ️6. Tech Stack

               Language | Java
               UI Framework | Java Swing
               Build Tool | Maven / NetBeans-compatible project structure
               Data Structure Course | CSD201
               """;
    }
}
