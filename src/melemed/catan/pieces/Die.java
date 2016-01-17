package melemed.catan.pieces;

import melemed.catan.board.Location;
import melemed.catan.game.ui.DieSkin;
import melemed.catan.ui.BoardShapeWrapper;

public class Die extends GamePiece {

	private int dots;
	private DieSkin skin;
	
	public Die() {
		dots = 6;
		skin = new DieSkin(this);
	}

	@Override
	public BoardShapeWrapper getSkin() {
		return null;
	}

	@Override
	public void build(Location location) {

	}

	public int roll() {
		return (int) Math.floor(6 * Math.random() + 1);
	}

	public int getDots() {
		return dots;
	}

	public void setDots(int dots) {
		this.dots = dots;
	}

}
