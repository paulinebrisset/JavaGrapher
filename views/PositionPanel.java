package views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import settings.OptionalSettings;

import java.awt.Dimension;
import java.awt.FlowLayout;

/*
 * Panel for the user to know about his mouse position
 */
public class PositionPanel extends JPanel {

    private final JTextField xTextField;
    private final JTextField yTextField;
    private final JTextField fxTextField;

    public PositionPanel(GraphMouse graphMouse) {
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
                setX((float) evt.getNewValue());
            } else if ("mouseY".equals(evt.getPropertyName())) {
                setY((float) evt.getNewValue());
            } else if ("mouseFx".equals(evt.getPropertyName())) {
                setFX((float) evt.getNewValue());
            }
        });
    }

    // Helper method to create and configure text fields
    private JTextField createFormattedTextField() {
        JTextField textField = new JTextField("");
        textField.setEditable(false);
        textField.setPreferredSize(new Dimension(80, 24));
        textField.setBackground(OptionalSettings.getLabelForegroundColor());
        return textField;
    }

    // SETTERS
    public void setX(float x) {
        xTextField.setText(String.valueOf(x));
    }

    public void setY(float y) {
        yTextField.setText(String.valueOf(y));
    }

    public void setFX(float fx) {
        fxTextField.setText(String.valueOf(fx));
    }
}
