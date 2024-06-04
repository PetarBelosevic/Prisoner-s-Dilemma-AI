package game.player.strategies;

import game.PDConstants;

import java.util.List;

/**
 * <p>
 *     Strategy in Axelrod's first tournament submitted by Johann Joss of the Eidgenossishe Technische Hochschule.
 * </p>
 * Strategy is not nice.
 * Strategy cooperates 90% of the time after a cooperation by the other player and it always defects after defection by the other.
 */
public class JossPlayer extends AbstractStrategyPlayer {
    @Override
    public int getDecision(List<Integer> otherDecisionHistory) {
        if (otherDecisionHistory.isEmpty()) {
            return PDConstants.COOPERATE;
        }
        int lastMove = otherDecisionHistory.get(otherDecisionHistory.size() - 1);
        if (lastMove == PDConstants.DEFECT || Math.random() > 0.9) {
            return PDConstants.DEFECT;
        }
        return PDConstants.COOPERATE;
    }
}
