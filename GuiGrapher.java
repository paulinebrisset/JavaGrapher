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
        float minX = this.grapherPanel.getMinX();
        float maxX = this.grapherPanel.getMaxX();
        float step = this.grapherPanel.getStep();

        // Create a Map to store the x-y pairs
        Map<Float, Float> xyPairs = new HashMap<>();
        // Loop through the range of X values and evaluate the function for each X
        for (float currentX = minX; currentX <= maxX; currentX += step) {
            float y = EvaluatorProvisoire.randonFunctionResult(currentX);
            xyPairs.put(currentX, y); // Store the x-y pair in the Map
        }
        this.grapherPanel.setcheckedEval(true);
        this.grapherPanel.setxyPairs(xyPairs);
        this.grapherPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuiGrapher();
            }
        });
    }
}
