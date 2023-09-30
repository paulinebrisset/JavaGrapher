package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel for the user to set the grapher grid caracteristics
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

    private GrapherPanel grapherPanel;

    public ActionPanel(GrapherPanel grapherPanel, Color backgroundColor) {
        this.setBackground(backgroundColor);
        this.setLayout(new GridBagLayout());
        this.grapherPanel = grapherPanel;

        // Define label texts and corresponding fields
        String[] labelTexts = { "xmin:", "xmax:", "ymin:", "ymax:", "step:", "x grid:", "y grid:" };
        JTextField[] textFields = {
                new JTextField(String.valueOf(grapherPanel.minX)),
                new JTextField(String.valueOf(grapherPanel.maxX)),
                new JTextField(String.valueOf(grapherPanel.minY)),
                new JTextField(String.valueOf(grapherPanel.maxY)),
                new JTextField(String.valueOf(grapherPanel.step)),
                new JTextField(String.valueOf(grapherPanel.gridX)),
                new JTextField(String.valueOf(grapherPanel.gridY))
        };

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
            textFields[i].setPreferredSize(new Dimension(100, 20)); // Set preferred size with a lower height
            add(textFields[i], constraint);

            constraint.gridx = 0;
            constraint.gridy++;
        }

        // Create the refresh button
        refreshButton = new JButton("Refresh");
        constraint.gridx = 1;
        constraint.gridwidth = 2; // Span two columns for the button
        add(refreshButton, constraint);

        // Add action listener to the refresh button
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshGrapherPanel();
            }
        });
    }

    private void refreshGrapherPanel() {
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
            // TODO FIXME
            grapherPanel.repaint(); // Redraw the GrapherPanel
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter numeric values.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}