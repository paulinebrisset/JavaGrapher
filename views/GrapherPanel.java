package views;

import java.awt.*;
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
    private boolean drag;
    private boolean checkedEval;

    private Map<Float, Float> xyPairs;
    protected ActionPanel actionPanel;

    public GrapherPanel() {
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
        this.autoStep = GridSettings.AUTO_STEP;
        this.drag = false;
        this.checkedEval = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(ColorPalette.getLabelForegroundColor());
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
    // Method to draw individual points based on xyPairs
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

    // Zoom
    public void actionZoomIn() {
        minX += (maxX - minX) * 0.1f;
        maxX -= (maxX - minX) * 0.1f;
        minY += (maxY - minY) * 0.1f;
        maxY -= (maxY - minY) * 0.1f;

        repaint();
    }

    public void actionZoomOut() {
        minX -= (maxX - minX) * 0.1f;
        maxX += (maxX - minX) * 0.1f;
        minY -= (maxY - minY) * 0.1f;
        maxY += (maxY - minY) * 0.1f;

        repaint();

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

    public void unsetxyPairs() {
        this.xyPairs = null;
    }

    public void setcheckedEval(boolean checkedEval) {
        this.checkedEval = checkedEval;
    }

    // TODO use it
    public void setAutoStep(boolean autoStep) {
        this.autoStep = autoStep;
    }
}
