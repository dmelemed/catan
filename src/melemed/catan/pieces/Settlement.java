package melemed.catan.pieces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.shape.Shape;
import melemed.catan.board.Location;
import melemed.catan.board.Vertex;
import melemed.catan.game.Game;
import melemed.catan.game.Player;
import melemed.catan.pieces.ui.SettlementSkin;
import melemed.catan.ui.BoardShapeWrapper;

public class Settlement extends GamePiece {

	private static final Logger logger = LoggerFactory.getLogger(Settlement.class);

	private SettlementSkin skin;
	private Vertex vertex;

	public Settlement(Player player) {
		owner = player;
		game = player.getGame();
		skin = new SettlementSkin(this);
	}

	@Override
	public void build(Location location) {
		vertex = (Vertex) location;
		logger.debug("Building a settlement at [{}, {}]", vertex.getRow(), vertex.getPos());
		Shape vertexShape = vertex.getSkin().getShape();
		double translateX = vertexShape.getTranslateX(); // half width
		double translateY = vertexShape.getTranslateY(); // half height of settlement
		skin.getShape().setTranslateX(translateX);
		skin.getShape().setTranslateY(translateY);
		vertex.getGame().getBoard().putPiece(this);
		vertex.setOccupyingGamePiece(this);
		
		if(game.isSetup()) {
			game.getControls().disableBuildSettlementButton();
		}
	}

	@Override
	public SettlementSkin getSkin() {
		return skin;
	}

	public Vertex getVertex() {
		return vertex;
	}

}