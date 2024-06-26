package game;

import game.observers.IGameObserver;
import game.player.IPlayer;
import utils.Pair;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *     Abstract implementation of IGame interface.
 * </p>
 * @param <T> type of first player
 * @param <D> type of second player
 */
public abstract class AbstractGame<T extends IPlayer, D extends IPlayer> implements IGame<T, D> {
    private T player1;
    private D player2;
    private final int iterations; // number of rounds in a game
    volatile boolean stop = false;
    private final List<IGameObserver> observers = new LinkedList<>();

    protected AbstractGame(T player1, D player2, int iterations) {
        player1.setIndex(1);
        player2.setIndex(2);
        this.player1 = player1;
        this.player2 = player2;
        this.iterations = iterations;
    }

    @Override
    public T getPlayer1() {
        return player1;
    }

    @Override
    public void setPlayer1(T player1) {
        this.player1 = player1;
        player1.reset();
    }

    @Override
    public D getPlayer2() {
        return player2;
    }

    @Override
    public void setPlayer2(D player2) {
        this.player2 = player2;
        player2.reset();
    }

    @Override
    public int getIterations() {
        return iterations;
    }

    @Override
    public void run() {
        player1.reset();
        player2.reset();
        stop = false;
        for (int i = 0; i < iterations && !stop; i++) {
            evaluateDecisions();
        }
    }

    /**
     * <p>
     *     Updates scores of both players.
     * </p>
     * @param score of both players
     */
    protected void updateScores(Pair<Integer, Integer> score) {
        player1.addPoints(score.getFirst());
        player2.addPoints(score.getSecond());
        notifyNewScore(score);
    }

    /**
     * <p>
     *     Runs 1 round of a game.
     * </p>
     * Method must update decision history of every player.
     */
    protected abstract void evaluateDecisions();

    @Override
    public synchronized void stopGame() {
        this.stop = true;
        notifyGameStopped();
    }

    @Override
    public void addGameObserver(IGameObserver go) {
        observers.add(go);
    }
    @Override
    public void removeGameObserver(IGameObserver go) {
        observers.remove(go);
    }
    @Override
    public void notifyGameStopped() {
        observers.forEach(IGameObserver::gameStopped);
    }
    @Override
    public void notifyNewScore(Pair<Integer, Integer> scores) {
        observers.forEach(go -> go.scoresAdded(scores));
    }
}
