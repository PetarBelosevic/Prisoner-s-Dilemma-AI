package game.player.strategies;

import game.PDConstants;

import java.util.List;

/**
 * <p>
 *     Player that plays random moves.
 * </p>
 * Strategy is not nice.
 */
public class RandomPlayer extends AbstractStrategyPlayer {
    @Override
    public int getDecision(List<Integer> otherDecisionHistory) {
        return Math.random() >= 0.5 ? PDConstants.COOPERATE : PDConstants.DEFECT;
    }
}
