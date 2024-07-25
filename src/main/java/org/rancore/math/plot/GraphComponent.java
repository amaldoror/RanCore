package org.rancore.math.plot;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.rancore.math.plot.FunctionPlotter.scaleFactorComponent;
import static org.rancore.math.plot.MathParser.evaluate;

public class GraphComponent {

    private double scaleFactorFunction = 1; // Initial scale factor for the drawn functions
    private static final List<Point> points = new ArrayList<>();
    private static String function;


    static void drawPoints(Graphics2D g2) {
        // Draw points
        g2.setColor(Color.WHITE);
        for (Point point : points) {
            g2.fillOval(point.x - 3, point.y - 3, 3, 3);
        }
    }

    static void drawAxis(Graphics2D g2, int height, int width) {
        // Draw x- and y-axis with arrows
        g2.setColor(Color.GRAY);
        g2.drawLine(0, height / 2, width, height / 2); // x-axis
        g2.drawLine(width / 2, 0, width / 2, height); // y-axis

        // Draw arrows on the axes
        g2.drawLine(width / 2, 0, width / 2 - 5, 10);
        g2.drawLine(width / 2, 0, width / 2 + 5, 10);
        g2.drawLine(width, height / 2, width - 10, height / 2 - 5);
        g2.drawLine(width, height / 2, width - 10, height / 2 + 5);

        // Draw labels for the axes
        g2.drawString("X", width - 15, height / 2 - 5);
        g2.drawString("Y", width / 2 + 5, 15);
    }

    static void drawAxisScale(Graphics2D g2, int width, int height, double scaleX, double scaleY, int rangeX, int rangeY) {        // Draw axis scale
        g2.setColor(Color.GRAY);

        // x-axis
        g2.setColor(Color.RED);
        for (int i = -rangeX; i <= rangeX; i += (int) scaleX) {
            int x = (int) (width / 2 + i * (width / (2.0 * rangeX/scaleFactorComponent)));
            g2.drawLine(x, height / 2 - 5, x, height / 2 + 5);
            g2.drawString(Integer.toString(i), x - 10, height / 2 + 20);
        }

        // y-axis
        g2.setColor(Color.GRAY);
        for (int i = -rangeY; i <= rangeY; i += (int) scaleY) {
            int y = (int) (height / 2 - i * (height / (2.0 * rangeY/scaleFactorComponent)));
            g2.drawLine(width / 2 - 5, y, width / 2 + 5, y);
            g2.drawString(Integer.toString(i), width / 2 + 10, y + 5);
        }
    }

    static void drawFunction(Graphics2D g2, int width, int height) {
        // Draw the function if it's set
        if (function != null && !function.isEmpty()) {
            g2.setColor(Color.RED);

            int prevX = -1, prevY = -1;

            for (int x = -width /2; x < width; x++) {
                double xValue = (x - width / 2.0) / scaleFactorComponent;
                try {
                    double yValue = evaluate(function.replace("x", String.valueOf(xValue)));
                    int y = height / 2 - (int)(yValue * scaleFactorComponent);
                    if (prevX != -1 && prevY != -1) {
                        g2.drawLine(prevX, prevY, x, y);
                    }
                    prevX = x;
                    prevY = y;
                } catch (Exception e) {
                    // Skip points where the function is undefined
                }
            }
        }
    }

    static void drawSquared(Graphics2D g2, int width, int height) {
        // Draw the function y = x^2
        g2.setColor(Color.RED);
        for (int x = -width / 2; x < width / 2; x++) {
            double scaledX = x / scaleFactorComponent;
            double scaledY = scaledX * scaledX;

            int x1 = width / 2 + (int)(scaledX * scaleFactorComponent);
            int y1 = height / 2 - (int)(scaledY * scaleFactorComponent);
            double nextScaledX = (x + 1) / scaleFactorComponent;
            double nextScaledY = nextScaledX * nextScaledX;
            int x2 = width / 2 + (int)(nextScaledX * scaleFactorComponent);
            int y2 = height / 2 - (int)(nextScaledY * scaleFactorComponent);

            g2.drawLine(x1, y1, x2, y2);
        }
    }

    static void drawGrid(Graphics2D g2, int width, int height) {
        g2.setColor(new Color(50, 50, 50));  // Dark gray color for the grid
        g2.setStroke(new BasicStroke(0.5f));  // Thin lines for the grid

        // Draw vertical lines
        for (int x = -width / 2; x < width; x += (int) (10 * scaleFactorComponent)) {
            g2.drawLine(x, 0, x, height);
        }
        for (int x = -width / 2; x > 0; x -= (int) (10 * scaleFactorComponent)) {
            g2.drawLine(x, 0, x, height);
        }

        // Draw horizontal lines
        for (int y = height / 2; y < height; y += (int) (10 * scaleFactorComponent)) {
            g2.drawLine(0, y, width, y);
        }
        for (int y = height / 2; y > 0; y -= (int) (10 * scaleFactorComponent)) {
            g2.drawLine(0, y, width, y);
        }
    }
}
