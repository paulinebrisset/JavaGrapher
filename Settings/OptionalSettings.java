package Settings;

import java.awt.Color;

/**
 * Just for fun and aestetics
 */
public class OptionalSettings {
    // Set main colors so we can access it easily from our different panels
    private static Color mainColor;
    private static Color secondColor;
    private static Color thirdColor;

    // Constructeur pour initialiser les couleurs
    static {
        mainColor = Color.decode("#3385d6");
        secondColor = Color.decode("#80b3e6");
        thirdColor = Color.decode("#b3d1f0");
    }

    // Méthode statique pour accéder à mainColor
    public static Color getMainColor() {
        return mainColor;
    }

    // Méthode statique pour accéder à secondColor
    public static Color getSecondColor() {
        return secondColor;
    }

    // Méthode statique pour accéder à thirdColor
    public static Color getThirdColor() {
        return thirdColor;
    }
}
