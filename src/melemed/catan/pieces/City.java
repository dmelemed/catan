package melemed.catan.pieces;

import melemed.catan.board.Location;
import melemed.catan.board.Vertex;
import melemed.catan.game.Player;
import melemed.catan.ui.BoardShapeWrapper;

public class City extends GamePiece {

	public City(Player player) {
		owner = player;
	}

	@Override
	public void build(Location location) {
		Vertex vertex = (Vertex) location;
	}

	@Override
	public BoardShapeWrapper getSkin() {
		// TODO Auto-generated method stub
		return null;
	}

}
