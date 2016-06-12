package melemed.catan.game.ui;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import melemed.catan.board.Resource;
import melemed.catan.game.Game;
import melemed.catan.game.GameManager;
import melemed.catan.game.Player;

public class GameSkinWrapper extends BorderPane {
	
	public GameSkin gameSkin;
	
	public GameSkinWrapper(GameManager gameManager, Game game) {
		setPrefSize(1080, 900);
		getStyleClass().add("board");
		gameSkin = new GameSkin(game);
		Node boardSkin = game.getBoard().getSkin();
		Node controlsSkin = game.getControls().getSkin();
		// setBottomAnchor(controlsSkin, 0d);
		// getChildren().addAll(boardSkin, new HandSkin(true), controlsSkin);
		setCenter(gameSkin);
		setBottom(controlsSkin);
	}

	public class GameSkin extends BorderPane {
		
		public GameSkin(Game game) {
			Node boardSkin = game.getBoard().getSkin();
			setCenter(boardSkin);
			List<Player> players = game.getPlayers();
//			for(int i = 0; i < players.size(); i++) {
//				
//			}
			players.get(0).takeResource(Resource.WHEAT, Resource.BRICK, Resource.SHEEP, Resource.BRICK);
			setLeft(new BorderSkin(players.get(1)));
//			setRight(new BorderSkin(players.get(3)));
			setBottom(new BorderSkin(players.get(0)));
//			setTop(new BorderSkin(players.get(2)));
		}
	}
	
	public void align() {
//		gameSkin.bottom.centerChildren();
	}
}
