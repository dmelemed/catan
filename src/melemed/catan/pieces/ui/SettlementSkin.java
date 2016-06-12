package melemed.catan.pieces.ui;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import melemed.catan.config.Config;
import melemed.catan.pieces.Settlement;
import melemed.catan.ui.BoardShapeWrapper;

public class SettlementSkin implements BoardShapeWrapper {

	private Shape shape;

	public SettlementSkin(Settlement settlement) {
		shape = new Rectangle(0.4, 0.25);
		shape.setScaleX(Config.SCALE);
		shape.setScaleY(Config.SCALE);
		settlement.getOwner().getTeam();
		shape.setMouseTransparent(true);
		shape.setFill(settlement.getColor()); // TO FIX
	}

	public Shape getShape() {
		return shape;
	}


}
