package game.player.strategies;

import game.PDConstants;

import java.util.List;

/**
 * <p>
 *     Strategy in Axelrod's first tournament submitted by Gordon Tullock of the Center for Study of Public Choice, Virginia Polytechnic Institute and State University.
 * </p>
 * Strategy is not nice.
 * Strategy cooperates for the first 11 moves. It then cooperates 10% less than the other player has cooperated on the preceding ten moves.
 */
public class TullockPlayer extends AbstractStrategyPlayer {
    private int count = 0;

    @Override
    public int getDecision(List<Integer> otherDecisionHistory) {
        int n = otherDecisionHistory.size();
        if (n < 11) {
            if (!otherDecisionHistory.isEmpty() && otherDecisionHistory.get(n - 1) == PDConstants.COOPERATE) {
                count++;
            }
            return PDConstants.COOPERATE;
        }
        double p = Math.max(0, (1.0 * count / 10) - 0.1);

        if (otherDecisionHistory.get(n - 1) == PDConstants.COOPERATE) {
            count++;
        }
        if (otherDecisionHistory.get(n - 11) == PDConstants.COOPERATE) {
            count--;
        }

        if (Math.random() < p) {
            return PDConstants.COOPERATE;
        }
        else {
            return PDConstants.DEFECT;
        }
    }

    @Override
    public void reset() {
        super.reset();
        count = 0;
    }
}
