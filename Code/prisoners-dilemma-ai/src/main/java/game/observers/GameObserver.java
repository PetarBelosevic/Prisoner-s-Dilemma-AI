package game.observers;

import utils.Pair;

/**
 * <p>
 *     Observer for IGame objects.
 * </p>
 */
public interface GameObserver {
    /**
     * <p>
     *     Method should be called when the game is stopped before it's regular end.
     * </p>
     */
    void gameStopped();

    void scoresAdded(Pair<Integer, Integer> scores);
}
