package melemed.catan.board.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import melemed.catan.board.Vertex;
import melemed.catan.config.Config;
import melemed.catan.ui.BoardShapeWrapper;

public class VertexSkin implements BoardShapeWrapper {
	
	private static final Logger logger = LoggerFactory.getLogger(VertexSkin.class);

	private Circle shape;

	public VertexSkin(Vertex vertex) {

		shape = new Circle(0.2 * Config.SCALE);
		shape.setFill(Config.VERTEX_SKIN_COLOR);

		shape.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				vertex.pressed();
			}
		});

		shape.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				shape.setFill(Config.VERTEX_HOVER_COLOR);
			}
		});
		
		shape.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				shape.setFill(Config.VERTEX_SKIN_COLOR);
			}
		});
	}

	@Override
	public Shape getShape() {
		return shape;
	}

}
