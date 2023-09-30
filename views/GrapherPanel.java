package views;

import java.awt.*;
import java.util.Map;

import javax.swing.JPanel;

import settings.OptionalSettings;

/**
 * Graph zone itself
 */

public class GrapherPanel extends JPanel {

    protected float minX = -10;
    protected float maxX = 10;
    protected float minY = -10;
    protected float maxY = 10;
    protected float step = 0.05f;
    // protected float step = 0.01f;
    protected float rangeX = 0;
    protected float rangeY = 0;
    protected float Ox = 0;
    protected float Oy = 0;
    protected float gridX = 1.0f;
    protected float gridY = 1.0f;

    protected boolean autoStep = false;
    protected boolean drag = false;
    boolean checkedEval = false;
    private Map<Float, Float> xyPairs;
    protected ActionPanel actionPanel;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(OptionalSettings.getLabelForegroundColor());
        g.fillRect(0, 0, getWidth(), getHeight());
        /* Relation between graphic size and screen size */
        if (!drag) {
            int w = getSize().width;
            rangeX = (maxX - minX) / w;
            int h = getSize().height;
            rangeY = (maxY - minY) / h;
        }

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

        // Draw x-axis and graduations for negative values
        for (float x = -gridX; x >= minX; x -= gridX) {
            float xi = (x / rangeX) + Ox;
            float yi = Oy;
            g2d.drawLine(Math.round(xi), Math.round(yi), Math.round(xi), Math.round(yi) - size);
            // Add graduation labels
            g2d.drawString(String.format("%.1f", x), Math.round(xi) - 10, Math.round(yi) + 15);
        }

        // Draw x-axis and graduations for positive values
        for (float x = gridX; x <= maxX; x += gridX) {
            float xi = (x / rangeX) + Ox;
            float yi = Oy;
            g2d.drawLine(Math.round(xi), Math.round(yi), Math.round(xi), Math.round(yi) - size);
            // Add graduation labels
            g2d.drawString(String.format("%.1f", x), Math.round(xi) - 10, Math.round(yi) + 15);
        }

        // Draw y-axis and graduations for negative values
        for (float y = -gridY; y >= minY; y -= gridY) {
            float yi = -(y / rangeY) + Oy;
            float xi = Ox;
            g2d.drawLine(Math.round(xi), Math.round(yi), Math.round(xi) + size, Math.round(yi));
            // Add graduation labels
            g2d.drawString(String.format("%.1f", y), Math.round(xi) - 40, Math.round(yi));
        }

        // Draw y-axis and graduations for positive values
        for (float y = gridY; y <= maxY; y += gridY) {
            float yi = -(y / rangeY) + Oy;
            float xi = Ox;
            g2d.drawLine(Math.round(xi), Math.round(yi), Math.round(xi) + size, Math.round(yi));
            // Add graduation labels
            g2d.drawString(String.format("%.1f", y), Math.round(xi) - 40, Math.round(yi));
        }

        // Draw x-axis again to ensure it's not overwritten by negative graduations
        g2d.drawLine(Math.round(-sizeAxis), Math.round(Oy), Math.round(sizeAxis), Math.round(Oy));

        // Draw y-axis again to ensure it's not overwritten by negative graduations
        g2d.drawLine(Math.round(Ox), Math.round(sizeAxis), Math.round(Ox), Math.round(-sizeAxis));
    }

    // Method to draw a curve based on xyPairs
    public void drawCurve(Graphics g, Map<Float, Float> xyPairs) {
        if (xyPairs == null || xyPairs.isEmpty()) {
            return;
        }
        /*
         * Style.
         * Cast to Graphics2D
         */
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(OptionalSettings.getButtonsColor());
        g2d.setStroke(new BasicStroke(3));

        int prevX = -1; // Previous X coordinate (initialized to an invalid value)
        int prevY = -1; // Previous Y coordinate (initialized to an invalid value)

        for (Map.Entry<Float, Float> entry : xyPairs.entrySet()) {
            float x = entry.getKey();
            float y = entry.getValue();

            // Convert real-world coordinates to screen coordinates
            int screenX = Math.round((x - minX) / rangeX);
            int screenY = Math.round((maxY - y) / rangeY);

            if (prevX != -1 && prevY != -1) {
                // Draw a line connecting the previous point to the current point
                g2d.drawLine(prevX, prevY, screenX, screenY);
            }

            prevX = screenX;
            prevY = screenY;
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
        return xyPairs != null && !xyPairs.isEmpty();
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

    public float getStep() {
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

    public void setcheckedEval(boolean checkedEval) {
        this.checkedEval = checkedEval;
    }
}
