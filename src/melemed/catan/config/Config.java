package melemed.catan.config;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Config {

	// Gameplay
	public final static int NUMBER_OF_PLAYERS = 4;
	
	// Graphics
	// Sizing & layout
	public static final double SCALE = 80;
	public static final double BORDER_WIDTH = 1.6 * SCALE;
	public static final double OFFSET_X = 2.7 * SCALE;
	public static final double OFFSET_Y = 1.1 * SCALE;
	public static final double HALF_SQRT_THREE = 0.866025;
	public static final double BUTTON_HEIGHT = 30;
	public static final boolean DEFAULT_MAXIMIZED = true;
	
	// Colors
//	public static final Color EDGE_SKIN_COLOR = Color.PALEVIOLETRED;
	public static final Color EDGE_HOVER_COLOR = Color.PALEGOLDENROD;
	public static final Color EDGE_SKIN_COLOR = Color.LIGHTGREY;

	public static final Color PLAYER_ONE_COLOR = Color.BLUE;
	public static final Color PLAYER_TWO_COLOR = Color.PALEVIOLETRED;
	public static final Color PLAYER_THREE_COLOR = Color.DARKORANGE;
	public static final Color PLAYER_FOUR_COLOR = Color.RED;

	public static final Color VERTEX_SKIN_COLOR = Color.LIGHTGREY;
	public static final Color VERTEX_HOVER_COLOR = Color.PALEGOLDENROD;

	public static final double HAND_SPACING = 100d;

}
