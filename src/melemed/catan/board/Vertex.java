package melemed.catan.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import melemed.catan.board.ui.VertexSkin;
import melemed.catan.game.Game;
import melemed.catan.game.Player;
import melemed.catan.pieces.City;
import melemed.catan.pieces.GamePiece;
import melemed.catan.pieces.Settlement;

public class Vertex extends Location {

	private static final Logger logger = LoggerFactory.getLogger(Vertex.class);

	private final int row;
	private final int pos;
	private final Game game;
	private VertexSkin skin;
	private List<Vertex> connectedVertices;
	private List<Edge> edges;

	public Vertex(int row, int pos, Game game) {
		this.row = row;
		this.pos = pos;
		this.game = game;
		skin = new VertexSkin(this);
		connectedVertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
	}

	public int getPos() {
		return pos;
	}

	public int getRow() {
		return row;
	}

	public void pressed() {
		logger.debug("Vertex pressed [{}, {}]", row, pos);
		if (game.getHeldPiece() instanceof Settlement) { // if settlement
			game.removeHeldGamePiece();
			game.settlementPlaced();
			Player player = game.getCurrentTurnTaker();
			GamePiece built = player.buildSettlement(this);
			if (built != null) {
				occupyingGamePiece = built;
				if(game.isSetup()) {
					game.getControls().disableBuildSettlementButton();
				}
			}
		} else if (game.getHeldPiece() instanceof City) {
			// Check conditions
		} else {
			logger.info("No valid piece to place on vertex");
		}
	}

	public VertexSkin getSkin() {
		return skin;
	}

	public Game getGame() {
		return game;
	}

	public GamePiece getOccupyingGamePiece() {
		return occupyingGamePiece;
	}
	
	@Override
	public void setOccupyingGamePiece(GamePiece piece) {
		if(piece instanceof Settlement || piece instanceof City) {
			this.occupyingGamePiece = piece;
		}
	}
	
	public List<Vertex> getConnectedVertices() {
		return connectedVertices;
	}
	
	public void addConnectedVertex(Vertex vertex) {
		connectedVertices.add(vertex);
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void addEdge(Edge... edges) {
		this.edges.addAll(Arrays.asList(edges));
	}
}
