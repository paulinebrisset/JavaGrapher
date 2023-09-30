import javax.swing.*;
import Panels.*;
import Settings.OptionalSettings;

import java.awt.*;

public class GuiGrapher extends JFrame {
    protected JPanel rightPanel;
    protected PositionPanel positionPanel;
    protected ActionPanel actionPanel;
    protected EvalPanel evalPanel;
    protected GrapherPanel grapherPanel;

    public GuiGrapher() {
        this.setTitle("Grapher");
        this.setSize(1200, 700);
        // Display it in the middle of the screen
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1, 2));

        // Initialisation des éléments :

        grapherPanel = new GrapherPanel();
        GraphMouse graphMouse = new GraphMouse(grapherPanel);
        positionPanel = new PositionPanel(graphMouse);
        actionPanel = new ActionPanel(grapherPanel);
        evalPanel = new EvalPanel();
        rightPanel = new JPanel();
        rightPanel.setBackground(OptionalSettings.getSecondColor());

        // Build whole panel
        Container content = this.getContentPane();
        content.setBackground(OptionalSettings.getLabelForegroundColor());
        content.setLayout(new BorderLayout());
        content.add(rightPanel, BorderLayout.EAST);
        content.add(positionPanel, BorderLayout.NORTH);
        content.add(actionPanel, BorderLayout.WEST);
        content.add(grapherPanel, BorderLayout.CENTER);
        content.add(evalPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GuiGrapher();
    }
}
