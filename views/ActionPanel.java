package views;

import javax.swing.*;

import settings.ColorPalette;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel for the user to set the grapher grid characteristics
 */
public class ActionPanel extends JPanel {
    private JTextField xminField;
    private JTextField xmaxField;
    private JTextField yminField;
    private JTextField ymaxField;
    private JTextField stepField;
    private JTextField xGridField;
    private JTextField yGridField;
    private JButton refreshButton;
    private JButton clearButton;

    private GrapherPanel grapherPanel;
    private ActionListener clearListener;

    public ActionPanel(GrapherPanel grapherPanel, ActionListener refreshListener, ActionListener clearListener) {
        this.clearListener = clearListener;
        this.setBackground(ColorPalette.getSecondColor());
        this.setLayout(new GridBagLayout());
        this.grapherPanel = grapherPanel;

        // Define label texts and corresponding fields
        String[] labelTexts = { "xmin:", "xmax:", "ymin:", "ymax:", "step:", "x grid:", "y grid:" };
        xminField = new JTextField(String.valueOf(grapherPanel.getMinX()));
        xmaxField = new JTextField(String.valueOf(grapherPanel.getMaxX()));
        yminField = new JTextField(String.valueOf(grapherPanel.getMinY()));
        ymaxField = new JTextField(String.valueOf(grapherPanel.getMaxY()));
        stepField = new JTextField(String.valueOf(grapherPanel.getStep()));
        xGridField = new JTextField(String.valueOf(grapherPanel.getGridX()));
        yGridField = new JTextField(String.valueOf(grapherPanel.getGridY()));

        // Create labels and add them along with their text fields
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.anchor = GridBagConstraints.WEST; // Align labels to the left
        constraint.insets = new Insets(5, 5, 5, 5); // Add some padding

        for (int i = 0; i < labelTexts.length; i++) {
            JLabel label = new JLabel(labelTexts[i]);
            add(label, constraint);

            constraint.gridx = 1;
            JTextField textField = i == 0 ? xminField
                    : i == 1 ? xmaxField
                            : i == 2 ? yminField
                                    : i == 3 ? ymaxField : i == 4 ? stepField : i == 5 ? xGridField : yGridField;
            textField.setPreferredSize(new Dimension(100, 20)); // Set preferred size with a lower height
            textField.setBackground(ColorPalette.getLabelForegroundColor());
            add(textField, constraint);

            constraint.gridx = 0;
            constraint.gridy++;
        }

        // Create the refresh button
        refreshButton = new JButton("Refresh");
        refreshButton.setBackground(ColorPalette.getButtonsColor());
        constraint.gridx = 1;
        constraint.gridwidth = 2;
        add(refreshButton, constraint);
        // Add action listener to the refresh button
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshGrapherPanel();
            }
        });

        // Create the clear button
        clearButton = new JButton("Clear");
        clearButton.setBackground(ColorPalette.getButtonsColor());
        constraint.gridx = 1; // Reset gridx to 0 to place in the first column
        constraint.gridy++; // Move to the next row
        constraint.gridwidth = 2;
        add(clearButton, constraint);
        // Add action listener to the refresh button
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionClearGrapherPanel();
            }
        });
    }

    // Listener for click on submit
    public void actionClearGrapherPanel() {
        if (clearListener != null) {
            clearListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Clear"));
        }
    }

    private void refreshGrapherPanel() {
        // TODO déménager tout le monde dans GrapherPanel
        // Get the values from the text fields and update the GrapherPanel
        try {
            float xmin = xminField.getText().isEmpty() ? grapherPanel.getMinX() : Float.parseFloat(xminField.getText());
            float xmax = xmaxField.getText().isEmpty() ? grapherPanel.getMaxX() : Float.parseFloat(xmaxField.getText());
            float ymin = yminField.getText().isEmpty() ? grapherPanel.getMinY() : Float.parseFloat(yminField.getText());
            float ymax = ymaxField.getText().isEmpty() ? grapherPanel.getMaxY() : Float.parseFloat(ymaxField.getText());
            float step = stepField.getText().isEmpty() ? grapherPanel.getStep() : Float.parseFloat(stepField.getText());
            float xGrid = xGridField.getText().isEmpty() ? grapherPanel.getGridX()
                    : Float.parseFloat(xGridField.getText());
            float yGrid = yGridField.getText().isEmpty() ? grapherPanel.getGridY()
                    : Float.parseFloat(yGridField.getText());

            grapherPanel.setMinMaxX(xmin, xmax);
            grapherPanel.setMinMaxY(ymin, ymax);
            grapherPanel.setStep(step);
            grapherPanel.setGridX(xGrid);
            grapherPanel.setGridY(yGrid);
            // todo faire un recompute de la méthode et passer par GUI
            grapherPanel.repaint(); // Redraw the GrapherPanel
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter numeric values.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Getters
    public String getXminField() {
        return xminField.getText();
    }

    public String getXmaxField() {
        return xmaxField.getText();
    }

    public String getYminField() {
        return yminField.getText();
    }

    public String getYmaxField() {
        return ymaxField.getText();
    }

    public String getStepField() {
        return stepField.getText();
    }

    public String getXGridField() {
        return xGridField.getText();
    }

    public String getYGridField() {
        return yGridField.getText();
    }
}
