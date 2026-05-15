package com.mycompany.millionaire;
import com.mycompany.millionaire.ui.GameFrame;


public class Millionaire {

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            new GameFrame().setVisible(true);
        });

    }
}