package settings;

import java.awt.Color;

/**
 * Just for fun and aesthetics
 */
public class ColorPalette {
    // Set main colors so we can access them easily from our different panels
    private static final Color PURPLE_COLOR = Color.decode("#D1CFFC");
    private static final Color PINK_COLOR = Color.decode("#FCDEDC");
    private static final Color LABEL_FOREGROUND_COLOR = Color.decode("#FFFFFF");
    private static final Color BUTTONS_COLOR = Color.decode("#FCFADF");

    // Static method to initialize colors
    public static Color getMainColor() {
        return PURPLE_COLOR;
    }

    public static Color getSecondColor() {
        return PINK_COLOR;
    }

    public static Color getLabelForegroundColor() {
        return LABEL_FOREGROUND_COLOR;
    }

    public static Color getButtonsColor() {
        return BUTTONS_COLOR;
    }
}