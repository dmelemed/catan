package melemed.catan.board.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import melemed.catan.board.Hexagon;
import melemed.catan.board.NumberToken;
import melemed.catan.config.Config;
import melemed.catan.ui.BoardShapeWrapper;

public class NumberTokenSkin extends BoardShapeWrapper {
	
	private static final Logger logger = LoggerFactory.getLogger(NumberTokenSkin.class);


	private Shape shape;
	private NumberToken token;
	
	public NumberTokenSkin(Hexagon hex) {
		shape = new Circle(0.4 * Config.SCALE);
		token = hex.getNumberToken();
		Image image = new Image(token.getImgUrl());
		ImagePattern imagePattern = new ImagePattern(image);
		shape.setFill(imagePattern);
		Shape hexShape = hex.getSkin().getShape();
		shape.setTranslateX(hexShape.getTranslateX());
		shape.setTranslateY(hexShape.getTranslateY());
		shape.setMouseTransparent(true);

	}
	
	
	
	@Override
	public Shape getShape() {
		return shape;
	}

	
}
