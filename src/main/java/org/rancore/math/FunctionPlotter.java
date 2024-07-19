package org.rancore.math;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import static org.rancore.math.MathParser.evaluate;

public class FunctionPlotter extends JPanel {

    private double scale = 1; // Initial scale factor
    private final List<Point> points = new ArrayList<>();
    private String function;

    // Main method to set up the frame
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        BorderLayout layout = new BorderLayout();
        FunctionPlotter plotter = new FunctionPlotter();

        JFrame frame = new JFrame("Function Plotter");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(layout);
        frame.setSize(screenSize.width/2, screenSize.height/2);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(plotter);
        frame.setVisible(true);

        // Create a panel for the buttons and text fields
        JPanel panel = new JPanel();
        JButton resetButton = new JButton("Reset Graph");
        JButton zoomInButton = new JButton("Zoom In");
        JButton zoomOutButton = new JButton("Zoom Out");
        JTextField functionField = new JTextField(10);
        JButton plotButton = new JButton("Plot");
        JTextField xField = new JTextField(5);
        JTextField yField = new JTextField(5);
        JButton addPointButton = new JButton("Add Point");

        // Add action listeners for buttons
        resetButton.addActionListener(e -> plotter.resetGraph());
        zoomInButton.addActionListener(e -> {
            plotter.scale *= 1.2;
            plotter.repaint();
        });
        zoomOutButton.addActionListener(e -> {
            plotter.scale /= 1.2;
            plotter.repaint();
        });
        addPointButton.addActionListener(e -> {
            try {
                int x = Integer.parseInt(xField.getText());
                int y = Integer.parseInt(yField.getText());
                plotter.addPoint(x, y);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid integers for x and y.");
            }
        });
        plotButton.addActionListener(e -> {
            try {
                String function = functionField.getText();
                evaluate(function);
                plotter.setFunction(function);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(frame, "Please enter valid function.");
            }
        });

        // Add components to the panel
        panel.add(resetButton);
        panel.add(zoomInButton);
        panel.add(zoomOutButton);
        panel.add(new JLabel("x:"));
        panel.add(xField);
        panel.add(new JLabel("y:"));
        panel.add(yField);
        panel.add(addPointButton);
        panel.add(new JLabel("Function:"));
        panel.add(functionField);
        panel.add(plotButton);

        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public FunctionPlotter() {
        setBackground(Color.DARK_GRAY);

        // Add mouse wheel listener for zooming
        addMouseWheelListener(e -> {
            if (e.getWheelRotation() < 0) {
                scale *= 1.1; // Zoom in
            } else if (e.getWheelRotation() > 0){
                scale /= 1.1; // Zoom out
            }
            repaint();
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                points.add(e.getPoint());
                repaint();
            }
        });

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown()) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        scale *= 1.1;
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        scale /= 1.1;
                    }
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Set rendering hints for better graphics quality
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Get the size of the drawing area
        int width = getWidth();
        int height = getHeight();

        /*
        // Draw the function y = x^2
        g2.setColor(Color.RED);
        for (int x = -width / 2; x < width / 2; x++) {
            double scaledX = x / scale;
            double scaledY = scaledX * scaledX;

            int x1 = width / 2 + (int)(scaledX * scale);
            int y1 = height / 2 - (int)(scaledY * scale);
            double nextScaledX = (x + 1) / scale;
            double nextScaledY = nextScaledX * nextScaledX;
            int x2 = width / 2 + (int)(nextScaledX * scale);
            int y2 = height / 2 - (int)(nextScaledY * scale);

            g2.drawLine(x1, y1, x2, y2);
        }

         */

        // Draw points
        g2.setColor(Color.WHITE);
        for (Point point : points) {
            g2.fillOval(point.x - 3, point.y - 3, 6, 6);
        }

        // Draw axis scale
        g2.setColor(Color.GRAY);
        for (int i = -width / 2; i < width / 2; i += 50) {
            g2.drawLine(width / 2 + i, height / 2 - 5, width / 2 + i, height / 2 + 5);
            g2.drawString(Integer.toString(i), width / 2 + i - 5, height / 2 + 20);
        }
        for (int i = -height / 2; i < height / 2; i += 50) {
            g2.drawLine(width / 2 - 5, height / 2 - i, width / 2 + 5, height / 2 - i);
            g2.drawString(Integer.toString(i), width / 2 + 10, height / 2 - i + 5);
        }

        // Draw x- and y-axis with arrows
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

        // Draw the function if it's set
        if (function != null && !function.isEmpty()) {
            g2.setColor(Color.RED);
            int prevX = -1, prevY = -1;
            for (int x = 0; x < width; x++) {
                double xValue = (x - width / 2.0) / scale;
                try {
                    double yValue = evaluate(function.replace("x", String.valueOf(xValue)));
                    int y = height / 2 - (int)(yValue * scale);
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

    // Method to reset the graph
    public void resetGraph() {
        points.clear();
        scale = 20;
        repaint();
    }

    // Method to add a point based on x and y values
    public void addPoint(int x, int y) {
        int width = getWidth();
        int height = getHeight();
        int xPanel = (int)(x * scale + width / 2);
        int yPanel = (int)(height / 2 - y * scale);
        points.add(new Point(xPanel, yPanel));
        repaint();
    }

    public void setFunction(String function) {
        this.function = function;
        repaint();
    }
}
