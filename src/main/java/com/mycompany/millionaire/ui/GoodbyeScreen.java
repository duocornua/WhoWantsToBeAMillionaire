package com.mycompany.millionaire.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * Short farewell window shown before the application exits.
 */
public class GoodbyeScreen extends JFrame {

    private static final Color BACKGROUND_BLUE = new Color(0, 0, 64);
    private static final Color GOLD = new Color(255, 204, 0);
    private static final Color WHITE = Color.WHITE;

    /**
     * Creates the goodbye screen.
     */
    public GoodbyeScreen() {
        initComponents();
    }

    /**
     * Builds the farewell message layout.
     */
    private void initComponents() {
        setTitle("Goodbye");
        setSize(new Dimension(640, 280));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(BACKGROUND_BLUE);
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = new JLabel("GOODBYE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 42));
        titleLabel.setForeground(GOLD);
        content.add(titleLabel, BorderLayout.CENTER);

        JLabel messageLabel = new JLabel("See you again!", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        messageLabel.setForeground(WHITE);
        content.add(messageLabel, BorderLayout.SOUTH);

        setContentPane(content);
    }

    /**
     * Shows the screen briefly, then exits the application.
     *
     * @param seconds number of seconds to display the screen
     */
    public void showForSeconds(int seconds) {
        setVisible(true);
        Timer timer = new Timer(seconds * 1000, e -> {
            dispose();
            System.exit(0);
        });
        timer.setRepeats(false);
        timer.start();
    }
}
