package game.player.strategies;

import game.PDConstants;

import java.util.List;

/**
 * <p>
 *     Strategy in Axelrod's first tournament submitted by Morton Davis of the Department of Mathematics, City College.
 * </p>
 * Strategy is nice.
 * Strategy cooperates for the first 10 moves.
 * After that, if other player defects this strategy defects until the end of a game.
 */
public class DavisPlayer extends AbstractStrategyPlayer {
    private boolean wasDefected = false;

    @Override
    public int getDecision(List<Integer> otherDecisionHistory) {
        int n = otherDecisionHistory.size();
        if (n < 10) {
            return PDConstants.COOPERATE;
        }
        if (wasDefected) {
            return PDConstants.DEFECT;
        }
        if (otherDecisionHistory.get(n - 1) == PDConstants.DEFECT) {
            wasDefected = true;
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
