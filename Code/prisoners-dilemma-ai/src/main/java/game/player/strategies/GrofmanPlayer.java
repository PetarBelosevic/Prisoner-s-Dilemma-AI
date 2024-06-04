package game.player.strategies;

import game.PDConstants;

import java.util.List;

/**
 * <p>
 *     Strategy in Axelrod's first tournament submitted by Bernard Grofman of the Public Policy Research Organization, University of California.
 * </p>
 * Strategy is nice.
 * This strategy has 2/7 chance of cooperating after other player defected.
 * In other cases strategy always cooperates.
 */
public class GrofmanPlayer extends AbstractStrategyPlayer {
    @Override
    public int getDecision(List<Integer> otherDecisionHistory) {
        int n = otherDecisionHistory.size();
        if (n == 0 || otherDecisionHistory.get(n - 1).equals(getDecisionHistory().get(n - 1)) || Math.random() < (2. / 7)) {
            return PDConstants.COOPERATE;
        }
        return PDConstants.DEFECT;
    }
}
