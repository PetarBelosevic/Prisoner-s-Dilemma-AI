package game;

import game.player.IPlayer;

/**
 * <p>
 *     Model of a iterative two-player game.
 * </p>
 * This model is also subject for GameObservers.
 *
 * @param <T> type of player1
 * @param <D> type of player2
 */
public interface IGame<T extends IPlayer, D extends IPlayer> {
    /**
     * @return player 1
     */
    T getPlayer1();

    /**
     * @return player 2
     */
    D getPlayer2();

    /**
     * @return number of iterations of game played between two players in one run
     */
    int getIterations();

    /**
     * <p>
     *     Runs game for defined number of iterations with two players.
     * </p>
     */
    void run();

    /**
     * <p>
     *     Sends signal to stop the game and it's players if necessary.
     * </p>
     */
    void stopGame();

    /**
     * <p>
     *     Registers game observers.
     * </p>
     * @param go GameObserver
     */
    void addGameObserver(GameObserver go);

    /**
     * <p>
     *     Removes game observers.
     * </p>
     * @param go GameObserver
     */
    void removeGameObserver(GameObserver go);

    /**
     * <p>
     *     Notifies all game observers.
     * </p>
     */
    void notifyGameObservers();
}
