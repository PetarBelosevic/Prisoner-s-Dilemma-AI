package game;

import game.player.IPlayer;

/**
 * <p>
 *     Model of a iterative two-player game in game theory.
 * </p>
 */
public interface IGame {
    /**
     * @return player 1
     */
    IPlayer getPlayer1();

    /**
     * <p>
     *     Updates player1.
     * </p>
     * @param player1
     */
    void setPlayer1(IPlayer player1);

    /**
     * @return player 2
     */
    IPlayer getPlayer2();

    /**
     * <p>
     *     Updates player1.
     * </p>
     * @param player2
     */
    void setPlayer2(IPlayer player2);

    /**
     * @return number of iterations of game played between two players in one run
     */
    int getIterations();

    /**
     * @param iterations new number of iterations of game played between two players in one run
     */
    void setIterations(int iterations);

    /**
     * <p>
     *     Runs game for defined number of iterations with two players.
     * </p>
     */
    void run();
}
