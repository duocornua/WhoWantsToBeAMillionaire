package com.mycompany.millionaire.ui.component;

import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class HexPanel extends JPanel {

    public HexPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Polygon shape = createHexShape(getWidth(), getHeight(), 34);
        g2.setPaint(new GradientPaint(0, 0, UiTheme.ANSWER_BLUE_TOP, 0, getHeight(), UiTheme.ANSWER_BLUE_BOTTOM));
        g2.fillPolygon(shape);
        g2.setColor(new java.awt.Color(205, 210, 225));
        g2.setStroke(new BasicStroke(2.2f));
        g2.drawPolygon(shape);
        g2.dispose();
    }

    public static Polygon createHexShape(int width, int height, int notch) {
        return new Polygon(
                new int[]{notch, width - notch, width, width - notch, notch, 0},
                new int[]{0, 0, height / 2, height, height, height / 2},
                6
        );
    }
}
