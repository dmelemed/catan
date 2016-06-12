package melemed.catan.board.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import melemed.catan.board.Board;
import melemed.catan.board.Edge;
import melemed.catan.board.Hexagon;
import melemed.catan.board.Vertex;
import melemed.catan.config.Config;
import melemed.catan.pieces.GamePiece;

public class BoardSkin extends Pane {

	private static final Logger logger = LoggerFactory.getLogger(BoardSkin.class);

	private static double HALF_SQRT_THREE = Math.sqrt(3) / 2;

	private Shape mouseFollowShape;
	private EventHandler<MouseEvent> mouseFollowHandler;
	private Board board;

	public BoardSkin(Board board) {
		logger.info("Setting up board...");
		setPrefSize(1000, 900);
		this.board = board;
		getStyleClass().add("board");

		addHexes();
		positionVerticesAroundHexes();
		addVertices();
		alignEdgesToVertices();
		addEdges();

		// Road road = new Road(new Player(board.getHexagon(0).getGame(),
		// Team.BLUE, 1));
		// getChildren().add(road.getSkin().getShape());
	}

	private void alignEdgesToVertices() {
		for (Edge edge : board.getEdges()) {
			double fromX = edge.getVertexFrom().getSkin().getShape().getTranslateX();
			double fromY = edge.getVertexFrom().getSkin().getShape().getTranslateY();
			double toX = edge.getVertexTo().getSkin().getShape().getTranslateX();
			double toY = edge.getVertexTo().getSkin().getShape().getTranslateY();
			edge.getSkin().getShape().setTranslateX((fromX + toX) / 2);
			edge.getSkin().getShape().setTranslateY((fromY + toY) / 2);
			if ((fromX - toX) < -0.01 * Config.SCALE) { // left to right
				if (fromY > toY) { // going up
					edge.setRotation(-30);
				} else if (fromY < toY) { // going down
					edge.setRotation(30);
				}
			} else { // vertical
				edge.setRotation(90);
			}

		}
	}

	private void addEdges() {
		for (Edge edge : board.getEdges()) {
			getChildren().add(edge.getSkin().getShape());
		}
	}

	private void addVertices() {
		for (Vertex vertex : board.getVertices()) {
			getChildren().add(vertex.getSkin().getShape());
		}
	}

	private void positionVerticesAroundHexes() {
		for (Hexagon hex : board.getHexes()) {
			Shape hexagon = hex.getSkin().getShape();
			for (int i = 0; i < 6; i++) {
				Vertex vertex = hex.getVertices().get(i);
				double offsetX = 0;
				double offsetY = 0;
				switch (i) {
				case 0:
					offsetX = -HALF_SQRT_THREE;
					offsetY = -0.5;
					break;
				case 1:
					offsetX = 0d;
					offsetY = -1d;
					break;
				case 2:
					offsetX = HALF_SQRT_THREE;
					offsetY = -0.5;
					break;
				case 3:
					offsetX = -HALF_SQRT_THREE;
					offsetY = 0.5;
					break;
				case 4:
					offsetX = 0d;
					offsetY = 1d;
					break;
				case 5:
					offsetX = HALF_SQRT_THREE;
					offsetY = 0.5;
					break;
				default:
					break;
				}
				vertex.getSkin().getShape().setTranslateX(hexagon.getTranslateX() + (offsetX * Config.SCALE));
				vertex.getSkin().getShape().setTranslateY(hexagon.getTranslateY() + (offsetY * Config.SCALE));
			}
		}
	}

	private void addHexes() {
		for (Hexagon hex : board.getHexes()) {
			getChildren().add(hex.getSkin().getShape());
			getChildren().add((new NumberTokenSkin(hex)).getShape());
		}
	}

	public void addElements(Node... nodes) {
		getChildren().addAll(nodes);
	}

	public void followMouse(GamePiece piece) {
		logger.debug("Piece started ollowing mouse");
		mouseFollowShape = piece.getSkin().getShape();
		mouseFollowHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				mouseFollowShape.setTranslateX(mouseEvent.getX());
				mouseFollowShape.setTranslateY(mouseEvent.getY());
			}
		};
		addEventHandler(MouseEvent.MOUSE_MOVED, mouseFollowHandler);
	}

	public void unfollowMouse() {
		logger.debug("Piece unfollowing mouse");
		removeEventHandler(MouseEvent.MOUSE_MOVED, mouseFollowHandler);
		mouseFollowHandler = null;
		mouseFollowShape = null;
	}

}
