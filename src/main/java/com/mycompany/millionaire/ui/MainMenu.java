package com.mycompany.millionaire.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import com.mycompany.millionaire.ui.component.CircleButton;
import com.mycompany.millionaire.ui.component.MenuBackgroundPanel;
import com.mycompany.millionaire.ui.component.SoundToggleButton;

public class MainMenu extends JFrame {

    private static final Color MENU_BLUE = new Color(0, 0, 64);
    private static final Color CARD_BLUE = new Color(7, 18, 100);
    private static final Color GOLD = new Color(255, 204, 0);
    private static final Color WHITE = Color.WHITE;

    public MainMenu() {
        initComponents();
        AudioPlayer.playLoop("menu.wav");
    }

    private void initComponents() {
        setTitle("Who Wants To Be A Millionaire");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel backgroundPanel = new MenuBackgroundPanel();
        backgroundPanel.setLayout(new GridBagLayout());
        setContentPane(backgroundPanel);

        Font btnFont = new Font("Segoe UI", Font.BOLD, 18);
        LineBorder btnBorder = new LineBorder(GOLD, 2, true);

        JButton btnHelp = new CircleButton("?");
        btnHelp.setFont(new Font("Segoe UI", Font.BOLD, 22));
        btnHelp.setBackground(MENU_BLUE);
        btnHelp.setForeground(GOLD);
        btnHelp.setFocusPainted(false);
        btnHelp.setToolTipText("How to play");
        btnHelp.addActionListener(e -> showHowToPlayFrame());

        SoundToggleButton btnMute = new SoundToggleButton();
        btnMute.setBackground(MENU_BLUE);
        btnMute.setForeground(GOLD);
        btnMute.setFocusPainted(false);
        btnMute.setToolTipText("Mute or unmute all game audio");
        btnMute.addActionListener(e -> {
            AudioPlayer.toggleMuted();
            btnMute.repaint();
            if (!AudioPlayer.isMuted()) {
                AudioPlayer.playLoop("menu.wav");
            }
        });

        GridBagConstraints helpGbc = new GridBagConstraints();
        helpGbc.gridx = 0;
        helpGbc.gridy = 0;
        helpGbc.weightx = 1;
        helpGbc.weighty = 1;
        helpGbc.anchor = GridBagConstraints.NORTHEAST;
        helpGbc.insets = new Insets(18, 0, 0, 18);
        backgroundPanel.add(btnHelp, helpGbc);

        GridBagConstraints muteGbc = new GridBagConstraints();
        muteGbc.gridx = 0;
        muteGbc.gridy = 0;
        muteGbc.weightx = 1;
        muteGbc.weighty = 1;
        muteGbc.anchor = GridBagConstraints.NORTHWEST;
        muteGbc.insets = new Insets(18, 18, 0, 0);
        backgroundPanel.add(btnMute, muteGbc);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 15));
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(320, 190));

        JButton btnPlay = createMenuButton("PLAY GAME", btnFont, btnBorder);
        btnPlay.addActionListener(e -> {
            btnPlay.setEnabled(false);
            AudioPlayer.stopLoop();
            AudioPlayer.playOnce("letsplay.wav");
            Timer startGameTimer = new Timer(3000, event -> {
                new GameFrame().setVisible(true);
                dispose();
            });
            startGameTimer.setRepeats(false);
            startGameTimer.start();
        });
        buttonPanel.add(btnPlay);

        JButton btnAbout = createMenuButton("ABOUT", btnFont, btnBorder);
        btnAbout.addActionListener(e -> showAboutFrame());
        buttonPanel.add(btnAbout);

        JButton btnExit = createMenuButton("EXIT", btnFont, btnBorder);
        btnExit.addActionListener(e -> {
            AudioPlayer.stopAll();
            new GoodbyeScreen().showForSeconds(3);
        });
        buttonPanel.add(btnExit);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(300, 0, 0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(buttonPanel, gbc);
    }

    private JButton createMenuButton(String text, Font font, LineBorder border) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(MENU_BLUE);
        button.setForeground(GOLD);
        button.setBorder(border);
        button.setFocusPainted(false);
        return button;
    }

    private void showHowToPlayFrame() {
        JFrame helpFrame = new JFrame("How to Play");
        helpFrame.setSize(560, 500);
        helpFrame.setLocationRelativeTo(this);
        helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = createDialogShell("How to Play");
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);
        content.add(createInfoCard("Goal", "Answer all 15 questions correctly and reach the $1,000,000 prize."));
        content.add(Box.createVerticalStrut(12));
        content.add(createInfoCard("Controls",
                "Click A, B, C, or D to choose an answer.\n"
                + "Click 50/50 once to remove two wrong answers.\n"
                + "Click QUIT to return to the main menu."));
        content.add(Box.createVerticalStrut(12));
        content.add(createInfoCard("Feedback",
                "A correct answer blinks green and moves you forward.\n"
                + "A wrong answer blinks red, shows the correct answer in green, then ends the game."));
        panel.add(content, BorderLayout.CENTER);

        helpFrame.setContentPane(panel);
        helpFrame.setVisible(true);
    }

    private void showAboutFrame() {
        JFrame aboutFrame = new JFrame("About This Project");
        aboutFrame.setSize(820, 720);
        aboutFrame.setLocationRelativeTo(this);
        aboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = createDialogShell("Who Wants To Be A Millionaire");

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(MENU_BLUE);
        content.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));

        content.add(createInfoCard("CSD201 Group Assignment",
                "FPT University | SE2001 - SU26 - CSD201\n"
                + "Group 6 | Java Swing Desktop Game\n"
                + "Mentor: Le Thi Thu Lan"));
        content.add(Box.createVerticalStrut(12));
        content.add(createMembersCard());
        content.add(Box.createVerticalStrut(12));
        content.add(createInfoCard("Game Introduction",
                "Who Wants to be a Millionaire is a Java Swing desktop quiz game inspired by the famous television game show. "
                + "The project practices object-oriented programming, event handling, user interface design, and question management in Java.\n\n"
                + "The player answers 15 multiple-choice questions and climbs a money ladder. Difficulty increases through easy, medium, hard, "
                + "and extra hard question pools."));
        content.add(Box.createVerticalStrut(12));
        content.add(createInfoCard("How to Play",
                "1. Click PLAY GAME from the main menu.\n"
                + "2. Read the question carefully.\n"
                + "3. Choose A, B, C, or D.\n"
                + "4. Use 50/50 once per playthrough to remove two wrong answers.\n"
                + "5. Keep answering correctly to climb the money ladder.\n"
                + "6. Answer question 15 correctly to win the game."));
        content.add(Box.createVerticalStrut(12));
        content.add(createInfoCard("Game Rules",
                "- The game contains 15 questions.\n"
                + "- Questions 1-5 use the Easy question list.\n"
                + "- Questions 6-10 use the Medium question list.\n"
                + "- Questions 11-14 use the Hard question list.\n"
                + "- Question 15 uses the Extra Hard question list.\n"
                + "- One wrong answer ends the game.\n"
                + "- The 50/50 button can only be used once."));
        content.add(Box.createVerticalStrut(12));
        content.add(createInfoCard("Frame Interfaces",
                "The game consists of 3 main frames:\n\n"
                + "1. Main Menu - Play Game, About, Help, Exit, and menu music.\n"
                + "2. About - Project information, group members, rules, and tech stack.\n"
                + "3. Gameplay - Question area, answer buttons, 50/50, Quit, money ladder, logo, and game audio."));
        content.add(Box.createVerticalStrut(12));
        content.add(createDsaCard());
        content.add(Box.createVerticalStrut(12));
        content.add(createTechCard());

        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setBorder(BorderFactory.createLineBorder(GOLD, 1));
        scrollPane.getViewport().setBackground(MENU_BLUE);
        styleHiddenScrollPane(scrollPane);
        panel.add(scrollPane, BorderLayout.CENTER);

        JLabel madeBy = new JLabel("Made by Group 6", SwingConstants.CENTER);
        madeBy.setFont(new Font("Segoe UI", Font.BOLD, 15));
        madeBy.setForeground(GOLD);
        panel.add(madeBy, BorderLayout.SOUTH);

        aboutFrame.setContentPane(panel);
        aboutFrame.setVisible(true);
        SwingUtilities.invokeLater(() -> scrollPane.getViewport().setViewPosition(new Point(0, 0)));
    }

    private JPanel createDialogShell(String titleText) {
        JPanel panel = new JPanel(new BorderLayout(18, 18));
        panel.setBackground(MENU_BLUE);
        panel.setBorder(BorderFactory.createEmptyBorder(28, 34, 22, 34));

        JLabel title = new JLabel(titleText, SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 25));
        title.setForeground(GOLD);
        panel.add(title, BorderLayout.NORTH);
        return panel;
    }

    private JPanel createInfoCard(String heading, String body) {
        JPanel card = new JPanel(new BorderLayout(8, 8));
        card.setBackground(CARD_BLUE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(GOLD, 1, true),
                BorderFactory.createEmptyBorder(14, 16, 14, 16)
        ));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel headingLabel = new JLabel(heading);
        headingLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        headingLabel.setForeground(GOLD);
        card.add(headingLabel, BorderLayout.NORTH);

        if (!body.isBlank()) {
            JTextArea bodyText = new JTextArea(body);
            bodyText.setEditable(false);
            bodyText.setLineWrap(true);
            bodyText.setWrapStyleWord(true);
            bodyText.setOpaque(false);
            bodyText.setForeground(WHITE);
            bodyText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            card.add(bodyText, BorderLayout.CENTER);
        }

        return card;
    }

    private JPanel createMembersCard() {
        String[][] members = {
            {"CE200157", "Vuong Kien Hao", "Member"},
            {"CE201342", "Nguyen Tran Phuc Dang", "Member"},
            {"CE201357", "Le Thuan Thanh", "Member"},
            {"CE180887", "Nguyen The Vinh", "Member"},
            {"CE181696", "Truong Minh Vy", "Member"},
        };
        JPanel card = createInfoCard("Group Members", "");
        card.add(createStyledTable(members, new String[]{"Student ID", "Full Name", "Role"}), BorderLayout.CENTER);
        return card;
    }

    private JPanel createTechCard() {
        String[][] tech = {
            {"Language", "Java"},
            {"UI Framework", "Java Swing"},
            {"Build Tool", "Maven / NetBeans-compatible project structure"},
            {"Course Focus", "Data Structures and Algorithms"},
            {"Main DSA Packages", "model, dsa, service"}
        };
        JPanel card = createInfoCard("Tech Stack", "");
        card.add(createStyledTable(tech, new String[]{"Component", "Technology"}), BorderLayout.CENTER);
        return card;
    }

    private JPanel createDsaCard() {
        String[][] rows = {
            {"ArrayList", "Stores question pools and the 15-level prize ladder"},
            {"EnumMap", "Groups questions by difficulty level"},
            {"Queue", "Keeps the selected questions in play order"},
            {"Stack", "Stores answered rounds for game history"},
            {"Shuffle", "Randomizes question choice and 50/50 removal"}
        };
        JPanel card = createInfoCard("DSA Used In Game", "");
        card.add(createStyledTable(rows, new String[]{"Structure", "Where It Is Used"}), BorderLayout.CENTER);
        return card;
    }

    private JComponent createStyledTable(String[][] rows, String[] columns) {
        JTable table = new JTable(rows, columns);
        table.setRowHeight(28);
        table.setEnabled(false);
        table.setFocusable(false);
        table.setShowGrid(false);
        table.setFillsViewportHeight(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setBackground(CARD_BLUE);
        table.setForeground(WHITE);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setBackground(MENU_BLUE);
        table.getTableHeader().setForeground(GOLD);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setOpaque(false);
        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
        tablePanel.add(table, BorderLayout.CENTER);
        return tablePanel;
    }

    private void styleHiddenScrollPane(JScrollPane scrollPane) {
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);
        scrollPane.getVerticalScrollBar().setBlockIncrement(48);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(12);
        scrollPane.getViewport().putClientProperty("EnableWindowBlit", Boolean.TRUE);
    }
}
