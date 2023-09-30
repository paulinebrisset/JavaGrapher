package settings;

import java.awt.Color;

/**
 * Just for fun and aestetics
 */
public class OptionalSettings {
    // Set main colors so we can access it easily from our different panels
    private static Color mainColor;
    private static Color secondColor;
    private static Color thirdColor;
    private static Color fourthColor;
    private static Color labelForgroundColor;

    static {
        mainColor = Color.decode("#D1CFFC");
        secondColor = Color.decode("#fcdedc");
        thirdColor = Color.decode("#ffffff");
        fourthColor = Color.decode("#FCFADF");
        labelForgroundColor = Color.decode("#FCF3C2");
    }

    // Static method to initiliaze colors
    public static Color getMainColor() {
        return mainColor;
    }

    public static Color getSecondColor() {
        return secondColor;
    }

    public static Color getLabelForegroundColor() {
        return thirdColor;
    }

    public static Color getFourthColor() {
        return fourthColor;
    }

    public static Color getButtonsColor() {
        return labelForgroundColor;
    }
}
