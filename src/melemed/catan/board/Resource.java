package melemed.catan.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Resource {

	BRICK, DESERT, ORE, SHEEP, WHEAT, WOOD;

	private static final Logger logger = LoggerFactory.getLogger(Resource.class);

	private String styleName;

	static {
		BRICK.styleName = "brick";
		DESERT.styleName = "desert";
		BRICK.styleName = "ore";
		BRICK.styleName = "sheep";
		BRICK.styleName = "wheat";
		BRICK.styleName = "wood";
	}

	public String getStyleName() {
		return styleName;
	}

	@Override
	public String toString() {
		switch (this) {
		case BRICK:
			return "Brick";
		case DESERT:
			return "Desert";
		case ORE:
			return "Ore";
		case SHEEP:
			return "Sheep";
		case WHEAT:
			return "Wheat";
		case WOOD:
			return "Wood";
		default:
			return "";
		}
	}
}