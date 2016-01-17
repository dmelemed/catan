package melemed.catan.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import melemed.catan.board.ui.EdgeSkin;
import melemed.catan.game.Game;
import melemed.catan.game.Player;
import melemed.catan.pieces.GamePiece;
import melemed.catan.pieces.Road;

public class Edge extends Location {

	private static final Logger logger = LoggerFactory.getLogger(Edge.class);

	private final Vertex vertexFrom;
	private final Vertex vertexTo;
	private final Game game;
	private EdgeSkin skin;

	public Edge(Vertex vertexFrom, Vertex vertexTo, Game game) {
		this.vertexFrom = vertexFrom;
		this.vertexTo = vertexTo;
		this.game = game;
		skin = new EdgeSkin(this);
	}

	public Vertex getVertexFrom() {
		return vertexFrom;
	}

	public Vertex getVertexTo() {
		return vertexTo;
	}

	public Game getGame() {
		return game;
	}

	public EdgeSkin getSkin() {
		return skin;
	}

	@Override
	public void setOccupyingGamePiece(GamePiece piece) {
		if (piece instanceof Road) {
			occupyingGamePiece = piece;
		}
	}

	public void setRotation(double degrees) {
		getSkin().getShape().setRotate(degrees);
	}

	public void pressed() {
		logger.debug("Edge pressed [{}, {}] to [{}, {}]", vertexFrom.getRow(), vertexFrom.getPos(), vertexTo.getPos(),
				vertexTo.getPos());
		if (game.getHeldPiece() instanceof Road) { // if settlement
			game.removeHeldGamePiece();
			game.roadPlaced();
			Player player = game.getCurrentTurnTaker();
			GamePiece built = player.buildRoad(this);
			if (built != null) {
				occupyingGamePiece = built;
				game.getControls().disableBuildRoadButton();
			}
		} else {
			logger.info("No valid piece to place on vertex");
		}
	}

}
