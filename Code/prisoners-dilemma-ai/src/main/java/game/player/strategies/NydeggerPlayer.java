package game.player.strategies;

import game.PDConstants;

import java.util.List;

/**
 * <p>
 *     Strategy in Axelrod's first tournament submitted by Rudy Nydegger of the Department of Psychology, Union College.
 * </p>
 * Strategy is nice.
 * Strategy plays Tit for Tat for the first three moves, except if the first two moves were (C, D) and (D, C), then it plays defection on the third move.
 * After that it calculates weighted sum A = (16 * D1(n) + 4 * D1(n-1) + D1(n-2)) + (16 * D2(n) + 4 * D2(n-1) + D2(n-2)),
 * where D1(i) = 1 if this strategy played defect on the i-th move and 0 otherwise, and
 * D2(i) = 2 if other player played defect on the i-th move and 0 otherwise.
 * If A equals 1, 6, 7, 17, 22, 23, 26, 29, 30, 31, 33, 38, 39, 45, 49, 54, 55, 58 or 61 then this strategy plays defect, and cooperates otherwise.
 */
public class NydeggerPlayer extends AbstractStrategyPlayer {
    private static final List<Integer> DEFECT_SUMS = List.of(1, 6, 7, 17, 22, 23, 26, 29, 30, 31, 33, 38, 39, 45, 49, 54, 55, 58, 61);
    private int myDefections = 0;
    private int othersDefections = 0;

    @Override
    public int getDecision(List<Integer> otherDecisionHistory) {
        int n = otherDecisionHistory.size();
        if (!getDecisionHistory().isEmpty()) {
            myDefections /= 4;
            othersDefections /= 4;

            if (getDecisionHistory().get(n - 1) == PDConstants.DEFECT) {
                myDefections += 16;
            }
            if (otherDecisionHistory.get(n - 1) == PDConstants.DEFECT) {
                othersDefections += 32;
            }
        }

        if (
                getDecisionHistory().size() == 2 &&
                otherDecisionHistory.get(0) == PDConstants.DEFECT &&
                otherDecisionHistory.get(1) == PDConstants.COOPERATE
        ) {
            return PDConstants.DEFECT;
        }
        else if (getDecisionHistory().size() <= 2) {
            if (otherDecisionHistory.isEmpty()) {
                return PDConstants.COOPERATE;
            }
            return otherDecisionHistory.get(n - 1);
        }

        if (DEFECT_SUMS.contains(myDefections + othersDefections)) {
            return PDConstants.DEFECT;
        }
        else {
            return PDConstants.COOPERATE;
        }
    }

    @Override
    public void reset() {
        super.reset();
        myDefections = 0;
        othersDefections = 0;
    }
}
