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
    private static final Pair<Integer, Integer> COOP_COOP = new Pair<>(PDConstants.C_C, PDConstants.C_C);
    private static final Pair<Integer, Integer> DEFECT_DEFECT = new Pair<>(PDConstants.D_D, PDConstants.D_D);
    private static final Pair<Integer, Integer> COOP_DEFECT = new Pair<>(PDConstants.C_D, PDConstants.D_C);
    private static final Pair<Integer, Integer> DEFECT_COOP = new Pair<>(PDConstants.D_C, PDConstants.C_D);
    private static final Pair<Integer, Integer> IGNORE_PLAY = new Pair<>(PDConstants.ERROR, PDConstants.ERROR);

    public PDGame(T player1, D player2, int iterations) {
        super(player1, player2, iterations);
    }

    /**
     * {@inheritDoc}
     * Valid decisions of each player are PDConstants.COOPERATE and PDConstants.DEFECT.
     * If the game was stopped players decisions are ignored.
     *
     * @throws IllegalDecisionException if any of the players return invalid decisions
     */
    @Override
    protected void evaluateDecisions() {
        int x1 = getPlayer1().getDecision(getPlayer2().getDecisionHistory());
        int x2 = getPlayer2().getDecision(getPlayer1().getDecisionHistory());
        Pair<Integer, Integer> score;
        getPlayer1().getDecisionHistory().add(x1);
        getPlayer2().getDecisionHistory().add(x2);

        if (x1 > 0 && x2 > 0) {
            score = COOP_COOP;
        }
        else if (x1 > 0 && x2 < 0) {
            score = COOP_DEFECT;
        }
        else if (x1 < 0 && x2 > 0) {
            score = DEFECT_COOP;
        }
        else if (x1 < 0 && x2 < 0) {
            score = DEFECT_DEFECT;
        }
        else if (stop) {
            score = IGNORE_PLAY;
        }
        else {
            throw new IllegalDecisionException();
        }
        updateScores(score);
    }
}
