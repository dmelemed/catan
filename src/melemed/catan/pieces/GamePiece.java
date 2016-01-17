package melemed.catan.pieces;

import javafx.scene.paint.Color;
import melemed.catan.board.Location;
import melemed.catan.game.Game;
import melemed.catan.game.Player;
import melemed.catan.ui.BoardShapeWrapper;

public abstract class GamePiece {
	protected Player owner;
	protected Game game;

	public abstract BoardShapeWrapper getSkin();

	public abstract void build(Location location);

	public Player getOwner() {
		return owner;
	}

	public boolean isOwner(Player player) {
		return owner == player;
	}

	public static boolean isOwner(Player player, GamePiece piece) {
		return piece != null && piece.isOwner(player);
	}

	public Color getColor() {

		return owner.getColor();
	}

	public Game getGame() {
		return game;
	}

}
