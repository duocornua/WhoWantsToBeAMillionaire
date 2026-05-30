package com.mycompany.millionaire.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        initComponents();
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
                ImageIcon imageIcon = new ImageIcon("logo.png");
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
        buttonPanel.setLayout(new GridLayout(2, 1, 0, 15)); // 15px vertical gap between buttons
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(320, 120)); // Elegant button cluster size

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
            new GameFrame().setVisible(true);
            this.dispose(); 
        });
        buttonPanel.add(btnPlay);

        // Exit button configuration
        JButton btnExit = new JButton("EXIT");
        btnExit.setFont(btnFont);
        btnExit.setBackground(btnBgColor);
        btnExit.setForeground(btnTextColor);
        btnExit.setBorder(btnBorder);
        btnExit.setFocusPainted(false);
        btnExit.addActionListener(e -> System.exit(0));
        buttonPanel.add(btnExit);

        // GridBagConstraints to align the button panel neatly under the central logo
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(220, 0, 0, 0); // Margin top to push buttons downward
        gbc.anchor = GridBagConstraints.CENTER;
        
        backgroundPanel.add(buttonPanel, gbc);
    }
}