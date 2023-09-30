package Panels;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JPanel;

/**
 * Graph zone itself
 */

public class GrapherPanel extends JPanel {

    protected float minX = -10;
    protected float maxX = 10;
    protected float minY = -10;
    protected float maxY = 10;
    protected float pas = 0.01f;
    protected float rangeX = 0;
    protected float rangeY = 0;
    protected float Ox = 0;
    protected float Oy = 0;
    protected float gridX = 1.0f;
    protected float gridY = 1.0f;

    protected boolean auto_pas = false;
    protected boolean drag = false;
    boolean evalIsOk1 = false;
    boolean evalIsOk2 = false;

    protected ActionPanel actionPanel;

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    // Adjust coordinates
    public Point adjustMousePosition(Point mousePosition) {
        int adjustedX = (int) ((mousePosition.getX() - Ox) / (gridX * pas));
        System.out.println(adjustedX);
        int adjustedY = (int) ((Oy - mousePosition.getY()) / (gridY * pas));
        return new Point(adjustedX, adjustedY);
    }

    public void paint(Graphics g) {
        /* Relation between graphi size and screen size */
        if (!drag) {
            int w = getSize().width;
            rangeX = (maxX - minX) / w;
            int h = getSize().height;
            rangeY = (maxY - minY) / h;
        }

        // Initialisation de l'origine du repère
        Ox = -minX / rangeX;
        Oy = maxY / rangeY;

        // ** DESSIN DES AXES ET DE LA FONCTION
        drawAxes(g);
        // if (evalIsOk1)
        // drawFonc(g, evaluateur1, fct_color1);
        // if (evalIsOk2)
        // drawFonc(g, evaluateur2, fct_color2);
    }

    // Add a property change listener
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    // Dessine les axes du repère
    public void drawAxes(Graphics g) {

        int taille = 5;
        float taille_axes = (getSize().height / rangeX) + Ox;

        // Dessin des axes x et y
        g.drawLine(Math.round(-taille_axes), Math.round(Oy), Math.round(taille_axes), Math.round(Oy));
        g.drawLine(Math.round(Ox), Math.round(taille_axes), Math.round(Ox), Math.round(-taille_axes));

        // Dessin des graduations
        // x droite
        for (float x = gridX; x < maxX;) {
            float xi = (x / rangeX) + Ox;
            float yi = Oy;
            g.drawLine(Math.round(xi), Math.round(yi), Math.round(xi), Math.round(yi) - taille);
            x += gridX;
        }
        // x gauche
        for (float x = -gridX; x > minX;) {
            float xi = (x / rangeX) + Ox;
            float yi = Oy;
            g.drawLine(Math.round(xi), Math.round(yi), Math.round(xi), Math.round(yi) - taille);
            x -= gridX;
        }

        // y bas
        for (float y = -gridY; y > minY;) {
            float yi = -(y / rangeY) + Oy;
            float xi = Ox;
            g.drawLine(Math.round(xi), Math.round(yi), Math.round(xi) + taille, Math.round(yi));
            y -= gridY;
        }
        // y haut
        for (float y = gridY; y < maxY;) {
            float yi = -(y / rangeY) + Oy;
            float xi = Ox;
            g.drawLine(Math.round(xi), Math.round(yi), Math.round(xi) + taille, Math.round(yi));
            y += gridY;
        }
    }
    // public void refresh() {

    // this.minX = Float.valueOf(actionPanel.txt_x_min.getText());
    // this.maxX = Float.valueOf(actionPanel.txt_x_max.getText());
    // this.minY = Float.valueOf(actionPanel.txt_y_min.getText());
    // this.maxY = Float.valueOf(actionPanel.txt_y_max.getText());
    // this.gridX = Float.valueOf(actionPanel.txt_x_grid.getText());
    // this.gridY = Float.valueOf(actionPanel.txt_y_grid.getText());
    // this.pas = Float.valueOf(actionPanel.txt_pas.getText());
    // this.auto_pas = false;
    // this.actionPanel.check_auto_pas.setSelected(false);

    // repaint();
    // }
}
