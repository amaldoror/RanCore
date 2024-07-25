package org.rancore.math.plot;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import static org.rancore.math.plot.FunctionPlotter.scaleFactorComponent;

public class UIComponents {

    private JButton resetButton;
    private JButton zoomInButton;
    private JButton zoomOutButton;
    private JTextField functionField;
    private JButton plotButton;
    private JTextField xField;
    private JTextField yField;
    private JButton addPointButton;

    private static final Color DARK_GRAY_01 = new Color(28,28,28);
    private static final Font menuFont = loadCustomFont(14f);
    private static final Font buttonFont = loadCustomFont(12f);
    private static final Font labelFont = loadCustomFont(10f);

    public UIComponents(FunctionPlotter plotter, JFrame frame) {
        JPanel panel = new JPanel();
        panel.setBackground(DARK_GRAY_01);

        resetButton = new JButton("Reset");
        zoomInButton = new JButton("Zoom In");
        zoomOutButton = new JButton("Zoom Out");
        functionField = new JTextField(10);
        plotButton = new JButton("Plot");
        xField = new JTextField(5);
        yField = new JTextField(5);
        addPointButton = new JButton("Add Point");

        customizeButton(resetButton);
        customizeButton(zoomInButton);
        customizeButton(zoomOutButton);
        customizeButton(plotButton);
        customizeButton(addPointButton);

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
    }

    public static JPanel createControlPanel(FunctionPlotter plotter, JFrame frame) {
        JPanel panel = new JPanel();
        JButton resetButton = new JButton("Reset");
        JButton zoomInButton = new JButton("Zoom In");
        JButton zoomOutButton = new JButton("Zoom Out");
        JTextField functionField = new JTextField(10);
        JButton plotButton = new JButton("Plot");
        JTextField xField = new JTextField(5);
        JTextField yField = new JTextField(5);
        JButton addPointButton = new JButton("Add Point");

        panel.setBackground(DARK_GRAY_01);

        // Set colors and font for buttons
        resetButton.setBackground(Color.LIGHT_GRAY);
        resetButton.setForeground(DARK_GRAY_01);
        resetButton.setFont(buttonFont);

        zoomInButton.setBackground(Color.LIGHT_GRAY);
        zoomInButton.setForeground(DARK_GRAY_01);
        zoomInButton.setFont(buttonFont);

        zoomOutButton.setBackground(Color.LIGHT_GRAY);
        zoomOutButton.setForeground(DARK_GRAY_01);
        zoomOutButton.setFont(buttonFont);

        plotButton.setBackground(Color.LIGHT_GRAY);
        plotButton.setForeground(DARK_GRAY_01);
        plotButton.setFont(buttonFont);

        addPointButton.setBackground(Color.LIGHT_GRAY);
        addPointButton.setForeground(DARK_GRAY_01);
        addPointButton.setFont(buttonFont);

        // Add action listeners for buttons
        resetButton.addActionListener(e -> plotter.resetGraph());
        zoomInButton.addActionListener(e -> {
            FunctionPlotter.scaleFactorComponent *= 1.2;
            plotter.repaint();
        });
        zoomOutButton.addActionListener(e -> {
            FunctionPlotter.scaleFactorComponent /= 1.2;
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
                MathParser.evaluate(function);
                plotter.setFunction(function);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid function.");
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

        return panel;
    }

    private void customizeButton(JButton button) {
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(DARK_GRAY_01);
        button.setFont(buttonFont);
    }

    static JMenuBar getjMenuBar(FunctionPlotter plotter) {
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
        JMenu viewMenu = getViewMenu(plotter);

        // Add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);

        // Set colors and font for the menu bar
        menuBar.setBackground(DARK_GRAY_01);
        menuBar.setForeground(DARK_GRAY_01);
        menuBar.setFont(menuFont);

        // Set colors and font for each menu
        fileMenu.setForeground(Color.WHITE);
        fileMenu.setBackground(DARK_GRAY_01);
        fileMenu.setFont(menuFont);

        editMenu.setForeground(Color.WHITE);
        editMenu.setBackground(DARK_GRAY_01);
        editMenu.setFont(menuFont);

        viewMenu.setForeground(Color.WHITE);
        viewMenu.setBackground(DARK_GRAY_01);
        viewMenu.setFont(menuFont);

        // Set colors and font for menu items
        exitItem.setBackground(Color.WHITE);
        exitItem.setForeground(DARK_GRAY_01);
        exitItem.setFont(menuFont);

        // Repeat for other menu items
        clearPointsItem.setBackground(Color.WHITE);
        clearPointsItem.setForeground(DARK_GRAY_01);
        clearPointsItem.setFont(menuFont);
        customizeMenuBar(menuBar, fileMenu, editMenu, viewMenu, exitItem, clearPointsItem);
        return menuBar;
    }

    private static void customizeMenuBar(JMenuBar menuBar, JMenu fileMenu, JMenu editMenu, JMenu viewMenu, JMenuItem exitItem, JMenuItem clearPointsItem) {
        menuBar.setBackground(DARK_GRAY_01);
        menuBar.setForeground(DARK_GRAY_01);
        menuBar.setFont(menuFont);

        customizeMenu(fileMenu);
        customizeMenu(editMenu);
        customizeMenu(viewMenu);
        customizeMenuItem(exitItem);
        customizeMenuItem(clearPointsItem);
    }

    private static void customizeMenu(JMenu menu) {
        menu.setForeground(Color.WHITE);
        menu.setBackground(DARK_GRAY_01);
        menu.setFont(menuFont);
    }

    private static void customizeMenuItem(JMenuItem menuItem) {
        menuItem.setBackground(Color.WHITE);
        menuItem.setForeground(DARK_GRAY_01);
        menuItem.setFont(menuFont);
    }

    private static JMenu getViewMenu(FunctionPlotter plotter) {
        JMenu viewMenu = new JMenu("View");
        JMenuItem zoomInItem = new JMenuItem("Zoom In");
        zoomInItem.setFont(menuFont);
        zoomInItem.addActionListener(e -> {
            scaleFactorComponent *= 1.2;
            plotter.repaint();
        });

        JMenuItem zoomOutItem = new JMenuItem("Zoom Out");
        zoomOutItem.setFont(menuFont);
        zoomOutItem.addActionListener(e -> {
            scaleFactorComponent /= 1.2;
            plotter.repaint();
        });

        JMenuItem resetViewItem = new JMenuItem("Reset View");
        resetViewItem.setFont(menuFont);
        resetViewItem.addActionListener(e -> plotter.resetGraph());
        viewMenu.add(zoomInItem);
        viewMenu.add(zoomOutItem);
        viewMenu.add(resetViewItem);
        return viewMenu;
    }

    private static Font loadCustomFont(float size) {
        try {
            // Load the font file from the resources folder
            InputStream is = FunctionPlotter.class.getResourceAsStream("/fonts/ALTRONED.ttf");
            assert is != null;
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);

            // Return the font in the desired size
            return customFont.deriveFont(size);
        } catch (IOException | FontFormatException e) {

            // Return a default font if the custom font fails to load
            return new Font("Arial", Font.PLAIN, (int)size);
        }
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JButton getZoomInButton() {
        return zoomInButton;
    }

    public JButton getZoomOutButton() {
        return zoomOutButton;
    }

    public JTextField getFunctionField() {
        return functionField;
    }

    public JButton getPlotButton() {
        return plotButton;
    }

    public JTextField getXField() {
        return xField;
    }

    public JTextField getYField() {
        return yField;
    }

    public JButton getAddPointButton() {
        return addPointButton;
    }
}
