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
    private ActionListener clearListener;
    private ActionListener zoomListener;

    private ActionListener refreshListener;

    public ActionPanel(GrapherPanel grapherPanel, ActionListener refreshListener, ActionListener clearListener,
            ActionListener zoomListener) {
        this.clearListener = clearListener;
        this.refreshListener = refreshListener;
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
        zoomInButton = new JButton("Zoom In");
        zoomInButton.setBackground(ColorPalette.getButtonsColor());
        constraint.gridx = 0;
        constraint.gridy++;
        constraint.gridwidth = 1;
        add(zoomInButton, constraint);
        zoomInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Raise an ActionEvent for zoom in
                if (zoomListener != null) {
                    zoomListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "ZoomIn"));
                }
            }
        });

        // Create the zoom out button
        zoomOutButton = new JButton("Zoom Out");
        zoomOutButton.setBackground(ColorPalette.getButtonsColor());
        constraint.gridx = 1;
        constraint.gridwidth = 1;
        add(zoomOutButton, constraint);
        zoomOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Raise an ActionEvent for zoom out
                if (zoomListener != null) {
                    zoomListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "ZoomOut"));
                }
            }
        });

        // Create the refresh button
        refreshButton = new JButton("Refresh");
        refreshButton.setBackground(ColorPalette.getButtonsColor());
        constraint.gridx = 0;
        constraint.gridy++;
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

        // Move to the next row
        constraint.gridx = 1;
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

    public void refreshGrapherPanel() {
        if (refreshListener != null) {
            refreshListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Refresh"));
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

    public boolean getAutoStep() {
        return autostepCheckbox.isSelected();
    }
}
