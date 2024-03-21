package game;

import game.player.IPlayer;
import utils.Pair;

/**
 * <p>
 *     Models game of iterated Prisoner's Dilemma.
 * </p>
 */
public class PrisonersDilemmaGame implements IGame {
    // defined payoffs based on the decisions of both players
    private static final Pair<Integer, Integer> COOP_COOP = new Pair<>(3, 3);
    private static final Pair<Integer, Integer> DEFLECT_DEFLECT = new Pair<>(1, 1);
    private static final Pair<Integer, Integer> COOP_DEFLECT = new Pair<>(0, 5);
    private static final Pair<Integer, Integer> DEFLECT_COOP = new Pair<>(5, 0);
    private IPlayer player1;
    private IPlayer player2;
    private int iterations; // number of repetitions of Prisoner's Dilemma game

    public PrisonersDilemmaGame(IPlayer player1, IPlayer player2, int iterations) {
        player1.setIndex(1);
        player2.setIndex(2);
        this.player1 = player1;
        this.player2 = player2;
        this.iterations = iterations;
    }

    public PrisonersDilemmaGame(int iterations) {
        this.iterations = iterations;
    }

    @Override
    public IPlayer getPlayer1() {
        return player1;
    }

    @Override
    public void setPlayer1(IPlayer player1) {
        player1.setIndex(1);
        this.player1 = player1;
    }

    @Override
    public IPlayer getPlayer2() {
        return player2;
    }

    @Override
    public void setPlayer2(IPlayer player2) {
        player2.setIndex(2);
        this.player2 = player2;
    }

    @Override
    public int getIterations() {
        return iterations;
    }
    @Override
    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    @Override
    public void run() {
        player1.resetScore();
        player2.resetScore();
        for (int i = 0; i < iterations; i++) {
            evaluateDecisions();
        }
    }

    /**
     * <p>
     *     Runs 1 iteration of Prisoner's Dilemma game
     * </p>
     */
    private void evaluateDecisions() {
        boolean x1 = player1.getDecision(player2.getDecisionHistory());
        boolean x2 = player2.getDecision(player1.getDecisionHistory());
        Pair<Integer, Integer> score;

        if (x1 && x2) {
            score = COOP_COOP;
        }
        else if (x1 && !x2) {
            score = COOP_DEFLECT;
        }
        else if (!x1 && x2) {
            score = DEFLECT_COOP;
        }
        else {
            score = DEFLECT_DEFLECT;
        }

        updateScores(score);
    }

    /**
     * <p>
     *     Updates scores of both players.
     * </p>
     * @param score of both players
     */
    private void updateScores(Pair<Integer, Integer> score) {
        player1.addPoints(score.getFirst());
        player2.addPoints(score.getSecond());
    }
}