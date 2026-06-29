package com.mycompany.millionaire.ui.component;

import com.mycompany.millionaire.dsa.MoneyLadder;
import com.mycompany.millionaire.model.PrizeLevel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * Draws the 15-level prize ladder and highlights the current level.
 */
public class MoneyLadderPanel extends JPanel {

    private final MoneyLadder ladder;
    private int currentLevel = 1;
    private boolean blinking;

    /**
     * Creates a ladder panel backed by a money ladder model.
     *
     * @param ladder prize ladder to render
     */
    public MoneyLadderPanel(MoneyLadder ladder) {
        this.ladder = ladder;
        setOpaque(false);
        setPreferredSize(new Dimension(310, 610));
    }

    /**
     * Updates which level is highlighted.
     *
     * @param currentLevel current 1-based level
     */
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
        repaint();
    }

    /**
     * Turns the current-level blink effect on or off.
     *
     * @param blinking {@code true} to use the green blink highlight
     */
    public void setBlinking(boolean blinking) {
        this.blinking = blinking;
        repaint();
    }

    /**
     * Paints the ladder background and every prize row.
     *
     * @param g graphics context supplied by Swing
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(0, 0, 60, 225));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);

        int rowHeight = getHeight() / ladder.size();
        g2.setFont(new Font("Segoe UI", Font.BOLD, 27));
        for (PrizeLevel prize : ladder.getLevelsDescending()) {
            drawPrizeRow(g2, prize, rowHeight);
        }
        g2.dispose();
    }

    /**
     * Draws one row in the prize ladder.
     *
     * @param g2 graphics context
     * @param prize prize level to draw
     * @param rowHeight height of one ladder row
     */
    private void drawPrizeRow(Graphics2D g2, PrizeLevel prize, int rowHeight) {
        int level = prize.getLevel();
        int rowFromTop = ladder.size() - level;
        int y = rowFromTop * rowHeight;
        boolean active = level == currentLevel;

        if (active) {
            g2.setColor(blinking ? UiTheme.GREEN : new Color(245, 166, 45));
            Polygon highlight = new Polygon(
                    new int[]{28, getWidth(), getWidth(), 28, 8},
                    new int[]{y + 3, y + 3, y + rowHeight - 3, y + rowHeight - 3, y + rowHeight / 2},
                    5
            );
            g2.fillPolygon(highlight);
        }

        g2.setColor(active ? Color.BLACK : prize.isMilestone() ? UiTheme.WHITE : UiTheme.GOLD);
        int baseline = y + rowHeight - 10;
        g2.drawString(String.valueOf(level), 42, baseline);
        if (level < ladder.size()) {
            g2.fillOval(108, baseline - 12, 8, 8);
        }
        drawMoneyText(g2, formatMoney(prize.getMoney()), 128, baseline, getWidth() - 138);
    }

    /**
     * Formats a prize value for display.
     *
     * @param value prize money
     * @return formatted money text
     */
    private String formatMoney(int value) {
        return String.format("$%,d", value);
    }

    /**
     * Draws money text and shrinks the font when the amount is too wide.
     *
     * @param g2 graphics context
     * @param text money text
     * @param x left position
     * @param baseline text baseline
     * @param maxWidth maximum available width
     */
    private void drawMoneyText(Graphics2D g2, String text, int x, int baseline, int maxWidth) {
        Font original = g2.getFont();
        FontMetrics metrics = g2.getFontMetrics();
        while (metrics.stringWidth(text) > maxWidth && g2.getFont().getSize() > 18) {
            g2.setFont(g2.getFont().deriveFont((float) g2.getFont().getSize() - 1));
            metrics = g2.getFontMetrics();
        }
        g2.drawString(text, x, baseline);
        g2.setFont(original);
    }
}
