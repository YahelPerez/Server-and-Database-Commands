package ui;

import javax.swing.*;
import java.awt.*;

/**
 * A custom JPanel that paints a vertical gradient background.
 */
public class GradientPanel extends JPanel {

    private final Color color1;
    private final Color color2;

    public GradientPanel(Color color1, Color color2) {
        this.color1 = color1;
        this.color2 = color2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        // Create a vertical gradient from the top (color1) to the bottom (color2)
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
    }
}