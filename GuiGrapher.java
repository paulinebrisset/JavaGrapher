import javax.swing.*;

import Eval.*;
import settings.OptionalSettings;
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
        ActionListener refreshListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if ("Refresh".equals(e.getActionCommand())) {
                    handleBtnSubmitClick();
                }
            }
        };
        ActionListener clearListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if ("Clear".equals(e.getActionCommand())) {
                    handleBtnClearClick();
                }
            }
        };
        // Initialization of elements:
        grapherPanel = new GrapherPanel();
        GraphMouse graphMouse = new GraphMouse(grapherPanel);
        positionPanel = new PositionPanel(graphMouse);
        actionPanel = new ActionPanel(grapherPanel, refreshListener, clearListener);

        // Create an ActionListener for the "Submit" button
        ActionListener submitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if ("Submit".equals(e.getActionCommand())) {
                    handleBtnSubmitClick();
                }
            }
        };

        evalPanel = new EvalPanel(submitListener); // Pass the submitListener to EvalPanel
        rightPanel = new JPanel();
        rightPanel.setBackground(OptionalSettings.getSecondColor());

        // Build the whole panel
        Container content = this.getContentPane();
        content.setBackground(OptionalSettings.getLabelForegroundColor());
        content.setLayout(new BorderLayout());
        content.add(rightPanel, BorderLayout.EAST);
        content.add(positionPanel, BorderLayout.NORTH);
        content.add(actionPanel, BorderLayout.WEST);
        content.add(grapherPanel, BorderLayout.CENTER);
        content.add(evalPanel, BorderLayout.SOUTH);
        this.setVisible(true);
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
            // todo faire un recompute de la méthode et passer par GUI
            grapherPanel.repaint(); // Redraw the GrapherPanel
            // Apply expression is exists
            if (!expression.isEmpty()) {
                // Create a Map to store the x-y pairs
                Map<Float, Float> xyPairs = new HashMap<>();
                // Loop through the range of X values and evaluate the function for each X
                for (float currentX = xmin; currentX <= xmax; currentX += step) {
                    float y = EvaluatorProvisoire.randomFunctionResult(expression, currentX);
                    xyPairs.put(currentX, y); // Store the x-y pair in the Map
                }
                this.grapherPanel.setcheckedEval(true);
                this.grapherPanel.setxyPairs(xyPairs);
                this.grapherPanel.repaint();
            } else {
                this.grapherPanel.setcheckedEval(false);
                this.grapherPanel.unsetxyPairs();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter numeric values.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuiGrapher();
            }
        });
    }
}
