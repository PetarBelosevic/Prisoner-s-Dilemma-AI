package game.player.strategies;

import java.util.List;

public class DowningPlayer extends AbstractStrategyPlayer {
    double pCC = 0.5;
    double pCD = 0.5;
    int nDC = 0;
    int nDX = 0;
    int nCC = 0;
    int nCX = 0;
    int lastMove = -1;
    int nC = 0;
    int nD = 0;

    @Override
    public int getDecision(List<Integer> otherDecisionHistory) {
        int nGames = otherDecisionHistory.size();
        if (nGames == 0) {
            return returnDefection();
        }
        else if (nGames == 1) {
            if (otherDecisionHistory.get(0) == 1) {
                nCC++;
            }
            return returnDefection();
        }

        if (getDecisionHistory().get(nGames - 2) == 1 && otherDecisionHistory.get(nGames - 1) == 1) {
            nCC++;
        }
        else if (getDecisionHistory().get(nGames - 2) == -1 && otherDecisionHistory.get(nGames - 1) == 1) {
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

        if (lastMove == 1) {
            return returnDefection();
        }
        else {
            return returnCooperation();
        }
    }

    @Override
    public void reset() {
        super.reset();
        pCC = 0.5;
        pCD = 0.5;
        nDC = 0;
        nDX = 0;
        nCC = 0;
        nCX = 0;
        lastMove = -1;
        nC = 0;
        nD = 0;
    }

    private int returnCooperation() {
        nC++;
        lastMove = 1;
        return 1;
    }

    private int returnDefection() {
        nD++;
        lastMove = -1;
        return -1;
    }
}
