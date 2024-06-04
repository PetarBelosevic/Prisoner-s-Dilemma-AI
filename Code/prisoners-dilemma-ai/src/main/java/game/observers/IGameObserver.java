package game.observers;

import utils.Pair;

/**
 * <p>
 *     Observer for IGame objects.
 * </p>
 */
public interface IGameObserver {
    /**
     * <p>
     *     Method should be called when the game is stopped before it's regular end.
     * </p>
     */
    void gameStopped();

    /**
     * <p>
     *     Method should be called when one round of the game was played.
     * </p>
     * @param scores scores of both players in new round
     */
    void scoresAdded(Pair<Integer, Integer> scores);
}
