package melemed.catan.cards.ui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import melemed.catan.board.Resource;
import melemed.catan.config.Config;
import melemed.catan.ui.BoardShapeWrapper;

public class ResourceCardSkin implements BoardShapeWrapper {

	private Rectangle shape;

	public ResourceCardSkin(Resource resource) {
		shape = new Rectangle(0.7 * Config.SCALE, 1 * Config.SCALE);
		shape.setArcWidth(0.2 * Config.SCALE);
		shape.setArcHeight(0.2 * Config.SCALE);
		shape.setStroke(Color.WHITE);
		shape.setFill(Color.RED);
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return shape;
	}

}
