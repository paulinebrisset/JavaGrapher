package Panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Settings.OptionalSettings;
import java.awt.Dimension;
import java.awt.FlowLayout;

/*
 * Panel for the user to know about his mouse position
 */
public class PositionPanel extends JPanel {

    private final JTextField xTextField;
    private final JTextField yTextField;
    private final JTextField fxTextField;
    private final GrapherPanel grapherPanel;
    private final GraphMouse graphMouse;

    public PositionPanel(GrapherPanel grapherPanel, GraphMouse graphMouse) {
        this.grapherPanel = grapherPanel;
        this.graphMouse = graphMouse;
        // Initialize panel
        this.setBackground(OptionalSettings.getMainColor());
        this.setLayout(new FlowLayout());

        // 'x'
        this.add(new JLabel("x = "));

        xTextField = createFormattedTextField();
        this.add(xTextField);

        // 'y'
        this.add(new JLabel("y = "));
        yTextField = createFormattedTextField();
        this.add(yTextField);

        // 'f(x)'
        this.add(new JLabel("f(x) = "));
        fxTextField = createFormattedTextField();
        this.add(fxTextField);

        // Add a property change listener to track mouse position on GrapherPanel
        graphMouse.addPropertyChangeListener(evt -> {
            // Update x and y based on mouseX and mouseY
            if ("mouseX".equals(evt.getPropertyName())) {
                setX((int) evt.getNewValue());
            } else if ("mouseY".equals(evt.getPropertyName())) {
                setY((int) evt.getNewValue());
            }
        });
    }

    // Helper method to create and configure text fields
    private JTextField createFormattedTextField() {
        JTextField textField = new JTextField("");
        textField.setEditable(false);
        textField.setPreferredSize(new Dimension(60, 24));
        textField.setBackground(OptionalSettings.getLabelForegroundColor());
        return textField;
    }

    // SETTERS
    public void setX(int x) {
        xTextField.setText(String.valueOf(x));
    }

    public void setY(int y) {
        yTextField.setText(String.valueOf(y));
    }

    public void setFX(float fx) {
        fxTextField.setText(String.valueOf(fx));
    }
}
