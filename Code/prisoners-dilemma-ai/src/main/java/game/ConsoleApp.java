package game;

import game.player.IPlayer;
import game.player.PrisonersDilemmaUserPlayer;

/**
 * <p>
 *      Simple console app for Prisoner's Dilemma game.
 * </p>
 */
public class ConsoleApp {
    public static void main(String[] args) {
        PrisonersDilemmaGame game = new PrisonersDilemmaGame(new PrisonersDilemmaUserPlayer(), new PrisonersDilemmaUserPlayer(), 6);
        game.run();
        printResults(game);
    }

    /**
     * <p>
     *     Prints game results.
     * </p>
     * Method should be called only after game finished.
     */
    private static void printResults(IGame game) {
        int iterations = game.getIterations();
        IPlayer player1 = game.getPlayer1();
        IPlayer player2 = game.getPlayer2();
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
}
