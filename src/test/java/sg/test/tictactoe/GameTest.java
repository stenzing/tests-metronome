package sg.test.tictactoe;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mockito;

import sg.test.tictactoe.api.BoardSizeInvalidException;
import sg.test.tictactoe.api.FieldOwner;
import sg.test.tictactoe.api.Player;
import sg.test.tictactoe.impl.player.CompPlayer;
import sg.test.tictactoe.impl.player.LineInPlayer;

@RunWith(BlockJUnit4ClassRunner.class)
public class GameTest {

	@Test
	public void testInit() throws BoardSizeInvalidException {
		Player p1 = Mockito.spy(new CompPlayer(FieldOwner.P1, 'A', 3));
		Player p2 = Mockito.spy(new CompPlayer(FieldOwner.P2, 'B', 3));
		Game target = new Game(3, new Player[] { p1, p2 });

		Assert.assertNotNull(target);
	}

	@Test
	public void testGetPlayerByFieldOwner() throws BoardSizeInvalidException {
		Player p1 = new CompPlayer(FieldOwner.P1, 'A', 3);
		Player p2 = new CompPlayer(FieldOwner.P2, 'B', 3);
		Player p3 = new CompPlayer(FieldOwner.P3, 'C', 3);
		Game target = new Game(3, new Player[] { p1, p2, p3 });

		Assert.assertNotNull(target);

		Assert.assertEquals(p1, target.getPlayerByFieldOwner(FieldOwner.P1));
		Assert.assertEquals(p2, target.getPlayerByFieldOwner(FieldOwner.P2));
		Assert.assertEquals(p3, target.getPlayerByFieldOwner(FieldOwner.P3));
		Assert.assertNull(target.getPlayerByFieldOwner(FieldOwner.P4));
	}

	@Test
	public void testRunProcessExitInTime() throws BoardSizeInvalidException, InterruptedException, ExecutionException {
		Properties p = Game.loadConfiguration("src/test/resources/config_runtest.properties");
		Player[] players = Game.createPlayers(p);

		final Game game = new Game(3, players);

		Assert.assertNotNull(game);
		ExecutorService executor = Executors.newFixedThreadPool(1);
		Callable<Player> callable = new Callable<Player>() {

			public Player call() throws Exception {
				return game.run();
			}
		};
		FutureTask<Player> task = new FutureTask<Player>(callable);
		executor.execute(task);
		TimeUnit.SECONDS.sleep(3);
		try {
			Assert.assertTrue("Game not fiinshed within reasonable time", task.isDone());
		} finally {
			if (!task.isDone()) {
				task.cancel(true);
			} else {
				task.get();
			}
		}

	}

	@Test
	public void testLoadConfigurationCall() {
		Properties p = Game.loadConfiguration("src/test/resources/config_test.properties");

		Assert.assertTrue(p.equals(getTestConfiguration()));
	}

	@Test
	public void testMissingConfigFile() {
		Properties p = Game.loadConfiguration("src/test/resources/notThere.properties");

		Assert.assertNull(p);
	}

	@Test
	public void testCreatePlayersCall() {
		Properties config = getTestConfiguration();
		Player[] result = Game.createPlayers(config);

		Assert.assertEquals(3, result.length);

		for (Player player : result) {
			switch (player.getOwnerValue()) {
			case P1:
				checkPlayer(player, '1', FieldOwner.P1, LineInPlayer.class);
				break;
			case P2:
				checkPlayer(player, '2', FieldOwner.P2, CompPlayer.class);
				break;
			case P3:
				checkPlayer(player, '3', FieldOwner.P3, CompPlayer.class);
				break;
			default:
				Assert.fail();
			}
		}
	}

	private void checkPlayer(Player p, char symbol, FieldOwner owner, Class<? extends Player> clazz) {
		Assert.assertTrue(p.getClass().getSimpleName().equals(clazz.getSimpleName()));
		Assert.assertEquals(symbol, p.getSymbol());
		Assert.assertEquals(owner, p.getOwnerValue());
	}

	private static Properties getTestConfiguration() {
		Properties config = new Properties();
		config.setProperty(Game.CFG_BOARD_SIZE, "3");
		config.setProperty("player.1.sign", "1");
		config.setProperty("player.1.type", "human");
		config.setProperty("player.2.sign", "2");
		config.setProperty("player.3.sign", "3");
		return config;
	}

}
