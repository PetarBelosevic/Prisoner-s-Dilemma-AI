package game;

import game.player.IPlayer;

/**
 * <p>
 *     Model of a iterative two-player game in game theory.
 * </p>
 */
public interface IGame {
    IPlayer getPlayer1();
    IPlayer getPlayer2();
    int getIterations();

    /**
     * <p>
     *     Runs game for defined iterations with two players.
     * </p>
     */
    void run();
}
