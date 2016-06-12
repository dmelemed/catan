package melemed.catan.board.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import melemed.catan.board.Hexagon;
import melemed.catan.board.Resource;
import melemed.catan.config.Config;
import melemed.catan.ui.BoardShapeWrapper;

public class HexSkin implements BoardShapeWrapper {

	private static final Logger logger = LoggerFactory.getLogger(HexSkin.class);

	private static double HALF_SQRT_THREE = Math.sqrt(3) / 2;

	private Shape shape;
	
	public HexSkin(final Hexagon hex) {

		Polygon hexagon = new Polygon();

		hexagon.setFill(getColorForResourceHex(hex.getResource()));
		hexagon.getPoints().addAll(new Double[] { 0d, 1d, HALF_SQRT_THREE, 0.5, HALF_SQRT_THREE, -0.5, 0d, -1d,
				-HALF_SQRT_THREE, -0.5, -HALF_SQRT_THREE, 0.5 });
		hexagon.setScaleX(Config.SCALE);
		hexagon.setScaleY(Config.SCALE);

		hexagon.setTranslateX(2 * Config.HALF_SQRT_THREE * Config.SCALE * hex.getPos()
				+ HALF_SQRT_THREE * Config.SCALE * Math.abs(hex.getRow() - 2) + Config.OFFSET_X);
		hexagon.setTranslateY(1.5d * Config.SCALE * hex.getRow() + Config.OFFSET_Y);
		hexagon.setStroke(Color.LIGHTGREY);
		hexagon.setStrokeWidth(0.0010*Config.SCALE);

		hexagon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				logger.debug("Hexagon pressed");
				hex.pressed();
			}
		});
		shape = hexagon;
	}

	private Color getColorForResourceHex(Resource resource) {
		switch (resource) {
		case BRICK:
			return Color.FIREBRICK;
		case DESERT:
			return Color.BURLYWOOD;
		case ORE:
			return Color.GREY;
		case SHEEP:
			return Color.GREENYELLOW;
		case WHEAT:
			return Color.YELLOW;
		case WOOD:
			return Color.DARKGREEN;
		default:
			return Color.BLUE;
		}
	}

	@Override
	public Shape getShape() {
		return shape;
	}

}
