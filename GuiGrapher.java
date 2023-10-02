import javax.swing.*;

import Calculator.*;
import Calculator.exception.DivisionByZeroException;
import Calculator.exception.LogByZeroException;
import Calculator.exception.SyntaxeErrorException;
import settings.ColorPalette;
import views.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class GuiGrapher extends JFrame {
    protected JPanel rightPanel;
    protected PositionPanel positionPanel;
    protected ActionPanel actionPanel;
    protected EvalPanel evalPanel;
    protected GrapherPanel grapherPanel;

    public GuiGrapher() {
        this.setTitle("Grapher");
        this.setSize(1200, 700);
        // Display it in the middle of the screen
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1, 2));
        ActionListener actionPanelListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("Refresh".equals(e.getActionCommand())) {
                    handleBtnSubmitClick();
                }
                if ("Clear".equals(e.getActionCommand())) {
                    handleBtnClearClick();
                }
                if ("ZoomIn".equals(e.getActionCommand())) {
                    handleBtnZoomInClick();
                } else if ("ZoomOut".equals(e.getActionCommand())) {
                    handleBtnZoomOutClick();
                }
            }
        };
        ActionListener repaintListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("Repaint".equals(e.getActionCommand())) {
                    updateActionPanelFields();
                }
            }
        };
        // Initialization of elements:
        grapherPanel = new GrapherPanel(repaintListener);
        GraphMouse graphMouse = new GraphMouse(grapherPanel);
        positionPanel = new PositionPanel(graphMouse);
        actionPanel = new ActionPanel(grapherPanel, actionPanelListener);

        // Create an ActionListener for the "Submit" button
        ActionListener submitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if ("Submit".equals(e.getActionCommand())) {
                    handleBtnSubmitClick();
                }
            }
        };

        evalPanel = new EvalPanel(submitListener);
        rightPanel = new JPanel();
        rightPanel.setBackground(ColorPalette.getSecondColor());

        // Build the whole panel
        Container content = this.getContentPane();
        content.setBackground(ColorPalette.getLabelForegroundColor());
        content.setLayout(new BorderLayout());
        content.add(rightPanel, BorderLayout.EAST);
        content.add(positionPanel, BorderLayout.NORTH);
        content.add(actionPanel, BorderLayout.WEST);
        content.add(grapherPanel, BorderLayout.CENTER);
        content.add(evalPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public void handleBtnZoomInClick() {
        this.grapherPanel.actionZoomIn();
    }

    public void handleBtnZoomOutClick() {
        this.grapherPanel.actionZoomOut();
    }

    public void updateActionPanelFields() {
        actionPanel.setXminField(grapherPanel.getMinX());
        actionPanel.setXmaxField(grapherPanel.getMaxX());
        actionPanel.setYminField(grapherPanel.getMinY());
        actionPanel.setYmaxField(grapherPanel.getMaxY());
    }

    public void handleBtnClearClick() {
        this.grapherPanel.setcheckedEval(false);
        // TODO Fix it still display f(x)
        this.grapherPanel.unsetxyPairs();
        this.grapherPanel.repaint();
        this.evalPanel.clearFunction();
    }

    public void handleBtnSubmitClick() {
        String expression = this.evalPanel.getExpression();

        try {
            String xminStr = actionPanel.getXminField();
            String xmaxStr = actionPanel.getXmaxField();
            String yminStr = actionPanel.getYminField();
            String ymaxStr = actionPanel.getYmaxField();
            String stepStr = actionPanel.getStepField();
            String xGridStr = actionPanel.getXGridField();
            String yGridStr = actionPanel.getYGridField();

            float xmin = xminStr.isEmpty() ? grapherPanel.getMinX() : Float.parseFloat(xminStr);
            float xmax = xmaxStr.isEmpty() ? grapherPanel.getMaxX() : Float.parseFloat(xmaxStr);
            float ymin = yminStr.isEmpty() ? grapherPanel.getMinY() : Float.parseFloat(yminStr);
            float ymax = ymaxStr.isEmpty() ? grapherPanel.getMaxY() : Float.parseFloat(ymaxStr);
            float step = stepStr.isEmpty() ? grapherPanel.getStep() : Float.parseFloat(stepStr);
            float xGrid = xGridStr.isEmpty() ? grapherPanel.getGridX() : Float.parseFloat(xGridStr);
            float yGrid = yGridStr.isEmpty() ? grapherPanel.getGridY() : Float.parseFloat(yGridStr);

            grapherPanel.setMinMaxX(xmin, xmax);
            grapherPanel.setMinMaxY(ymin, ymax);
            grapherPanel.setStep(step);
            grapherPanel.setGridX(xGrid);
            grapherPanel.setGridY(yGrid);
            grapherPanel.repaint(); // Redraw the GrapherPanel

            // Create a Map to store the x-y pairs
            Map<Float, Float> xyPairs = new HashMap<>();
            // Loop through the range of X values and evaluate the function for each X
            for (float currentX = xmin; currentX <= xmax; currentX += step) {
                float y;
                try {
                    y = Calculator.evaluateExpression(expression, currentX);

                } catch (NumberFormatException ex) {
                    // Handle invalid inputs with a pop-up error message
                    displayErrorMessage("Invalid input. Please enter numeric values.");
                    return;
                } catch (DivisionByZeroException ex) {
                    displayErrorMessage("Division by zero error.");
                    return;
                } catch (LogByZeroException ex) {
                    displayErrorMessage("Logarithm by zero error.");
                    return;
                }
                xyPairs.put(currentX, y); // Store the x-y pair in the Map
            }

            // If no exceptions occurred, update the graph
            this.grapherPanel.setcheckedEval(true);
            this.grapherPanel.setxyPairs(xyPairs);
            this.grapherPanel.repaint();
        } catch (SyntaxeErrorException ex) {
            // Handle SyntaxeErrorException with a pop-up error message
            displayErrorMessage("Syntax Error: " + ex.getMessage());
        }
    }

    // Helper method to display error messages
    private void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuiGrapher();
            }
        });
    }
}
