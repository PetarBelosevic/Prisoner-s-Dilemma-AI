package game.player.strategies;

import java.util.List;

public class RandomPlayer extends AbstractStrategyPlayer {
    @Override
    public int getDecision(List<Integer> otherDecisionHistory) {
        return Math.random() >= 0.5 ? 1 : -1;
    }
}
