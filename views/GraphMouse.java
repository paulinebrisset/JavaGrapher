package views;

import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GraphMouse extends MouseAdapter {
    // Raw mouse position (without grid adjustments)
    public float rawMouseX = 0;
    public float rawMouseY = 0;

    // Mouse position taking the grid into account
    public float mouseX = 0;
    public float mouseY = 0;
    public float mouseFx = 0;

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
        float[] updatedPosition = grapherPanel.adjustMousePosition((float) rawMouseX, (float) rawMouseY);
        float mouseX = updatedPosition[0];
        float mouseY = updatedPosition[1];

        // Notify property change listeners
        propertyChangeSupport.firePropertyChange("mouseX", null, mouseX);
        propertyChangeSupport.firePropertyChange("mouseY", null, mouseY);
        boolean isData = grapherPanel.isData();
        if (isData) {
            mouseFx = grapherPanel.getFxForX(mouseX);
            if (!Float.isNaN(mouseFx)) {
                propertyChangeSupport.firePropertyChange("mouseFx", null, mouseFx);
            }
        }

    }

    // Add a property change listener to this instance
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
}
