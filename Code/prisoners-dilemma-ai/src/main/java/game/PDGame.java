package game;

import game.player.IPlayer;
import game.player.IllegalDecisionException;
import utils.Pair;

/**
 * <p>
 *     Model of iterated Prisoner's Dilemma game.
 * </p>
 * @param <T> extends IPlayer, type of player1
 * @param <D> extends IPlayer, type of player2
 */
public class PDGame<T extends IPlayer, D extends IPlayer> extends AbstractGame<T, D> {
    // defined payoffs based on the decisions of both players
    private static final Pair<Integer, Integer> COOP_COOP = new Pair<>(3, 3);
    private static final Pair<Integer, Integer> DEFLECT_DEFLECT = new Pair<>(1, 1);
    private static final Pair<Integer, Integer> COOP_DEFLECT = new Pair<>(0, 5);
    private static final Pair<Integer, Integer> DEFLECT_COOP = new Pair<>(5, 0);

    public PDGame(T player1, D player2, int iterations) {
        super(player1, player2, iterations);
    }

    @Override
    protected void evaluateDecisions() {
        int x1 = getPlayer1().getDecision(getPlayer2().getDecisionHistory());
        int x2 = getPlayer2().getDecision(getPlayer1().getDecisionHistory());
        Pair<Integer, Integer> score;

        if (x1 > 0 && x2 > 0) {
            score = COOP_COOP;
        }
        else if (x1 > 0 && x2 < 0) {
            score = COOP_DEFLECT;
        }
        else if (x1 < 0 && x2 > 0) {
            score = DEFLECT_COOP;
        }
        else if (x1 < 0 && x2 < 0) {
            score = DEFLECT_DEFLECT;
        }
        else {
            throw new IllegalDecisionException();
        }
        updateScores(score);
    }
}
