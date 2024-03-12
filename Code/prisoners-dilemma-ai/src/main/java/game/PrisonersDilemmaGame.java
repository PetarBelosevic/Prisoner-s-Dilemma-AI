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
    private final IPlayer player1;
    private final IPlayer player2;
    private final int iterations; // number of repetitions of Prisoner's Dilemma game

    public PrisonersDilemmaGame(IPlayer player1, IPlayer player2, int iterations) {
        player1.setIndex(1);
        player2.setIndex(2);
        this.player1 = player1;
        this.player2 = player2;
        this.iterations = iterations;
    }

    public IPlayer getPlayer1() {
        return player1;
    }
    public IPlayer getPlayer2() {
        return player2;
    }
    public int getIterations() {
        return iterations;
    }

    /**
     * <p>
     *     Runs game of Prisoner's Dilemma for number of repetitions defined by the value of iterations filed.
     * </p>
     */
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
        boolean x1 = player1.getDecision(player2.getScoreHistory());
        boolean x2 = player2.getDecision(player1.getScoreHistory());
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
