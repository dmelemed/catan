package melemed.catan.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.property.ReadOnlyObjectWrapper;
import melemed.catan.board.ui.HexSkin;
import melemed.catan.game.Game;
import melemed.catan.pieces.GamePiece;
import melemed.catan.pieces.Robber;

public class Hexagon extends Location{

	private static final Logger logger = LoggerFactory.getLogger(Hexagon.class);

	private final int row;
	private final int pos;
	private final Game game;
	private final HexSkin skin;
	private final Resource resource;
	private final NumberToken numberToken;
	private final List<Vertex> vertices;

	public Hexagon(int row, int pos, Resource resource, NumberToken numberToken, Game game) {
		this.row = row;
		this.pos = pos;
		this.resource = resource;
		this.numberToken = numberToken;
		this.game = game;
		skin = new HexSkin(this);
		vertices = new ArrayList<Vertex>();
	}


	public void pressed() {
		logger.debug("{} hexagon pressed", resource);
	}

	public HexSkin getSkin() {
		return skin;
	}

	public int getPos() {
		return pos;
	}

	public int getRow() {
		return row;
	}

	public Game getGame() {
		return game;
	}


	public Resource getResource() {
		return resource;
	}


	public NumberToken getNumberToken() {
		return numberToken;
	}
	
	public List<Vertex> getVertices() {
		return vertices;
	}
	
	public void addVertices(Vertex... vertex) {
		vertices.addAll(Arrays.asList(vertex));
	}


	@Override
	public void setOccupyingGamePiece(GamePiece piece) {
		if(piece instanceof Robber) {
			occupyingGamePiece = piece;
		}
	}


}
