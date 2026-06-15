package com.mycompany.millionaire.ui.component;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MenuBackgroundPanel extends JPanel {

    private final Image image = new ImageIcon("MainMenu.png").getImage();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);

        if (imageWidth <= 0 || imageHeight <= 0) {
            return;
        }

        double scale = Math.max((double) panelWidth / imageWidth, (double) panelHeight / imageHeight);
        int drawWidth = (int) (imageWidth * scale);
        int drawHeight = (int) (imageHeight * scale);
        int x = (panelWidth - drawWidth) / 2;
        int y = (panelHeight - drawHeight) / 2;
        g.drawImage(image, x, y, drawWidth, drawHeight, this);
    }
}
