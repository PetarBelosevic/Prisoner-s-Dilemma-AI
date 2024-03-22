package game;

import game.player.IPlayer;

/**
 * <p>
 *     Model of a iterative two-player game in game theory.
 * </p>
 * @param <T> type of player1
 * @param <D> type of player2
 */
public interface IGame<T extends IPlayer, D extends IPlayer> {
    /**
     * @return player 1
     */
    T getPlayer1();

    /**
     * <p>
     *     Updates player1.
     * </p>
     * @param player1
     */
    void setPlayer1(T player1);

    /**
     * @return player 2
     */
    D getPlayer2();

    /**
     * <p>
     *     Updates player1.
     * </p>
     * @param player2
     */
    void setPlayer2(D player2);

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
