package game;

import game.player.IPlayer;
import utils.Pair;

/**
 * <p>
 *     Prisoner's Dilemma simulator with logs.
 * </p>
 * Simulator prints in console players' choices after every move and total score with history of moves at the end of game.
 * @param <T> type of player1
 * @param <D> type of player2
 */
public class PDGameLogs<T extends IPlayer, D extends IPlayer> extends PDGame<T, D> {
    public PDGameLogs(T player1, D player2, int iterations) {
        super(player1, player2, iterations);
    }

    @Override
    public void run() {
        super.run();
        printResults();
    }

    /**
     * <p>
     *     Prints game results.
     * </p>
     * Method should be called only after game finished.
     */
    private void printResults() {
        int iterations = getIterations();
        IPlayer player1 = getPlayer1();
        IPlayer player2 = getPlayer2();
        System.out.println("========================================");
        System.out.println("Game finished with score: " + player1.getScore() + " - " + player2.getScore());
        System.out.println("========================================");
        System.out.println("| Iteration | Player1 | Player2 |");
        System.out.println("|===========|=========|=========|");
        for (int i = 0; i < iterations; i++) {
            System.out.printf("|    %3d.   |    %1d    |    %1d    |%n", i+1, player1.getScoreHistory().get(i), player2.getScoreHistory().get(i));
            System.out.println("|-----------|---------|---------|");
        }
    }

    @Override
    protected void updateScores(Pair<Integer, Integer> score) {
        super.updateScores(score);
        System.out.println("Played: " + score);
    }
}
