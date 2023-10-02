package settings;

/*
 * Initialize default values for the GrapherPanel. Called in GrapherPanel constructor
 * Final class to prevent inheritance
 */
public class GridSettings {

    public static final float MIN_X = -10;
    public static final float MAX_X = 10;
    public static final float MIN_Y = -10;
    public static final float MAX_Y = 10;
    public static final float STEP = 0.05f;
    public static final float RANGE_X = 0;
    public static final float RANGE_Y = 0;
    public static final float OX = 0;
    public static final float OY = 0;
    public static final float GRID_X = 1.0f;
    public static final float GRID_Y = 1.0f;
    public static final boolean IS_AUTO_STEP = false;
    public static final float DEFAULT_AUTO_STEP = 140f;
    public static final int POINT_SIZE = 8;
}