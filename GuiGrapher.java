import javax.swing.*;
import Panels.*;
import Settings.*;
import java.awt.*;

public class GuiGrapher extends JFrame {
    protected JPanel mainPanel;
    protected PositionPanel positionPanel;
    protected ActionPanel actionPanel;
    protected EvalPanel evalPanel;
    protected GrapherPanel grapherPanel;

    public GuiGrapher() {
        this.setTitle("Grapher");
        this.setSize(1200, 600);
        // Display it in the middle of the screen
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1, 2));

        // Initialisation des éléments :

        grapherPanel = new GrapherPanel();
        GraphMouse graphMouse = new GraphMouse(grapherPanel);
        positionPanel = new PositionPanel(OptionalSettings.getMainColor(), grapherPanel, graphMouse);

        actionPanel = new ActionPanel(grapherPanel, OptionalSettings.getSecondColor());
        // grapher.setActionPanel(actionPanel);
        // evalPanel = new EvalPanel(grapherPanel, mainColor);
        mainPanel = new JPanel();
        // Build whole panel
        Container content = this.getContentPane();
        content.setLayout(new BorderLayout());
        content.add(mainPanel, BorderLayout.EAST);
        content.add(positionPanel, BorderLayout.NORTH);
        content.add(actionPanel, BorderLayout.WEST);
        content.add(grapherPanel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GuiGrapher();
    }
}
