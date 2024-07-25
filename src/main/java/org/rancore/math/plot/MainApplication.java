package org.rancore.math.plot;

import javax.swing.*;
import java.awt.*;

public class MainApplication {
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        BorderLayout layout = new BorderLayout();
        FunctionPlotter plotter = new FunctionPlotter();

        JFrame frame = new JFrame("Function Plotter");

        // Create menu bar
        JMenuBar menuBar = UIComponents.getjMenuBar(plotter);

        // Set menu bar to frame
        frame.setJMenuBar(menuBar);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(layout);
        frame.setSize(screenSize.width / 2, screenSize.height / 2);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(plotter);
        frame.setVisible(true);

        // Create a panel for the buttons and text fields
        JPanel panel = UIComponents.createControlPanel(plotter, frame);

        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
