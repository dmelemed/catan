package melemed.catan.game;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.paint.Color;
import melemed.catan.board.Edge;
import melemed.catan.board.Resource;
import melemed.catan.board.Vertex;
import melemed.catan.cards.DevelopmentCard;
import melemed.catan.cards.Hand;
import melemed.catan.cards.ResourceCard;
import melemed.catan.pieces.City;
import melemed.catan.pieces.GamePiece;
import melemed.catan.pieces.Road;
import melemed.catan.pieces.Settlement;

public class Player {

	private static final Logger logger = LoggerFactory.getLogger(Player.class);

	private final int playerId;
	private final Team team;
	private final Game game;
	private List<Road> roads;
	private List<Settlement> settlements;
	private List<City> cities;
	private List<ResourceCard> resourcesInHand;
	private List<DevelopmentCard> devCardsInHand;
	private int points;
	private Hand hand;
	private boolean hasLongestRoad;
	private boolean hasLargestArmy;

	public Player(Game game, Team team, int playerId) {
		logger.trace("Creating player {} for team {}", playerId, team);
		this.game = game;
		this.team = team;
		this.playerId = playerId;
		roads = new ArrayList<>();
		settlements = new ArrayList<>();
		cities = new ArrayList<>();
		resourcesInHand = new ArrayList<>();
		devCardsInHand = new ArrayList<>();
		points = 0;
		hasLongestRoad = false;
		hasLargestArmy = false;
		initializePieces();
		hand = new Hand();
	}

	public void initializePieces() {
		for (int i = 0; i < 15; i++) {
			roads.add(new Road(this));
		}
		for (int i = 0; i < 5; i++) {
			settlements.add(new Settlement(this));
		}
		for (int i = 0; i < 4; i++) {
			cities.add(new City(this));
		}
	}

	public Road buildRoad(Edge edge) {
		logger.debug("Player {} attempting to build on [{}, {}] to [{}, {}]", getPlayerId(),
				edge.getVertexFrom().getRow(), edge.getVertexFrom().getPos(), edge.getVertexTo().getPos(),
				edge.getVertexTo().getPos());
		Road road = null;
		if (!roads.isEmpty()) {
			if (canBuildRoad(edge)) {
				road = roads.get(0);
				road.build(edge);
				roads.remove(0);
				game.checkForLongestRoad();
				if (game.isSetup()) {
					game.turnIsComplete();
				}
				return road;

			}
			return null;
		} else {
			logger.info("No settlements left");
			return null;
		}

	}

	private boolean canBuildRoad(Edge edge) {
		if (edge.isOccupied()) {
			return false;
		}
		GamePiece fromPiece = edge.getVertexFrom().getOccupyingGamePiece();
		GamePiece toPiece = edge.getVertexTo().getOccupyingGamePiece();
		boolean fromPieceIsOwned = GamePiece.isOwner(this, fromPiece);
		boolean toPieceIsOwned = GamePiece.isOwner(this, toPiece);
		if (fromPieceIsOwned || toPieceIsOwned) {
			return true;
		}

		for (Edge fromVertexEdge : edge.getVertexFrom().getEdges()) {
			if (GamePiece.isOwner(this, fromVertexEdge.getOccupyingGamePiece())) {
				return true;
			}
		}
		for (Edge fromVertexEdge : edge.getVertexTo().getEdges()) {
			if (GamePiece.isOwner(this, fromVertexEdge.getOccupyingGamePiece())) {
				return true;
			}
		}
		return false;

	}

	public Settlement buildSettlement(Vertex vertex) {
		logger.debug("Player {} attempting to build settlement on [{}, {}]", getPlayerId(), vertex.getRow(),
				vertex.getPos());
		Settlement settlement = null;
		if (!settlements.isEmpty()) {
			if (canBuildSettlement(vertex) && (game.isSetup() || isVertexConnected(vertex))) {
				settlement = settlements.get(0);
				settlement.build(vertex);
				settlements.remove(0);
				points++;
				return settlement;
			} else {
				logger.info("Cannot build settlement");
				return null;
			}
		} else {
			logger.info("No settlements left");
			return null;
		}

	}

	public boolean canBuildSettlement(Vertex vertex) {
		if (vertex.getOccupyingGamePiece() != null) {
			logger.info("Vertex already occupied");
			return false;
		}
		for (Vertex connected : vertex.getConnectedVertices()) {
			if (connected.isOccupied()) {
				logger.info("Cannot build within one space of another city or settlement");
				return false;
			}
		}
		return true;
	}

	public boolean isVertexConnected(Vertex vertex) {
		for (Edge edge : vertex.getEdges()) {
			if (GamePiece.isOwner(this, edge.getOccupyingGamePiece())) {
				return true;
			}
		}
		return false;
	}

	public Settlement getNextSettlement() {
		if (!settlements.isEmpty()) {
			return settlements.get(0);
		}
		return null;
	}

	public void upgradeToCity(Settlement settlement) {
		points++;
	}

	public int getPoints() {
		return points;
	}

	public int getPlayerId() {
		return playerId;
	}

	public Team getTeam() {
		return team;
	}

	public Road getNextRoad() {
		if (!roads.isEmpty()) {
			return roads.get(0);
		}
		return null;
	}

	public Color getColor() {
		return team.getColor();
	}

	public Game getGame() {
		return game;
	}

	public Hand getHand() {
		return hand;
	}

	public void takeResource(Resource... resources) {
		for (Resource resource : resources) {
			hand.addCard(resource);
		}
	}

}
