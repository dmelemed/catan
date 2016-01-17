package melemed.catan.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.paint.Color;
import melemed.catan.board.NumberToken;
import melemed.catan.config.Config;

public enum Team {

	ONE, TWO, THREE, FOUR;

	private static final Logger logger = LoggerFactory.getLogger(Team.class);

	public Color getColor() {
		switch(this) {
		case ONE:
			return Config.PLAYER_ONE_COLOR;
		case TWO:
			return Config.PLAYER_TWO_COLOR;
		case THREE:
			return Config.PLAYER_THREE_COLOR;
		case FOUR:
			return Config.PLAYER_FOUR_COLOR;
		default:
			return Color.BLACK;
		}
	}
	
}
