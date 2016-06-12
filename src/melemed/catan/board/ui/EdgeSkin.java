package melemed.catan.board.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import melemed.catan.board.Edge;
import melemed.catan.config.Config;
import melemed.catan.ui.BoardShapeWrapper;

public class EdgeSkin implements BoardShapeWrapper {

	private static final Logger logger = LoggerFactory.getLogger(EdgeSkin.class);

	private Shape shape;
	private Edge edge;

	public EdgeSkin(Edge edge) {
		this.edge = edge;
		shape = new Rectangle(0.6, 0.1);
		shape.setScaleX(Config.SCALE);
		shape.setScaleY(Config.SCALE);
		shape.setFill(Config.EDGE_SKIN_COLOR);
		
		shape.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				logger.debug("Vertex pressed at [{}, {}]", shape.getTranslateX(), shape.getTranslateY());
				edge.pressed();
			}
		});

		shape.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				shape.setFill(Config.EDGE_HOVER_COLOR);
			}
		});
		
		shape.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				shape.setFill(Config.EDGE_SKIN_COLOR);
			}
		});
	}

	@Override
	public Shape getShape() {
		return shape;
	}

}
