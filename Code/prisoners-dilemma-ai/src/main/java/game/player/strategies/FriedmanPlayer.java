package game.player.strategies;

import game.PDConstants;

import java.util.List;

/**
 * <p>
 *     Strategy in Axelrod's first tournament submitted by James W. Friedman of the Department of Economics, University of Rochester.
 * </p>
 * Strategy is not nice.
 * It plays cooperation until other player defects.
 */
public class FriedmanPlayer extends AbstractStrategyPlayer {
    private boolean wasDefected = false;

    @Override
    public int getDecision(List<Integer> otherDecisionHistory) {
        if (!otherDecisionHistory.isEmpty() && otherDecisionHistory.get(otherDecisionHistory.size() - 1) == PDConstants.DEFECT) {
            wasDefected = true;
        }
        if (wasDefected) {
            return PDConstants.DEFECT;
        }
        else {
            return PDConstants.COOPERATE;
        }
    }

    @Override
    public void reset() {
        super.reset();
        wasDefected = false;
    }
}
