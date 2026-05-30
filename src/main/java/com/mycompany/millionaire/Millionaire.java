package com.mycompany.millionaire;

import com.mycompany.millionaire.ui.MainMenu;

public class Millionaire {

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            // Launch the main menu first
            new MainMenu().setVisible(true);
        });

    }
}
