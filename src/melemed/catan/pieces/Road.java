package melemed.catan.pieces;

import melemed.catan.board.Edge;
import melemed.catan.board.Location;
import melemed.catan.board.Vertex;
import melemed.catan.game.Player;
import melemed.catan.pieces.ui.RoadSkin;
import melemed.catan.ui.BoardShapeWrapper;

public class Road extends GamePiece {

	private Edge edge;
	private RoadSkin skin;

	public Road(Player owner) {
		this.owner = owner;
		skin = new RoadSkin(this);
	}

	public Player getOwner() {
		return owner;
	}

	@Override
	public void build(Location location) {
		Edge edge = (Edge) location;
		skin = new RoadSkin(this);
		skin.getShape().setTranslateX(edge.getSkin().getShape().getTranslateX());
		skin.getShape().setTranslateY(edge.getSkin().getShape().getTranslateY());
		skin.getShape().setRotate(edge.getSkin().getShape().getRotate());
		edge.getGame().getBoard().putPiece(this);
		edge.setOccupyingGamePiece(this);
	}

	@Override
	public BoardShapeWrapper getSkin() {
		return skin;
	}

	public Vertex getVertexTo() {
		return edge.getVertexTo();
	}

	public Vertex getVertexFrom() {
		return edge.getVertexFrom();
	}

	public Edge getEdge() {
		return edge;
	}

}
