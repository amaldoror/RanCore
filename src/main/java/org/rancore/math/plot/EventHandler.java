package org.rancore.math.plot;

import javax.swing.*;

import static org.rancore.math.plot.MathParser.evaluate;

public class EventHandler {
    private final FunctionPlotter plotter;
    private final JFrame frame;
    private final UIComponents uiComponents;

    public EventHandler(FunctionPlotter plotter, JFrame frame, UIComponents uiComponents) {
        this.plotter = plotter;
        this.frame = frame;
        this.uiComponents = uiComponents;
        addEventListeners();
    }

    private void addEventListeners() {
        uiComponents.getResetButton().addActionListener(e -> plotter.resetGraph());
        uiComponents.getZoomInButton().addActionListener(e -> {
            FunctionPlotter.scaleFactorComponent *= 1.2;
            plotter.repaint();
        });
        uiComponents.getZoomOutButton().addActionListener(e -> {
            FunctionPlotter.scaleFactorComponent /= 1.2;
            plotter.repaint();
        });
        uiComponents.getAddPointButton().addActionListener(e -> {
            try {
                int x = Integer.parseInt(uiComponents.getXField().getText());
                int y = Integer.parseInt(uiComponents.getYField().getText());
                plotter.addPoint(x, y);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid integers for x and y.");
            }
        });
        uiComponents.getPlotButton().addActionListener(e -> {
            try {
                String function = uiComponents.getFunctionField().getText();
                evaluate(function);
                plotter.setFunction(function);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(frame, "Please enter valid function.");
            }
        });
    }
}
