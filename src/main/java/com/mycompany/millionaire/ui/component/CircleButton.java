package com.mycompany.millionaire.ui.component;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

public class CircleButton extends JButton {

    public CircleButton(String text) {
        super(text);
        setPreferredSize(new Dimension(48, 48));
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillOval(2, 2, getWidth() - 4, getHeight() - 4);
        g2.setColor(UiTheme.MENU_GOLD);
        g2.setStroke(new BasicStroke(2.2f));
        g2.drawOval(2, 2, getWidth() - 5, getHeight() - 5);
        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
    }
}
