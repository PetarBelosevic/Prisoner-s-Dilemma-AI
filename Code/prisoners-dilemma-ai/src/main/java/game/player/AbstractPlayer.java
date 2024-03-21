package game.player;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     Abstract class that implements general functionalities of IPlayer interface.
 * </p>
 */
public abstract class AbstractPlayer implements IPlayer {
    protected List<Integer> scoreHistory;
    protected List<Integer> decisionHistory;
    protected int score = 0;
    protected int index = 0;

    protected AbstractPlayer() {
        this(16);
    }

    protected AbstractPlayer(int initialSize) {
        this.scoreHistory = new ArrayList<>(initialSize);
        this.decisionHistory = new ArrayList<>(initialSize);
    }

    @Override
    public void addPoints(int points) {
        score += points;
        scoreHistory.add(points);
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void resetScore() {
        score = 0;
        scoreHistory.clear();
    }

    @Override
    public List<Integer> getScoreHistory() {
        return scoreHistory;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public List<Integer> getDecisionHistory() {
        return decisionHistory;
    }
}
