package melemed.catan.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import melemed.catan.pieces.GamePiece;
import melemed.catan.pieces.Road;

public abstract class Location {

	private static final Logger logger = LoggerFactory.getLogger(Location.class);

	protected GamePiece occupyingGamePiece;
	
	public GamePiece getOccupyingGamePiece() {
		return occupyingGamePiece;
	}
	
	public abstract void setOccupyingGamePiece(GamePiece piece);

	public boolean isOccupied() {
		return occupyingGamePiece != null;
	}
}
