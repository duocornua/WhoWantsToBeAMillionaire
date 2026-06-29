package com.mycompany.millionaire.ui.component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import javax.swing.JButton;

/**
 * Custom hexagonal answer button used for A/B/C/D choices.
 */
public class MillionaireButton extends JButton {

    private final String letter;
    private String answer = "";
    private Color blinkColor;
    private boolean staticReveal;

    /**
     * Creates an answer button with its answer letter prefix.
     *
     * @param letter answer letter, such as {@code A:}
     */
    public MillionaireButton(String letter) {
        this.letter = letter;
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setForeground(UiTheme.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 24));
        setPreferredSize(new Dimension(360, 86));
    }

    /**
     * Updates the answer text drawn inside the custom button.
     *
     * @param answer answer text without the A/B/C/D prefix
     */
    public void setAnswerText(String answer) {
        this.answer = answer;
        setText(letter + " " + answer);
    }

    /**
     * Sets the temporary fill color used for feedback blinking.
     *
     * @param blinkColor highlight color, or {@code null} for the normal color
     */
    public void setBlinkColor(Color blinkColor) {
        this.blinkColor = blinkColor;
        repaint();
    }

    /**
     * Keeps the selected wrong answer visibly marked during reveal animation.
     *
     * @param staticReveal {@code true} to draw the selected button as revealed
     */
    public void setStaticReveal(boolean staticReveal) {
        this.staticReveal = staticReveal;
        repaint();
    }

    /**
     * Paints the hexagonal answer button, border, letter, and fitted answer.
     *
     * @param g graphics context supplied by Swing
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Polygon shape = HexPanel.createHexShape(getWidth(), getHeight(), 38);

        if (blinkColor != null) {
            g2.setColor(blinkColor);
            g2.fillPolygon(shape);
        } else {
            g2.setPaint(new GradientPaint(0, 0, UiTheme.ANSWER_BLUE_TOP, 0, getHeight(), UiTheme.ANSWER_BLUE_BOTTOM));
            g2.fillPolygon(shape);
        }

        if (staticReveal) {
            g2.setColor(UiTheme.GOLD);
            g2.setStroke(new BasicStroke(2.2f));
        } else if (blinkColor == UiTheme.GREEN) {
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(3.5f));
        } else {
            g2.setColor(new Color(205, 210, 225));
            g2.setStroke(new BasicStroke(2.2f));
        }
        g2.drawPolygon(shape);

        Color letterColor = UiTheme.GOLD;
        Color textColor = UiTheme.WHITE;
        if (blinkColor == UiTheme.GREEN || staticReveal) {
            letterColor = Color.BLACK;
            textColor = Color.BLACK;
        }

        Font letterFont = getFont().deriveFont(Font.BOLD, 34f);
        g2.setFont(letterFont);
        g2.setColor(letterColor);
        FontMetrics letterMetrics = g2.getFontMetrics();
        int centerY = (getHeight() + letterMetrics.getAscent() - letterMetrics.getDescent()) / 2;
        g2.drawString(letter, 52, centerY);

        g2.setFont(getFont());
        g2.setColor(textColor);
        drawFittedText(g2, answer, 142, centerY, getWidth() - 170);
        g2.dispose();
    }

    /**
     * Draws text with a smaller font when needed so it fits the answer button.
     *
     * @param g2 graphics context
     * @param text text to draw
     * @param x left position
     * @param baseline text baseline
     * @param maxWidth maximum available width
     */
    private void drawFittedText(Graphics2D g2, String text, int x, int baseline, int maxWidth) {
        Font original = g2.getFont();
        FontMetrics metrics = g2.getFontMetrics();
        while (metrics.stringWidth(text) > maxWidth && g2.getFont().getSize() > 14) {
            g2.setFont(g2.getFont().deriveFont((float) g2.getFont().getSize() - 1));
            metrics = g2.getFontMetrics();
        }
        g2.drawString(text, x, baseline);
        g2.setFont(original);
    }
}
