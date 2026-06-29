package com.mycompany.millionaire;

import com.mycompany.millionaire.ui.MainMenu;

/**
 * Entry point for the Millionaire desktop game.
 */
public class Millionaire {

    /**
     * Starts the Swing application on the Event Dispatch Thread.
     *
     * @param args command-line arguments, currently unused
     */
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            // Launch the main menu first
            new MainMenu().setVisible(true);
        });

    }
}
