package melemed.catan.pieces;

import javafx.scene.shape.Shape;
import melemed.catan.board.Hexagon;
import melemed.catan.board.Location;
import melemed.catan.ui.BoardShapeWrapper;

public class Robber extends GamePiece {

	private Hexagon hexagon;
	private Shape robber;
	
	public Robber(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	
	@Override
	public BoardShapeWrapper getSkin() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void build(Location location) {
		Hexagon hexagon = (Hexagon) location;
		this.hexagon = hexagon;
		// add shape
		
	}
	
}
