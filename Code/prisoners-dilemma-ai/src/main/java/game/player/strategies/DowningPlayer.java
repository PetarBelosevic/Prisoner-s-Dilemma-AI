package game.player.strategies;

import game.PDConstants;

import java.util.List;

/**
 * <p>
 *     Strategy in Axelrod's first tournament submitted by Leslie Downing of the Department of Psychology, Union College.
 * </p>
 * Strategy is not nice.
 * Strategy selects its choice to maximize its own long term expected payoff.
 * It assumes that the other player cooperates with fixed probability which depends on whether this strategy cooperated of defected on the previous move.
 * Implementation based on
 * <a href="https://axelrod.readthedocs.io/en/fix-documentation/_modules/axelrod/strategies/axelrod_first.html#FirstByDowning.strategy">this</a>
 * Python source code.
 */
public class DowningPlayer extends AbstractStrategyPlayer {
    private int nDC = 0;
    private int nCC = 0;
    private int lastMove = PDConstants.DEFECT;
    private int nC = 0;
    private int nD = 0;

    @Override
    public int getDecision(List<Integer> otherDecisionHistory) {
        int nGames = otherDecisionHistory.size();
        if (nGames == 0) {
            return returnDefection();
        }
        else if (nGames == 1) {
            if (otherDecisionHistory.get(0) == PDConstants.COOPERATE) {
                nCC++;
            }
            return returnDefection();
        }

        if (getDecisionHistory().get(nGames - 2) == PDConstants.COOPERATE && otherDecisionHistory.get(nGames - 1) == PDConstants.COOPERATE) {
            nCC++;
        }
        else if (getDecisionHistory().get(nGames - 2) == PDConstants.DEFECT && otherDecisionHistory.get(nGames - 1) == PDConstants.COOPERATE) {
            nDC++;
        }

        double alpha = (double) nCC / (nC + 1);
        double beta = (double) nDC / Math.max(2, nD);

        double eC = alpha * 3 + (1 - alpha) * 0;
        double eD = beta * 5 + (1 - beta) * 1;

        if (eC > eD) {
            return returnCooperation();
        }
        else if (eC < eD) {
            return returnDefection();
        }

        if (lastMove == PDConstants.COOPERATE) {
            return returnDefection();
        }
        else {
            return returnCooperation();
        }
    }

    @Override
    public void reset() {
        super.reset();
        nDC = 0;
        nCC = 0;
        lastMove = PDConstants.DEFECT;
        nC = 0;
        nD = 0;
    }

    private int returnCooperation() {
        nC++;
        lastMove = PDConstants.COOPERATE;
        return PDConstants.COOPERATE;
    }

    private int returnDefection() {
        nD++;
        lastMove = PDConstants.DEFECT;
        return PDConstants.DEFECT;
    }
}
