package Panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

/*
 * Panel for the user to know about his mouse position
 */
public class PositionPanel extends JPanel {

    protected JTextField x = new JTextField("");
    protected JTextField y = new JTextField("");
    protected JTextField fx = new JTextField("");
    protected GrapherPanel grapherPanel;
    protected GraphMouse graphMouse;

    public PositionPanel(Color backgroundColor, GrapherPanel grapherPanel, GraphMouse graphMouse) {
        // Initialisation
        this.setBackground(backgroundColor);
        this.setLayout(new FlowLayout());
        this.add(new JLabel("x = "));
        x.setEditable(false);
        x.setPreferredSize(new Dimension(60, 24));
        this.add(x);
        this.add(new JLabel("y = "));
        y.setEditable(false);
        y.setPreferredSize(new Dimension(60, 24));
        this.add(y);
        this.add(new JLabel("f(x) = "));
        fx.setEditable(false);
        fx.setPreferredSize(new Dimension(60, 24));
        this.add(fx);
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

    public void setX(float x) {
        this.x.setText(String.valueOf(x));
    }

    public void setY(float y) {
        this.y.setText(String.valueOf(y));
    }

    public void setFX1(float fx) {
        this.fx.setText(String.valueOf(fx));
    }
}
