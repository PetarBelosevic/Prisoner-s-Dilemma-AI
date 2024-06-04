package game.player;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     Abstract class that implements general functionalities of IPlayer interface.
 * </p>
 */
public abstract class AbstractPlayer implements IPlayer {
    private final List<Integer> scoreHistory = new ArrayList<>();
    private final List<Integer> decisionHistory = new ArrayList<>();
    private int score = 0;
    private int index = 0;

    protected AbstractPlayer() {}

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
    public void reset() {
        score = 0;
        scoreHistory.clear();
        decisionHistory.clear();
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
