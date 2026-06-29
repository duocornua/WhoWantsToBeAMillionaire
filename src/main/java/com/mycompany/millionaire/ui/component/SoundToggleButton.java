package com.mycompany.millionaire.ui.component;

import com.mycompany.millionaire.ui.AudioPlayer;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

/**
 * Circular icon button that shows muted or unmuted audio state.
 */
public class SoundToggleButton extends JButton {

    /**
     * Creates a transparent sound toggle button.
     */
    public SoundToggleButton() {
        setPreferredSize(new Dimension(48, 48));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
    }

    /**
     * Paints the sound icon based on {@link AudioPlayer#isMuted()}.
     *
     * @param g graphics context supplied by Swing
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int size = Math.min(getWidth(), getHeight()) - 4;
        int left = (getWidth() - size) / 2;
        int top = (getHeight() - size) / 2;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        double scale = size / 48.0;

        g2.setColor(getBackground());
        g2.fillOval(left, top, size, size);
        g2.setColor(UiTheme.MENU_GOLD);
        g2.setStroke(new BasicStroke(2.2f));
        g2.drawOval(left, top, size - 1, size - 1);

        int[] speakerX = {
            centerX - (int) (12 * scale), centerX - (int) (6 * scale), centerX + (int) (2 * scale),
            centerX + (int) (2 * scale), centerX - (int) (6 * scale), centerX - (int) (12 * scale)
        };
        int[] speakerY = {
            centerY - (int) (6 * scale), centerY - (int) (6 * scale), centerY - (int) (12 * scale),
            centerY + (int) (12 * scale), centerY + (int) (6 * scale), centerY + (int) (6 * scale)
        };
        g2.fillPolygon(speakerX, speakerY, speakerX.length);

        if (AudioPlayer.isMuted()) {
            g2.setStroke(new BasicStroke((float) (2.4 * scale)));
            g2.drawLine(centerX + (int) (8 * scale), centerY - (int) (8 * scale), centerX + (int) (18 * scale), centerY + (int) (8 * scale));
            g2.drawLine(centerX + (int) (18 * scale), centerY - (int) (8 * scale), centerX + (int) (8 * scale), centerY + (int) (8 * scale));
        } else {
            g2.setStroke(new BasicStroke((float) (2.0 * scale)));
            g2.drawArc(centerX + (int) (4 * scale), centerY - (int) (10 * scale), (int) (12 * scale), (int) (20 * scale), -42, 84);
            g2.drawArc(centerX + (int) (8 * scale), centerY - (int) (14 * scale), (int) (16 * scale), (int) (28 * scale), -42, 84);
        }

        g2.dispose();
    }

    /**
     * Skips default border painting because the icon draws its own outline.
     *
     * @param g graphics context supplied by Swing
     */
    @Override
    protected void paintBorder(Graphics g) {
    }
}
