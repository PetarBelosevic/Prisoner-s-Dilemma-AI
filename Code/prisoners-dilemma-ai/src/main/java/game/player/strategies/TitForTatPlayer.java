package game.player.strategies;

import java.util.List;

/**
 * <p>
 *     Player with Tit for Tat strategy.
 * </p>
 * Tit for That always responds with other player's last move.
 */
public class TitForTatPlayer extends AbstractStrategyPlayer {
    @Override
    public int getDecision(List<Integer> otherDecisionHistory) {
        if (otherDecisionHistory.isEmpty()) {
            return 1;
        }
        return otherDecisionHistory.get(otherDecisionHistory.size() - 1);
    }
}
