package game.player.strategies;

import game.PDConstants;

import java.util.List;

/**
 * <p>
 *     Player with Tit for Tat strategy.
 * </p>
 * Strategy is nice.
 * Tit for Tat always starts with cooperation and after that responds with other player's last move.
 */
public class TitForTatPlayer extends AbstractStrategyPlayer {
    @Override
    public int getDecision(List<Integer> otherDecisionHistory) {
        if (otherDecisionHistory.isEmpty()) {
            return PDConstants.COOPERATE;
        }
        return otherDecisionHistory.get(otherDecisionHistory.size() - 1);
    }
}
