package views;

import java.awt.Point;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GraphMouse extends MouseAdapter {
    // Raw mouse position (without grid adjustments)
    public int rawMouseX = 0;
    public int rawMouseY = 0;

    // Mouse position taking the grid into account
    public int mouseX = 0;
    public int mouseY = 0;

    // Reference to the GrapherPanel
    protected GrapherPanel grapherPanel;

    // Property change support for notifying listeners of mouse position changes
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public GraphMouse(GrapherPanel grapherPanel) {
        this.grapherPanel = grapherPanel;

        // Apply the mouse listener to the GrapherPanel
        grapherPanel.addMouseListener(this);
        grapherPanel.addMouseMotionListener(this);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Capture the untreated mouse coordinates
        rawMouseX = e.getX();
        rawMouseY = e.getY();

        // Take the grid into account
        Point updatedPoint = grapherPanel.adjustMousePosition(new Point((int) rawMouseX, (int) rawMouseY));
        mouseX = (int) updatedPoint.getX();
        mouseY = (int) updatedPoint.getY();

        // Notify property change listeners
        propertyChangeSupport.firePropertyChange("mouseX", null, mouseX);
        propertyChangeSupport.firePropertyChange("mouseY", null, mouseY);
    }

    // Add a property change listener to this instance
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
}
