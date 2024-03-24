package game;

import game.player.IPlayer;
import utils.Pair;

public abstract class AbstractGame<T extends IPlayer, D extends IPlayer> implements IGame<T, D> {
    protected T player1;
    protected D player2;
    protected int iterations; // number of rounds in game

    public AbstractGame(T player1, D player2, int iterations) {
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
    public D getPlayer2() {
        return player2;
    }

    @Override
    public int getIterations() {
        return iterations;
    }

    @Override
    public void run() {
        player1.reset();
        player2.reset();
        for (int i = 0; i < iterations; i++) {
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
    }

    /**
     * <p>
     *     Runs 1 round of a game.
     * </p>
     */
    protected abstract void evaluateDecisions();
}
