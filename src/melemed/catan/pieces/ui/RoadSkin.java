package melemed.catan.pieces.ui;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import melemed.catan.config.Config;
import melemed.catan.pieces.Road;
import melemed.catan.ui.BoardShapeWrapper;

public class RoadSkin extends BoardShapeWrapper {

	private Shape shape;

	public RoadSkin(Road road) {
		shape = new Rectangle(0.6, 0.1);
		shape.setScaleX(Config.SCALE);
		shape.setScaleY(Config.SCALE);
		shape.setFill(road.getColor());
		shape.setMouseTransparent(true);
	}

	@Override
	public Shape getShape() {
		return shape;
	}


}
