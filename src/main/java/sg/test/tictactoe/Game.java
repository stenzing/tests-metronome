package sg.test.tictactoe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import sg.test.tictactoe.api.Board;
import sg.test.tictactoe.api.BoardFieldInvalidException;
import sg.test.tictactoe.api.BoardSizeInvalidException;
import sg.test.tictactoe.api.LocalChecker;
import sg.test.tictactoe.api.FieldOwner;
import sg.test.tictactoe.api.GlobalChecker;
import sg.test.tictactoe.api.Player;
import sg.test.tictactoe.api.Point;
import sg.test.tictactoe.impl.ConsoleView;
import sg.test.tictactoe.impl.board.ParamtericBoard;
import sg.test.tictactoe.impl.checker.AggregateChecker;
import sg.test.tictactoe.impl.checker.CheckDraw;
import sg.test.tictactoe.impl.checker.CheckNSVictory;
import sg.test.tictactoe.impl.checker.CheckNWSEVictory;
import sg.test.tictactoe.impl.checker.CheckSWNEVictory;
import sg.test.tictactoe.impl.checker.CheckWEVictory;
import sg.test.tictactoe.impl.player.CompPlayer;
import sg.test.tictactoe.impl.player.LineInPlayer;

public class Game {

	static final String CFG_BOARD_SIZE = "boardSize";
	private static final String DEFAULT_CONFIG_PATH = "src/main/resources/config.properties";
	private LocalChecker chkVictoryCondition;
	private GlobalChecker chkDrawCondition;
	private Board board;
	private Player[] players;
	private ConsoleView view;

	public Game(int size, Player[] players) throws BoardSizeInvalidException {
		board = new ParamtericBoard(size);
		chkVictoryCondition = new AggregateChecker(new LocalChecker[] { new CheckNSVictory(board),
				new CheckWEVictory(board), new CheckNWSEVictory(board), new CheckSWNEVictory(board) });
		chkDrawCondition = new CheckDraw(board);
		this.players = players;
		view = new ConsoleView();
	}

	public Player getPlayerByFieldOwner(FieldOwner o) {
		for (Player player : players) {
			if (player.getOwnerValue().equals(o)) {
				return player;
			}
		}
		return null;
	}

	public Board getBoard() {
		return board;
	}

	Player run() {
		boolean isGameOver = false;
		Player winner = null;

		while (!isGameOver) {
			for (int i = 0; i < players.length; i++) {
				while (!isGameOver) {
					view.onDisplay(this, System.out);
					System.out.println("Next is " + players[i]);
					Point move = players[i].getNextMove();
					System.out.println("Player choose " + move);
					try {
						if (board.claimField(move, players[i].getOwnerValue())) {
							if (chkVictoryCondition.checkCondition(move)) {
								isGameOver = true;
								winner = players[i];
							}
							break;
						} else {
							System.err.println("Place not claimable: " + move);
						}
					} catch (BoardFieldInvalidException e) {
						System.err.println("Bad next move: " + e.getMessage());
					}
				}
				if (chkDrawCondition.checkCondition()) {
					isGameOver = true;
				}
			}
		}
		view.onDisplay(this, System.out);
		if (winner == null) {
			System.out.println("No winner in the game, DRAW");
		} else {
			System.out.println("Winner was: " + winner);
		}
		
		return winner;
	}

	public static void main(String[] args) throws NumberFormatException, BoardSizeInvalidException {
		String configLocation = DEFAULT_CONFIG_PATH;

		if (args.length > 0) {
			configLocation = args[0];
		}
		Properties p = loadConfiguration(configLocation);
		if (p ==null) {
			System.exit(1);
		}
		Player[] players = createPlayers(p);
		

		if (players.length < 2) {
			System.err.println("Configuration error. Too few players");
			System.exit(1);
		}
		Game game = new Game(Integer.parseInt(p.getProperty(CFG_BOARD_SIZE)), players);

		System.out.println("Game start");
		game.run();
		System.out.println("Game ended");
	}

	public static Player[] createPlayers(Properties p) {
		List<Player> players = new LinkedList<Player>();
		Iterator<FieldOwner> nextAvailablePlayer = FieldOwner.getValidPlayers().iterator();

		int i = 1;
		while (p.getProperty("player." + i + ".sign") != null) {
			char symbol = p.getProperty("player." + i + ".sign").charAt(0);
			FieldOwner place = nextAvailablePlayer.next();
			Player player = null;
			if ("human".equals(p.getProperty("player." + i + ".type", "computer"))) {
				player = new LineInPlayer(place, symbol, System.in);
			} else {
				player = new CompPlayer(place, symbol, Integer.parseInt(p.getProperty(CFG_BOARD_SIZE)));
			}
			players.add(player);
			System.out.println("Player initialized:" + player);
			i++;
		}
		System.out.println("Number of players: " + (i - 1));
		Collections.shuffle(players);
		return players.toArray(new Player[] {});
	}

	public static Properties loadConfiguration(String configLocation) {

		Properties p = new Properties();
		try {
			p.load(Files.newInputStream(Paths.get(configLocation), StandardOpenOption.READ));
		} catch (IOException e) {
			System.out.println("Could not load config file:" + configLocation);
			return null;
		}
		return p;
	}

}
