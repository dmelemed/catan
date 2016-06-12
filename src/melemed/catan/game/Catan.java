package melemed.catan.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import melemed.catan.config.Config;

public class Catan extends Application {
	
	private static final Logger logger = LoggerFactory.getLogger(Catan.class);

	@Override
	public void start(Stage stage) throws Exception {
		logger.info("Booting up Catan...");
		GameManager gameManager = new GameManager();
		Scene scene = gameManager.getGameScene();
		scene.getStylesheets().add(getResource("/catan-skin.css"));
		stage.setTitle("Settlers of Catan");
		stage.setScene(scene);
		stage.show();
		stage.setMaximized(Config.DEFAULT_MAXIMIZED);
//		gameManager.getGame().getSkin().gameSkin.bottom.centerChildren();
//		stage.setFullScreen(true);
	}

	public static void main(String[] args) {
		Application.launch(Catan.class);
	}
	
	private String getResource(String resourceName) {
		return getClass().getResource(resourceName).toExternalForm();
	}

}
