package com.mycompany.millionaire.ui.component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Scales and centers the game logo image.
 */
public class LogoPanel extends JPanel {

    private final Image logo = new ImageIcon("logo.png").getImage();

    /**
     * Creates a transparent logo panel.
     */
    public LogoPanel() {
        setOpaque(false);
    }

    /**
     * Draws the logo at the largest size that fits inside the panel.
     *
     * @param g graphics context supplied by Swing
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (logo.getWidth(this) <= 0 || logo.getHeight(this) <= 0) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        double scale = Math.min((double) getWidth() / logo.getWidth(this), (double) getHeight() / logo.getHeight(this));
        int drawWidth = (int) (logo.getWidth(this) * scale);
        int drawHeight = (int) (logo.getHeight(this) * scale);
        int x = (getWidth() - drawWidth) / 2;
        int y = (getHeight() - drawHeight) / 2;
        g2.drawImage(logo, x, y, drawWidth, drawHeight, this);
        g2.dispose();
    }
}
