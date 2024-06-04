package game.player.strategies;

import game.PDConstants;

import java.util.List;

/**
 * <p>
 *     Strategy in Axelrod's first tournament submitted by Martin Shubik of the Department of Economics, Yale University.
 * </p>
 * Strategy is nice.
 * After defection by the other player, this strategy defects for k times.
 * After that, if other player defects again, this strategy defects for k+1 times.
 */
public class ShubikPlayer extends AbstractStrategyPlayer {
    private int k = 0;
    private int counter = 0;

    @Override
    public int getDecision(List<Integer> otherDecisionHistory) {
        if (counter < k) {
            counter++;
            return PDConstants.DEFECT;
        }
        int n = otherDecisionHistory.size();
        if (n == 0 || otherDecisionHistory.get(n - 1) == PDConstants.COOPERATE) {
            return PDConstants.COOPERATE;
        }
        k++;
        counter = 1;
        return PDConstants.DEFECT;
    }

    @Override
    public void reset() {
        super.reset();
        k = 0;
        counter = 0;
    }
}
