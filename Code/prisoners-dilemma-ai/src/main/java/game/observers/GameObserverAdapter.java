package game.observers;

import utils.Pair;

/**
 * <p>
 *     Adapter for GameObserver interface with empty implementations.
 * </p>
 */
public abstract class GameObserverAdapter implements IGameObserver {
    @Override
    public void gameStopped() {}

    @Override
    public void scoresAdded(Pair<Integer, Integer> scores) {}
}
