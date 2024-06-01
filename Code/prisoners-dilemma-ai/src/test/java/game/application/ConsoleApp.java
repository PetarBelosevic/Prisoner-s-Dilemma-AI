package game.application;

import game.PDGame;
import game.PDGameLogs;
import game.player.ConsolePDPlayer;
import game.player.strategies.AbstractStrategyPlayer;
import game.player.strategies.DowningPlayer;
import game.player.strategies.RandomPlayer;
import game.player.strategies.TitForTatPlayer;

/**
 * <p>
 *      Simple console app for Prisoner's Dilemma game.
 * </p>
 */
public class ConsoleApp {
    private static final int ITERATIONS = 200;

    public static void main(String[] args) {
        int n = 100;
        double pl1Avg = 0;
        double pl2Avg = 0;
        PDGame<?, ?> game = new PDGameLogs<>(
//                new TitForTatPlayer(),
                new RandomPlayer(),
//                new ConsolePDPlayer(),
                new DowningPlayer(),
                ITERATIONS
        );
        for (int i = 0; i < n; i++) {
            game.run();
            pl1Avg += game.getPlayer1().getScore();
            pl2Avg += game.getPlayer2().getScore();
        }
        pl1Avg /= n;
        pl2Avg /= n;
        System.out.println();
        System.out.println("Player 1 avg score: " + pl1Avg);
        System.out.println("Player 2 avg score: " + pl2Avg);
    }
}
