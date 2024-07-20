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
    private static boolean debug = true;

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        BorderLayout layout = new BorderLayout();
        FunctionPlotter plotter = new FunctionPlotter();

        JFrame frame = new JFrame("Function Plotter");

        // Create menu bar
        JMenuBar menuBar = getjMenuBar(plotter);

        // Set menu bar to frame
        frame.setJMenuBar(menuBar);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(layout);
        frame.setSize(screenSize.width/2, screenSize.height/2);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(plotter);
        frame.setVisible(true);

        // Create a panel for the buttons and text fields
        JPanel panel = new JPanel();
        JButton resetButton = new JButton("Reset");
        JButton zoomInButton = new JButton("Zoom In");
        JButton zoomOutButton = new JButton("Zoom Out");
        JTextField functionField = new JTextField(10);
        JButton plotButton = new JButton("Plot");
        JTextField xField = new JTextField(5);
        JTextField yField = new JTextField(5);
        JButton addPointButton = new JButton("Add Point");

        // Create a custom font for buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 12);

        // Set colors and font for buttons
        resetButton.setBackground(Color.LIGHT_GRAY);
        resetButton.setForeground(Color.BLACK);
        resetButton.setFont(buttonFont);

        zoomInButton.setBackground(Color.LIGHT_GRAY);
        zoomInButton.setForeground(Color.BLACK);
        zoomInButton.setFont(buttonFont);

        zoomOutButton.setBackground(Color.LIGHT_GRAY);
        zoomOutButton.setForeground(Color.BLACK);
        zoomOutButton.setFont(buttonFont);

        plotButton.setBackground(Color.LIGHT_GRAY);
        plotButton.setForeground(Color.BLACK);
        plotButton.setFont(buttonFont);

        addPointButton.setBackground(Color.LIGHT_GRAY);
        addPointButton.setForeground(Color.BLACK);
        addPointButton.setFont(buttonFont);

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

    private static JMenuBar getjMenuBar(FunctionPlotter plotter) {
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        // Edit menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem clearPointsItem = new JMenuItem("Clear Points");
        clearPointsItem.addActionListener(e -> plotter.clearPoints());
        editMenu.add(clearPointsItem);

        // View menu
        JMenu viewMenu = new JMenu("View");
        JMenuItem zoomInItem = new JMenuItem("Zoom In");
        zoomInItem.addActionListener(e -> {
            plotter.scale *= 1.2;
            plotter.repaint();
        });

        JMenuItem zoomOutItem = new JMenuItem("Zoom Out");
        zoomOutItem.addActionListener(e -> {
            plotter.scale /= 1.2;
            plotter.repaint();
        });

        JMenuItem resetViewItem = new JMenuItem("Reset View");
        resetViewItem.addActionListener(e -> plotter.resetGraph());
        viewMenu.add(zoomInItem);
        viewMenu.add(zoomOutItem);
        viewMenu.add(resetViewItem);

        // Add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);

        // Create a custom font
        Font menuFont = new Font("Arial", Font.BOLD, 14);

        // Set colors and font for the menu bar
        menuBar.setBackground(Color.LIGHT_GRAY);
        menuBar.setForeground(Color.BLACK);
        menuBar.setFont(menuFont);

        // Set colors and font for each menu
        fileMenu.setForeground(Color.BLUE);
        fileMenu.setFont(menuFont);
        editMenu.setForeground(Color.BLUE);
        editMenu.setFont(menuFont);
        viewMenu.setForeground(Color.BLUE);
        viewMenu.setFont(menuFont);

        // Set colors and font for menu items
        exitItem.setBackground(Color.WHITE);
        exitItem.setForeground(Color.BLACK);
        exitItem.setFont(menuFont);

        // Repeat for other menu items
        clearPointsItem.setBackground(Color.WHITE);
        clearPointsItem.setForeground(Color.BLACK);
        clearPointsItem.setFont(menuFont);

        return menuBar;
    }

    private void drawPoints(Graphics2D g2) {
        // Draw points
        g2.setColor(Color.WHITE);
        for (Point point : points) {
            g2.fillOval(point.x - 3, point.y - 3, 3, 3);
        }
    }

    private static void drawAxis(Graphics2D g2, int height, int width) {
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

    private static void drawAxisScale(Graphics2D g2, int width, int height) {
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
    }

    private void drawFunction(Graphics2D g2, int width, int height) {
        // Draw the function if it's set
        if (function != null && !function.isEmpty()) {
            g2.setColor(Color.RED);

            int prevX = -1, prevY = -1;

            for (int x = -width /2; x < width; x++) {
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

    private void drawSquared(Graphics2D g2, int width, int height) {
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
    }

    public void resetGraph() {
        points.clear();
        scale = 1;
        repaint();
    }

    public void addPoint(int x, int y) {
        int width = getWidth();
        int height = getHeight();
        int xPanel = (int)(x * scale + width / 2);
        int yPanel = (int)(height / 2 - y * scale);
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

    private void drawGrid(Graphics2D g2, int width, int height) {
        g2.setColor(new Color(50, 50, 50));  // Dark gray color for the grid
        g2.setStroke(new BasicStroke(0.5f));  // Thin lines for the grid

        // Draw vertical lines
        for (int x = -width / 2; x < width; x += (int) (10 * scale)) {
            g2.drawLine(x, 0, x, height);
        }
        for (int x = -width / 2; x > 0; x -= (int) (10 * scale)) {
            g2.drawLine(x, 0, x, height);
        }

        // Draw horizontal lines
        for (int y = height / 2; y < height; y += (int) (10 * scale)) {
            g2.drawLine(0, y, width, y);
        }
        for (int y = height / 2; y > 0; y -= (int) (10 * scale)) {
            g2.drawLine(0, y, width, y);
        }
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

        if(debug) drawSquared(g2, width, height);

        drawPoints(g2);

        drawGrid(g2, width, height);

        drawAxis(g2, height, width);

        drawAxisScale(g2, width, height);

        drawFunction(g2, width, height);
    }
}
