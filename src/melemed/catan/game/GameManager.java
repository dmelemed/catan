package melemed.catan.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.Scene;
import melemed.catan.board.NumberToken;
import melemed.catan.config.Config;

public class GameManager {

	private static final Logger logger = LoggerFactory.getLogger(GameManager.class);

	private Scene gameScene;
	private Game game;

	public GameManager() {
		newGame();
	}

	public void newGame() {
		logger.info("Initializing new game with " + Config.NUMBER_OF_PLAYERS + " players");
		game = new Game(this);
		gameScene = new Scene(game.getSkin());
	}

	public void quit() {
		gameScene.getWindow().hide();
	}

	public Game getGame() {
		return game;
	}

	public Scene getGameScene() {
		return gameScene;
	}


}
