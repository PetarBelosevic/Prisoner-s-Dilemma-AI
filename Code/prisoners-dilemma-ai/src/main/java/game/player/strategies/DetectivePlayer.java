package game.player.strategies;

import game.PDConstants;

import java.util.List;

/**
 * <p>
 *     This strategy is inspired by the player in Nicky Case's <a href="https://ncase.me/trust/">"The Evolution of Trust"</a> game.
 * </p>
 * Strategy is not nice.
 * Strategy plays cooperate, defect, cooperate, cooperate.
 * Then it plays Tit for Tat if there were any defections by other player.
 * Otherwise it plays defect to exploit other player's unresponsiveness.
 */
public class DetectivePlayer extends AbstractStrategyPlayer {
    private boolean retaliated = false;

    @Override
    public int getDecision(List<Integer> otherDecisionHistory) {
        int n = otherDecisionHistory.size();
        if (!retaliated && n != 0 && otherDecisionHistory.get(n - 1) == PDConstants.DEFECT) {
            retaliated = true;
        }
        if (n == 0 || n == 2 || n == 3) {
            return PDConstants.COOPERATE;
        }
        else if (n == 1) {
            return PDConstants.DEFECT;
        }
        else if (!retaliated) {
               return PDConstants.DEFECT;
        }
        else {
            return otherDecisionHistory.get(n - 1);
        }
    }

    @Override
    public void reset() {
        super.reset();
        retaliated = false;
    }
}
