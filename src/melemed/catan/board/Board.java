package melemed.catan.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import melemed.catan.board.ui.BoardSkin;
import melemed.catan.game.Game;
import melemed.catan.pieces.GamePiece;

public class Board {

	private static final Logger logger = LoggerFactory.getLogger(Board.class);
	
	private final BoardSkin skin;
	public static final int[] hexagonsPerRow = new int[] { 3, 4, 5, 4, 3 };
	public static final int[] verticesPerRow = new int[] { 7, 9, 11, 11, 9, 7 };

	private List<Hexagon> boardHexes;
	private List<Vertex> vertices;
	private List<Edge> edges;

	private List<Resource> gameHexResources;
	private List<NumberToken> tokens;
	private Game game;

	public Board(Game game) {
		logger.info("Loading board and pieces");
		this.game = game;
		initializeHexResources();
		initializeNumberTokens();
		addHexesToBoard();
		addVertices();
		addVerticesToHexes();
		addVerticesToEdges();
		addEdgesToVertices();
		skin = new BoardSkin(this);
	}

	private void addEdgesToVertices() {
		for(Edge edge : edges) {
			edge.getVertexFrom().addEdge(edge);
			edge.getVertexTo().addEdge(edge);
		}
	}

	private void addVerticesToHexes() {
		// Add vertices to hexagons
		int pos = 0;
		int topOffset = 0;
		int bottomOffset = 0;
		for (int i = 0; i < 5; i++) {
			bottomOffset += verticesPerRow[i];
			for (int j = 0; j < hexagonsPerRow[i]; j++) {
				getHexagon(pos).addVertices(getVertex(2 * j + topOffset), getVertex(2 * j + 1 + topOffset),
						getVertex(2 * j + 2 + topOffset));

				getHexagon(pos).addVertices(getVertex(2 * j + 1 + bottomOffset), getVertex(2 * j + 2 + bottomOffset),
						getVertex(2 * j + 3 + bottomOffset));
				pos++;
			}
			topOffset += verticesPerRow[i];
			if (i == 2) {
				topOffset++;
			}
			if (i == 1) {
				bottomOffset--;
			}
		}
	}

	private void addVertices() {
		vertices = new ArrayList<Vertex>();
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < verticesPerRow[i]; j++) {
				vertices.add(new Vertex(i, j, game));
			}
		}
	}

	private void addVerticesToEdges() {
		// make connections and edges
		edges = new ArrayList<Edge>();
		Vertex to;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < verticesPerRow[i]; j++) {
				Vertex from = getVertex(i, j);

				if (j < verticesPerRow[i] - 1) { // not last in row
	
					to = getVertex(i, j + 1);
					edges.add(new Edge(from, to, game));
					from.addConnectedVertex(to);
				}
				if (j > 0) { // not first in row
					to = getVertex(i, j - 1);
					from.addConnectedVertex(to);
				}
				if (i < 5) { // not last row
					int offset = (verticesPerRow[i + 1] - verticesPerRow[i]) / 2;
					if ((j % 2 == 0 && i < 3) || (j % 2 == 1 && i >= 3)) { // vertical
						to = getVertex(i + 1, j + offset);
						edges.add(new Edge(from, to, game));
						from.addConnectedVertex(to);
						to.addConnectedVertex(from);
					}
				}
			}
		}
	}

	private void addHexesToBoard() {
		boardHexes = new ArrayList<Hexagon>();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < hexagonsPerRow[i]; j++) {
				Resource resource = getRandomGameHexResource();
				NumberToken token = null;
				if (resource == Resource.DESERT) {
					token = NumberToken.SEVEN;
					boardHexes.add(new Hexagon(i, j, resource, NumberToken.SEVEN, game));
				} else {
					token = getRandomNumberToken();
					boardHexes.add(new Hexagon(i, j, resource, token, game));
				}
			}
		}
	}

	private void initializeNumberTokens() {
		tokens = new ArrayList<NumberToken>();
		tokens.addAll(Arrays.asList(new NumberToken[] { NumberToken.TWO, NumberToken.THREE, NumberToken.THREE,
				NumberToken.FOUR, NumberToken.FOUR, NumberToken.FIVE, NumberToken.FIVE, NumberToken.SIX,
				NumberToken.SIX, NumberToken.EIGHT, NumberToken.EIGHT, NumberToken.NINE, NumberToken.NINE,
				NumberToken.TEN, NumberToken.TEN, NumberToken.ELEVEN, NumberToken.ELEVEN, NumberToken.TWELVE }));
		Collections.shuffle(tokens);
	}

	private NumberToken getRandomNumberToken() {
		NumberToken token = tokens.get(0);
		tokens.remove(0);
		return token;
	}

	private void initializeHexResources() {
		gameHexResources = new ArrayList<Resource>();
		for (int i = 0; i < 3; i++) {
			gameHexResources.add(Resource.BRICK);
		}
		gameHexResources.add(Resource.DESERT);
		for (int i = 0; i < 3; i++) {
			gameHexResources.add(Resource.ORE);
		}
		for (int i = 0; i < 4; i++) {
			gameHexResources.add(Resource.SHEEP);
		}
		for (int i = 0; i < 4; i++) {
			gameHexResources.add(Resource.WHEAT);
		}
		for (int i = 0; i < 4; i++) {
			gameHexResources.add(Resource.WOOD);
		}
		Collections.shuffle(gameHexResources);
	}

	private Resource getRandomGameHexResource() {
		Resource resource = gameHexResources.get(0);
		gameHexResources.remove(0);
		return resource;
	}

	public Hexagon getHexagon(int row, int pos) {
		if (row >= 5 || pos >= hexagonsPerRow[row]) {
			return null;
		}
		int position = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < hexagonsPerRow[i]; j++) {
				if (i == row && j == pos) {
					return boardHexes.get(position);
				}
				position++;
			}
		}
		return null;
	}

	public Hexagon getHexagon(int pos) {
		if (pos < 19) {
			return boardHexes.get(pos);
		}
		return null;
	}

	public BoardSkin getSkin() {
		return skin;
	}

	public Vertex getVertex(int pos) {
		return vertices.get(pos);
	}

	public Vertex getVertex(int row, int pos) {
		int position = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < verticesPerRow[i]; j++) {
				if (i == row && j == pos) {
					// System.out.println("Position: " + position);
					return vertices.get(position);
				}
				position++;
			}
		}
		return null;
	}

	public List<Hexagon> getHexes() {
		return boardHexes;
	}

	public List<Vertex> getVertices() {
		return vertices;
	}

	public void putPiece(GamePiece piece) {
		logger.debug("Adding {} piece to board", piece);
		skin.addElements(piece.getSkin().getShape());
	}

	public void removePiece(GamePiece piece) {
		skin.getChildren().remove(piece.getSkin().getShape());
	}

	public void followMouse(GamePiece piece) {
		skin.followMouse(piece);
	}

	public void unfollowMouse() {
		skin.unfollowMouse();
	}

	public List<Edge> getEdges() {
		return edges;
	}

}
