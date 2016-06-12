package melemed.catan.game.ui;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import melemed.catan.game.Game;
import melemed.catan.game.GameControls;

public class GameControlsSkin extends HBox {

	private final Game game;
	private final GameControls controls;
	private Button buildSettlementButton;
	private Button buildRoad;

	public GameControlsSkin(GameControls gameControls) {
		controls = gameControls;
		game = gameControls.getGame();
		getStyleClass().add("controls");

		Button takeTurn = new Button("Take turn");
		takeTurn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				game.nextTurnTaker();
			}
		});
		buildSettlementButton = new Button("Build settlement");
		buildRoad = new Button("Build road");
		getChildren().addAll(takeTurn, buildSettlementButton, buildRoad);
	}

	public Button getBuildSettlementButton() {
		return buildSettlementButton;
	}

	public Button getBuildRoadButton() {
		return buildRoad;
	}

}
