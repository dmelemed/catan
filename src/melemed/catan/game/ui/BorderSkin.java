package melemed.catan.game.ui;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import melemed.catan.board.Resource;
import melemed.catan.cards.Hand;
import melemed.catan.cards.ui.ResourceCardSkin;
import melemed.catan.config.Config;
import melemed.catan.game.Player;

public class BorderSkin extends StackPane {

	public BorderSkin(Player player) {
		getStyleClass().add("hands");
		setPrefWidth(Config.BORDER_WIDTH);
		setPrefHeight(Config.BORDER_WIDTH);
		Hand hand = player.getHand();
		setAlignment(Pos.CENTER);
		for (Resource resource : Resource.values()) {
			List<Resource> resources = hand.getAll(resource);
			addCard(resources.toArray(new Resource[resources.size()]));
		}
	}

	private void addCard(Resource... resources) {
		for (int i = 0; i < resources.length; i++) {
			Resource resource = resources[i];
			ResourceCardSkin skin = new ResourceCardSkin(resource);
			Text text = new Text(10, 50, resource.toString());
			text.setTranslateX(getTranslateX() + 10 * i + getOffset(resource));
			text.setTranslateY(getTranslateY() + 10 * i);

			skin.getShape().setTranslateX(getTranslateX() + 10 * i + getOffset(resource));
			skin.getShape().setTranslateY(getTranslateY() + 15* i);
			getChildren().addAll(skin.getShape(), text);
		}
	}

	private double getOffset(Resource resource) {
		switch (resource) {
		case BRICK:
			return -2 * Config.HAND_SPACING;
		case ORE:
			return -1 * Config.HAND_SPACING;
		case SHEEP:
			return 0 * Config.HAND_SPACING;
		case WHEAT:
			return 1 * Config.HAND_SPACING;
		case WOOD:
			return 2 * Config.HAND_SPACING;
		default:
			return 0 * Config.HAND_SPACING;
		}
	}

	public void centerChildren() {
		for (Node node : getChildren()) {
			System.out.println("GET WIDTH: " + getPrefWidth());
			node.setTranslateX(400d);
		}
	}

}
