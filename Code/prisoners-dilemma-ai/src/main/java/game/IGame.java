package game;

import game.observers.IGameObserver;
import game.player.IPlayer;
import utils.Pair;

/**
 * <p>
 *     Model of a iterative two-player game.
 * </p>
 * This model is also subject for GameObservers.
 *
 * @param <T> type of player 1
 * @param <D> type of player 2
 */
public interface IGame<T extends IPlayer, D extends IPlayer> {
    /**
     * @return player 1
     */
    T getPlayer1();

    /**
     * @param player1 new player 1
     */
    void setPlayer1(T player1);

    /**
     * @return player 2
     */
    D getPlayer2();

    /**
     * @param player2 new player 2
     */
    void setPlayer2(D player2);

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
    void addGameObserver(IGameObserver go);

    /**
     * <p>
     *     Removes game observers.
     * </p>
     * @param go GameObserver
     */
    void removeGameObserver(IGameObserver go);

    /**
     * <p>
     *     Notifies all game observers that the game is stopped.
     * </p>
     */
    void notifyGameStopped();

    /**
     * <p>
     *     Notifies all game observers that the new score is added.
     * </p>
     * @param scores new score
     */
    void notifyNewScore(Pair<Integer, Integer> scores);
}
