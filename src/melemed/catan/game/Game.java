package melemed.catan.game;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import melemed.catan.board.Board;
import melemed.catan.config.Config;
import melemed.catan.game.ui.GameSkinWrapper;
import melemed.catan.pieces.GamePiece;

public class Game {
	
	private static final Logger logger = LoggerFactory.getLogger(Game.class);

	private GameSkinWrapper skin;
	private Board board;
	private List<Player> players;
	public List<Player> getPlayers() {
		return players;
	}

	private Player currentTurnTaker;
	private GameControls controls;
	private GamePiece heldPiece;
	private int round;
	private boolean turnIsComplete;
	
	public Game(GameManager gameManager) {
		round = 0;
		createPlayers();
		board = new Board(this);
		controls = new GameControls(this);
		skin = new GameSkinWrapper(gameManager, this);
		currentTurnTaker = players.get(0);
		turnIsComplete = false;
	}

	public Player getCurrentTurnTaker() {
		return currentTurnTaker;
	}

	private boolean isGameOver() {
		for (Player player : players) {
			if (player.getPoints() >= 10) {
				return true;
			}
		}
		return false;
	}

	public void checkForLargestArmy() {
	}

	public void checkForLongestRoad() {

	}

	private void createPlayers() {
		players = new ArrayList<>();
		if (Config.NUMBER_OF_PLAYERS <= 4) {
			for (int i = 0; i < Config.NUMBER_OF_PLAYERS; i++) {
				players.add(new Player(this, Team.values()[i], i));
			}
		}
	}

	public void nextTurnTaker() {
		if(!isSetup() || turnIsComplete) {
			currentTurnTaker = getNextTurnTaker();
			controls.enableAllButtons();
			turnIsComplete = false;
			isGameOver();
		}
	}

	public Player getNextTurnTaker() {
		int currentPlayerId = currentTurnTaker.getPlayerId();
		int nextPlayerId;
		boolean isEndOfFirstRound = round == 0 && currentPlayerId == (Config.NUMBER_OF_PLAYERS - 1);
		boolean isSecondRoundNotFirst = round == 1 && currentPlayerId > 0;
		boolean isEndOfSecondRound = round == 1 && currentPlayerId == 0;
		if (isEndOfFirstRound) {
			logger.info("End of round {}", round);
			round++;
			nextPlayerId = Config.NUMBER_OF_PLAYERS -1;
		} else if (isSecondRoundNotFirst) {
			nextPlayerId = currentPlayerId - 1;
		} else if (currentPlayerId < (Config.NUMBER_OF_PLAYERS - 1)) {
			nextPlayerId = currentPlayerId + 1;
		} else {
			nextPlayerId = 0;
			logger.info("End of round {}", round);
			round++;
		}
		if (isEndOfSecondRound) {
			logger.info("End of round {}", round);
			nextPlayerId = 0;
			round++;
		}
		return players.get(nextPlayerId);

	}

	public Board getBoard() {
		return board;
	}

	public GameSkinWrapper getSkin() {
		return skin;
	}

	public void holdGamePiece(GamePiece piece) {
		logger.debug("Holding {}", piece.getClass());
		if (heldPiece == null) {
			heldPiece = piece;
			board.putPiece(piece);
			board.followMouse(piece);
		}
	}

	public void removeHeldGamePiece() {
		removeGamePiece(heldPiece);
		heldPiece = null;
	}

	public GamePiece getHeldPiece() {
		return heldPiece;
	}

	public void removeGamePiece(GamePiece piece) {
		board.unfollowMouse();
		board.removePiece(piece);
		heldPiece = null;
	}

	public void settlementPlaced() {
		controls.settlementPlaced();
	}

	public void roadPlaced() {
		controls.roadPlaced();
	}

	public GameControls getControls() {
		return controls;
	}

	public boolean isSetup() {
		return round <= 1;
	}
	
	public int getRound() {
		return round;
	}
	
	public void turnIsComplete() {
		turnIsComplete = true;
	}

}
