package com.mycompany.millionaire.ui.component;

import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * Panel with the hexagonal shape used for question and timer boxes.
 */
public class HexPanel extends JPanel {

    /**
     * Creates a transparent hex panel.
     */
    public HexPanel() {
        setOpaque(false);
    }

    /**
     * Paints the hexagonal gradient panel and its border.
     *
     * @param g graphics context supplied by Swing
     */
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

    /**
     * Creates a six-point polygon with left and right notches.
     *
     * @param width available width
     * @param height available height
     * @param notch size of each side notch
     * @return polygon representing the hex shape
     */
    public static Polygon createHexShape(int width, int height, int notch) {
        return new Polygon(
                new int[]{notch, width - notch, width, width - notch, notch, 0},
                new int[]{0, 0, height / 2, height, height, height / 2},
                6
        );
    }
}
