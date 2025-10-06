package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A custom component that draws a circular loading spinner animation.
 * This is drawn with code, so it has no background issues like a GIF.
 */
public class LoadingSpinner extends JComponent {

    private final Timer timer;
    private int currentDot = 0;
    private final int dotCount = 8;
    private final int dotRadius = 6;
    private final int circleRadius = 30;

    public LoadingSpinner() {
        // The timer will trigger a repaint every 100ms to create the animation
        this.timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentDot = (currentDot + 1) % dotCount;
                repaint(); // Redraw the component
            }
        });
    }

    // Call this to start the animation
    public void start() {
        timer.start();
    }

    // Call this to stop the animation
    public void stop() {
        timer.stop();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Enable anti-aliasing for smooth circles
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Center of the component
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Draw all the dots in a circle
        for (int i = 0; i < dotCount; i++) {
            double angle = 2 * Math.PI * i / dotCount;
            int x = centerX + (int) (circleRadius * Math.cos(angle)) - dotRadius;
            int y = centerY + (int) (circleRadius * Math.sin(angle)) - dotRadius;

            // The "current" dot is brighter, the others are dimmer
            if (i == currentDot) {
                g2d.setColor(new Color(150, 210, 255)); // Bright blue
            } else {
                g2d.setColor(new Color(150, 210, 255, 100)); // Dimmer, semi-transparent blue
            }
            g2d.fillOval(x, y, dotRadius * 2, dotRadius * 2);
        }

        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        // Give the component a default size
        return new Dimension(100, 100);
    }
}