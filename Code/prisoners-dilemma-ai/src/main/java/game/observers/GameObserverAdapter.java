package game.observers;

import utils.Pair;

public abstract class GameObserverAdapter implements GameObserver {
    @Override
    public void gameStopped() {}

    @Override
    public void scoresAdded(Pair<Integer, Integer> scores) {}
}
