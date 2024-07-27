package org.rancore.math.plot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import static org.rancore.math.plot.GraphComponent.*;

public class FunctionPlotter extends JPanel {
    public static double scaleFactorComponent = 1; // Initial scale factor for the components
    private double scaleFactorFunction = 1; // Initial scale factor for the drawn functions
    private final List<Point> points = new ArrayList<>();
    private String function;


    public FunctionPlotter() {
        setBackground(Color.DARK_GRAY);
        addMouseWheelListener(new MouseWheelZoomListener());
        addMouseListener(new PointAddingMouseListener());
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new ZoomKeyListener());
    }

    public void resetGraph() {
        points.clear();
        scaleFactorComponent = 1;
        repaint();
    }

    public void addPoint(int x, int y) {
        int width = getWidth();
        int height = getHeight();
        int xPanel = (int)(x * scaleFactorComponent + width / 2);
        int yPanel = (int)(height / 2 - y * scaleFactorComponent);
        points.add(new Point(xPanel, yPanel));
        repaint();
    }

    public void clearPoints() {
        points.clear();
        repaint();
    }
    
    public void setFunction(String function) {
        this.function = function;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        double scaleX = 50;
        double scaleY = 50;
        int rangeX = 100;
        int rangeY = 100;
        drawPoints(g2);
        drawGrid(g2, width, height);
        drawAxis(g2, height, width);
        drawAxisScale(g2, width, height, scaleX, scaleY, rangeX, rangeY);
        drawPrimes(g2, width, height);
        drawSquared(g2, width, height);
        drawFunction(g2, width, height);
    }

    private class MouseWheelZoomListener implements MouseWheelListener {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            if (e.getWheelRotation() < 0) {
                scaleFactorComponent *= 1.1; // Zoom in
            } else if (e.getWheelRotation() > 0) {
                scaleFactorComponent /= 1.1; // Zoom out
            }
            repaint();
        }
    }

    private class PointAddingMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            points.add(e.getPoint());
            repaint();
        }
    }

    private class ZoomKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.isControlDown()) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    scaleFactorComponent *= 1.1;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    scaleFactorComponent /= 1.1;
                }
                repaint();
            }
        }
    }
}
