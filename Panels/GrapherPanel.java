package Panels;

import java.awt.*;
import javax.swing.JPanel;

/**
 * Graph zone itself
 */

public class GrapherPanel extends JPanel {

    protected float minX = -10;
    protected float maxX = 10;
    protected float minY = -10;
    protected float maxY = 10;
    protected float step = 0.01f;
    protected float rangeX = 0;
    protected float rangeY = 0;
    protected float Ox = 0;
    protected float Oy = 0;
    protected float gridX = 1.0f;
    protected float gridY = 1.0f;

    protected boolean autoStep = false;
    protected boolean drag = false;
    boolean checkedEval = false;

    protected ActionPanel actionPanel;

    // Adjust coordinates
    public Point adjustMousePosition(Point mousePosition) {
        int adjustedX = (int) ((mousePosition.getX() - Ox) / (gridX * step));
        int adjustedY = (int) ((Oy - mousePosition.getY()) / (gridY * step));
        return new Point(adjustedX, adjustedY);
    }

    public void paint(Graphics g) {
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

        // Draw axis
        drawAxes(g);
        // if (checkedEval)
        // drawFonc(g, evaluateur1, fct_color1);
    }

    // DRaw axis
    public void drawAxes(Graphics g) {

        int size = 5;
        float sizeAxis = (getSize().height / rangeX) + Ox;

        // Axis
        g.drawLine(Math.round(-sizeAxis), Math.round(Oy), Math.round(sizeAxis), Math.round(Oy));
        g.drawLine(Math.round(Ox), Math.round(sizeAxis), Math.round(Ox), Math.round(-sizeAxis));

        // Graduations
        // right x
        for (float x = gridX; x < maxX;) {
            float xi = (x / rangeX) + Ox;
            float yi = Oy;
            g.drawLine(Math.round(xi), Math.round(yi), Math.round(xi), Math.round(yi) - size);
            x += gridX;
        }
        // left x
        for (float x = -gridX; x > minX;) {
            float xi = (x / rangeX) + Ox;
            float yi = Oy;
            g.drawLine(Math.round(xi), Math.round(yi), Math.round(xi), Math.round(yi) - size);
            x -= gridX;
        }

        // bottom y
        for (float y = -gridY; y > minY;) {
            float yi = -(y / rangeY) + Oy;
            float xi = Ox;
            g.drawLine(Math.round(xi), Math.round(yi), Math.round(xi) + size, Math.round(yi));
            y -= gridY;
        }
        // upper y
        for (float y = gridY; y < maxY;) {
            float yi = -(y / rangeY) + Oy;
            float xi = Ox;
            g.drawLine(Math.round(xi), Math.round(yi), Math.round(xi) + size, Math.round(yi));
            y += gridY;
        }
    }

    // GETTERS
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
}
