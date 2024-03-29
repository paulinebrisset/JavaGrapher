package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JPanel;
import settings.ColorPalette;
import settings.GridSettings;

/**
 * Graph zone itself
 */

public class GrapherPanel extends JPanel {

    private float minX;
    private float maxX;
    private float minY;
    private float maxY;
    private float step;
    private float rangeX;
    private float rangeY;
    private float Ox;
    private float Oy;
    private float gridX;
    private float gridY;
    private boolean autoStep;
    private boolean checkedEval;

    private Map<Float, Float> xyPairs;
    protected ActionPanel actionPanel;
    private ActionListener repaintListener;

    public GrapherPanel(ActionListener repaintListener) {
        this.repaintListener = repaintListener;
        this.minX = GridSettings.MIN_X;
        this.maxX = GridSettings.MAX_X;
        this.minY = GridSettings.MIN_Y;
        this.maxY = GridSettings.MAX_Y;
        this.step = GridSettings.STEP;
        this.gridX = GridSettings.GRID_X;
        this.gridY = GridSettings.GRID_Y;
        this.rangeX = GridSettings.RANGE_X;
        this.rangeY = GridSettings.RANGE_Y;
        this.Ox = GridSettings.OX;
        this.Oy = GridSettings.OY;
        this.autoStep = GridSettings.IS_AUTO_STEP;
        this.checkedEval = false;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(ColorPalette.getLabelForegroundColor());
        g.fillRect(0, 0, getWidth(), getHeight());
        /* Relation between graphic size and screen size */

        int w = getSize().width;
        rangeX = (maxX - minX) / w;
        int h = getSize().height;
        rangeY = (maxY - minY) / h;

        // Initialization of the origin of the coordinate system
        Ox = -minX / rangeX;
        Oy = maxY / rangeY;

        if (checkedEval) {
            drawCurve(g, xyPairs);
        }
        // Draw axis
        drawAxes(g);
    }

    // Draw axes and graduations
    public void drawAxes(Graphics g) {
        int size = 5;
        float sizeAxis = (getSize().height / rangeX) + Ox;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2)); // Set line thickness
        // modulo if for guetting axis readable
        int modulo = getModulo();
        // Draw x-axis and graduations for negative values
        for (float x = -gridX; x >= minX; x -= gridX) {
            // Calculate the x-coordinate on the for the current value of 'x'
            float xi = (x / rangeX) + Ox;

            // y coordinate of the x-axis
            float yi = Oy;

            // Draw a vertical line representing a graduation mark on the x-axis
            g2d.drawLine(Math.round(xi), Math.round(yi), Math.round(xi), Math.round(yi) - size);

            if (Math.abs(x) % modulo == 0) {
                // Add graduation labels (convert 'x' to a string)
                g2d.drawString(String.format("%.1f", x), Math.round(xi) - 10, Math.round(yi) + 15);
            }
        }

        // Draw x-axis and graduations for positive values
        for (float x = gridX; x <= maxX; x += gridX) {
            float xi = (x / rangeX) + Ox;
            float yi = Oy;
            g2d.drawLine(Math.round(xi), Math.round(yi), Math.round(xi), Math.round(yi) - size);
            if (Math.abs(x) % modulo == 0) {
                // Add graduation labels
                g2d.drawString(String.format("%.1f", x), Math.round(xi) - 10, Math.round(yi) + 15);
            }
        }

        // Draw y-axis and graduations for negative values
        for (float y = -gridY; y >= minY; y -= gridY) {
            float yi = -(y / rangeY) + Oy;
            float xi = Ox;
            g2d.drawLine(Math.round(xi), Math.round(yi), Math.round(xi) + size, Math.round(yi));
            if (Math.abs(y) % modulo == 0) {
                // Add graduation labels
                g2d.drawString(String.format("%.1f", y), Math.round(xi) - 40, Math.round(yi));
            }
        }

        // Draw y-axis and graduations for positive values
        for (float y = gridY; y <= maxY; y += gridY) {
            float yi = -(y / rangeY) + Oy;
            float xi = Ox;
            g2d.drawLine(Math.round(xi), Math.round(yi), Math.round(xi) + size, Math.round(yi));
            if (Math.abs(y) % modulo == 0) {
                // Add graduation labels
                g2d.drawString(String.format("%.1f", y), Math.round(xi) - 40, Math.round(yi));
            }
        }

        // Draw x-axis again to ensure it's not overwritten by negative graduations
        g2d.drawLine(Math.round(-sizeAxis), Math.round(Oy), Math.round(sizeAxis), Math.round(Oy));

        // Draw y-axis again to ensure it's not overwritten by negative graduations
        g2d.drawLine(Math.round(Ox), Math.round(sizeAxis), Math.round(Ox), Math.round(-sizeAxis));
    }

    private int getModulo() {
        /**
         * Help to keep axis readable to give a step for labels printing
         */

        if (rangeX <= 0.02) {
            return 1;
        } else if (rangeX <= 0.04) {
            return 2;
        } else if (rangeX <= 0.05) {
            return 5;
        } else if (rangeX <= 0.07) {
            return 10;
        } else if (rangeX <= 0.3) {
            return 20;
        } else if (rangeX <= 1) {
            return 50;
        } else {
            return 100;
        }
    }

    // Method to draw a curve based on xyPairs
    public void drawCurve(Graphics g, Map<Float, Float> xyPairs) {
        if (xyPairs == null || xyPairs.isEmpty()) {
            return;
        }

        // Style
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(ColorPalette.getSecondColor());
        g2d.setStroke(new BasicStroke(5));

        for (Map.Entry<Float, Float> entry : xyPairs.entrySet()) {
            float x = entry.getKey();
            float y = entry.getValue();

            // Convert real-world coordinates to screen coordinates
            int screenX = Math.round((x - minX) / rangeX);
            int screenY = Math.round((maxY - y) / rangeY);

            // Draw a point at the current coordinates
            int pointSize = GridSettings.POINT_SIZE;
            g2d.fillOval(screenX - pointSize / 2, screenY - pointSize / 2, pointSize, pointSize);
        }
    }

    public void clearGraph() {
        minX = GridSettings.MIN_X;
        maxX = GridSettings.MAX_X;
        minY = GridSettings.MIN_Y;
        maxY = GridSettings.MAX_Y;
        step = GridSettings.STEP;
        gridX = GridSettings.GRID_X;
        gridY = GridSettings.GRID_Y;
        rangeX = GridSettings.RANGE_X;
        rangeY = GridSettings.RANGE_Y;
        Ox = GridSettings.OX;
        Oy = GridSettings.OY;
        autoStep = GridSettings.IS_AUTO_STEP;
        checkedEval = false;
        repaint();
        if (repaintListener != null) {
            repaintListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Repaint"));
        }
    }

    // Zoom
    public void actionZoomIn() {
        minX += (maxX - minX) * 0.1f;
        maxX -= (maxX - minX) * 0.1f;
        minY += (maxY - minY) * 0.1f;
        maxY -= (maxY - minY) * 0.1f;
        repaint();
        // Triggers GuiGrapher so that values are computed again
        if (repaintListener != null) {
            repaintListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Repaint"));
        }
    }

    public void actionZoomOut() {
        minX -= (maxX - minX) * 0.1f;
        maxX += (maxX - minX) * 0.1f;
        minY -= (maxY - minY) * 0.1f;
        maxY += (maxY - minY) * 0.1f;
        repaint();

        // Triggers GuiGrapher so that values are computed again
        if (repaintListener != null) {
            repaintListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Repaint"));
        }
    }

    // GETTERS
    // Adjust coordinates
    public float[] adjustMousePosition(float mouseX, float mouseY) {
        float adjustedX = (mouseX - getWidth() / 2f) * (maxX - minX) / getWidth() + (maxX + minX) / 2f;
        float adjustedY = (getHeight() / 2f - mouseY) * (maxY - minY) / getHeight() + (maxY + minY) / 2f;
        adjustedX = roundToNearestStep(adjustedX);
        adjustedY = roundToNearestStep(adjustedY);
        return new float[] { adjustedX, adjustedY };
    }

    public boolean isData() {
        return xyPairs != null;
    }

    public Float getFxForX(float x) {
        if (isData()) {
            float tolerance = step; // Adjust this tolerance based on your step size
            for (Map.Entry<Float, Float> entry : xyPairs.entrySet()) {
                float key = entry.getKey();
                if (Math.abs(key - x) < tolerance) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    // Round x to the nearest step value
    private float roundToNearestStep(float x) {
        return Math.round(x / step) * step;
    }

    public float getMaxY() {
        return maxY;
    }

    public float getMinY() {
        return minY;
    }

    public float getMaxX() {
        return maxX;
    }

    public float getMinX() {
        return minX;
    }

    // Take into account if autoStep is checked
    public float getStep() {
        if (this.autoStep) {
            step = (maxX - minX) / GridSettings.DEFAULT_AUTO_STEP;
        }
        return step;
    }

    public float getGridX() {
        return gridX;
    }

    public float getGridY() {
        return gridY;
    }

    // SETTERS
    public void setMinMaxX(float xmin, float xmax) {
        this.minX = xmin;
        this.maxX = xmax;
    }

    public void setMinMaxY(float ymin, float ymax) {
        this.minY = ymin;
        this.maxY = ymax;
    }

    public void setStep(float step) {
        this.step = step;
    }

    public void setGridX(float xGrid) {
        this.gridX = xGrid;
    }

    public void setGridY(float yGrid) {
        this.gridY = yGrid;
    }

    public void setxyPairs(Map<Float, Float> xyPairs) {
        this.xyPairs = xyPairs;
    }

    public void unsetxyPairs() {
        this.xyPairs = null;
    }

    public void setcheckedEval(boolean checkedEval) {
        this.checkedEval = checkedEval;
    }

    public void setAutoStep(boolean autoStep) {
        this.autoStep = autoStep;
    }
}
