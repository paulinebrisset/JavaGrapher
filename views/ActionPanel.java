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
    private JCheckBox autostepCheckbox;
    private JButton zoomInButton;
    private JButton zoomOutButton;

    private GrapherPanel grapherPanel;
    private ActionListener actionListener;

    public ActionPanel(GrapherPanel grapherPanel, ActionListener actionListener) {
        this.actionListener = actionListener;
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
            textField.setPreferredSize(new Dimension(100, 20));
            textField.setBackground(ColorPalette.getLabelForegroundColor());
            add(textField, constraint);
            constraint.gridx = 0;
            constraint.gridy++;
        }

        // Create the autostep checkbox
        autostepCheckbox = new JCheckBox("Auto Step");
        autostepCheckbox.setBackground(ColorPalette.getSecondColor());
        autostepCheckbox.setSelected(false); // Initialize to unchecked
        constraint.gridx = 1;
        constraint.gridy++;
        constraint.gridwidth = 2;
        add(autostepCheckbox, constraint);

        // Create the zoom in button
        zoomInButton = createButton("Zoom In", "ZoomIn");
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.gridwidth = 1;
        add(zoomInButton, constraint);

        // Create the zoom out button
        zoomOutButton = createButton("Zoom Out", "ZoomOut");
        constraint.gridx = 1;
        constraint.gridwidth = 1;
        add(zoomOutButton, constraint);

        // Create the refresh button
        refreshButton = createButton("Refresh", "Refresh");
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.gridwidth = 2;
        add(refreshButton, constraint);

        // Create the clear button
        clearButton = createButton("Clear", "Clear");
        constraint.gridx = 1;
        constraint.gridwidth = 2;
        add(clearButton, constraint);
    }

    // Helper method to create a button and add action listener
    private JButton createButton(String label, String command) {
        JButton button = new JButton(label);
        button.setBackground(ColorPalette.getButtonsColor());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Raise an ActionEvent with the specified command
                if (actionListener != null) {
                    actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, command));
                }
            }
        });
        return button;
    }

    // Getters for field values
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

    public boolean getAutoStep() {
        return autostepCheckbox.isSelected();
    }

    // Setters
    public void setXminField(float x) {
        this.xminField.setText(String.valueOf(x));
    }

    public void setXmaxField(float x) {
        this.xmaxField.setText(String.valueOf(x));
    }

    public void setYminField(float y) {
        this.yminField.setText(String.valueOf(y));
    }

    public void setYmaxField(float y) {
        this.ymaxField.setText(String.valueOf(y));
    }

    public void setStepField(float step) {
        this.stepField.setText(String.valueOf(step));
    }

    public void setXGridField(float xGrid) {
        this.xGridField.setText(String.valueOf(xGrid));
    }

    public void setYGridField(float yGrid) {
        this.yGridField.setText(String.valueOf(yGrid));
    }

}
