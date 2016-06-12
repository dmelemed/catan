package melemed.catan.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import melemed.catan.game.ui.GameControlsSkin;
import melemed.catan.pieces.Road;
import melemed.catan.pieces.Settlement;

public class GameControls {

	private static final Logger logger = LoggerFactory.getLogger(GameControls.class);

	private final Game game;
	private final GameControlsSkin skin;
	
	public final EventHandler<MouseEvent> buildSettlementHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent mouseEvent) {
			Settlement settlement = game.getCurrentTurnTaker().getNextSettlement();
			if (settlement != null) {
				Button buildSettlement = skin.getBuildSettlementButton();
				buildSettlement.setText("Cancel");
				game.holdGamePiece(settlement);
				buildSettlement.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
				buildSettlement.addEventHandler(MouseEvent.MOUSE_CLICKED, cancelSettlementHandler);
			} else {
				logger.info("No settlements remaining");
			}
		}
	};

	public final EventHandler<MouseEvent> cancelSettlementHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			Button buildSettlement = skin.getBuildSettlementButton();
			buildSettlement.setText("Build settlement");
			logger.debug("Removing game piece");
			Settlement settlement = game.getCurrentTurnTaker().getNextSettlement();
			game.removeGamePiece(settlement);
			buildSettlement.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
			buildSettlement.addEventHandler(MouseEvent.MOUSE_CLICKED, buildSettlementHandler);
		}
	};

	public final EventHandler<MouseEvent> buildRoadHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent mouseEvent) {
			logger.debug("Building road");
			Road road = game.getCurrentTurnTaker().getNextRoad();
			if (road != null) {
				Button buildRoad = skin.getBuildRoadButton();
				buildRoad.setText("Cancel");
				game.holdGamePiece(road);
				buildRoad.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
				buildRoad.addEventHandler(MouseEvent.MOUSE_CLICKED, cancelRoadHandler);
			} else {
				logger.info("No roads remaining");
			}
		}
	};

	public final EventHandler<MouseEvent> cancelRoadHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			logger.debug("Cancel building road");
			Button buildRoad = skin.getBuildRoadButton();
			buildRoad.setText("Build road");
			Road road = game.getCurrentTurnTaker().getNextRoad();
			game.removeGamePiece(road);
			buildRoad.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
			buildRoad.addEventHandler(MouseEvent.MOUSE_CLICKED, buildRoadHandler);
		}
	};

	public GameControls(Game game) {
		this.game = game;
		skin = new GameControlsSkin(this);
		enableBuildSettlementButton();
		enableBuildRoadButton();
	}
	
	public void enableBuildSettlementButton() {
		Button buildSettlementButton = skin.getBuildSettlementButton();
		buildSettlementButton.addEventHandler(MouseEvent.MOUSE_CLICKED, buildSettlementHandler);
		buildSettlementButton.setTextFill(Color.BLACK);

	}
	
	public void disableBuildSettlementButton() {
		Button buildSettlementButton = skin.getBuildSettlementButton();
		buildSettlementButton.removeEventHandler(MouseEvent.MOUSE_CLICKED, buildSettlementHandler);
		buildSettlementButton.setTextFill(Color.GREY);
	}
	
	public void enableBuildRoadButton() {
		Button buildRoadButton = skin.getBuildRoadButton();
		buildRoadButton.addEventHandler(MouseEvent.MOUSE_CLICKED, buildRoadHandler);
		buildRoadButton.setTextFill(Color.BLACK);
	}
	
	public void disableBuildRoadButton() {
		Button buildRoadButton = skin.getBuildRoadButton();
		buildRoadButton.removeEventHandler(MouseEvent.MOUSE_CLICKED, buildRoadHandler);
		buildRoadButton.setTextFill(Color.GREY);
	}

	public Game getGame() {
		return game;
	}

	public GameControlsSkin getSkin() {
		return skin;
	}
	
	public void settlementPlaced() {
		Button buildSettlement = skin.getBuildSettlementButton();
		buildSettlement.setText("Build settlement");
		buildSettlement.addEventHandler(MouseEvent.MOUSE_CLICKED, buildSettlementHandler);
		buildSettlement.removeEventHandler(MouseEvent.MOUSE_CLICKED, cancelSettlementHandler);
	}

	public void roadPlaced() {
		Button buildRoad = skin.getBuildRoadButton();
		buildRoad.setText("Build road");
		buildRoad.addEventHandler(MouseEvent.MOUSE_CLICKED, buildRoadHandler);
		buildRoad.removeEventHandler(MouseEvent.MOUSE_CLICKED, cancelRoadHandler);
	}

	public void enableAllButtons() {
		enableBuildRoadButton();
		enableBuildSettlementButton();
	}


}
