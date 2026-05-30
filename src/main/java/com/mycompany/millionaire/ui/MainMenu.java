package com.mycompany.millionaire.ui;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Who Wants To Be A Millionaire");
        setSize(800, 600); // Increased window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on screen

        // Create a custom panel to draw the background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the background image from the root project folder
                ImageIcon imageIcon = new ImageIcon("logo.png");
                Image image = imageIcon.getImage();
                // Draw the image to scale dynamically with the window size
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        // Game title
        JLabel lblTitle = new JLabel("WHO WANTS TO BE A MILLIONAIRE", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTitle.setForeground(Color.WHITE); // Set text color to white for contrast
        lblTitle.setBorder(BorderFactory.createEmptyBorder(60, 0, 40, 0));
        backgroundPanel.add(lblTitle, BorderLayout.NORTH);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 20, 20));
        buttonPanel.setOpaque(false); // Make transparent to show the background image
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(100, 250, 120, 250));

        // Play button
        JButton btnPlay = new JButton("Play Game");
        btnPlay.setFont(new Font("Segoe UI", Font.BOLD, 22));
        btnPlay.setFocusPainted(false);
        btnPlay.addActionListener(e -> {
            new GameFrame().setVisible(true);
            this.dispose(); // Close the Main Menu window
        });
        buttonPanel.add(btnPlay);

        // Exit button
        JButton btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Segoe UI", Font.BOLD, 22));
        btnExit.setFocusPainted(false);
        btnExit.addActionListener(e -> System.exit(0));
        buttonPanel.add(btnExit);

        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
    }
}
