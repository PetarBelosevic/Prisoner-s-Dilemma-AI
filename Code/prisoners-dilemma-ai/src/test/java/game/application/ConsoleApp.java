package game.application;

import game.PDGame;
import game.PDGameLogs;
import game.player.strategies.DowningPlayer;
import game.player.strategies.NydeggerPlayer;

/**
 * <p>
 *      Simple console app for playing Prisoner's Dilemma game.
 * </p>
 */
public class ConsoleApp {
    private static final int ITERATIONS = 200;

    public static void main(String[] args) {
        int n = 100;
        double pl1Avg = 0;
        double pl2Avg = 0;
        PDGame<?, ?> game = new PDGameLogs<>(
//                new ConsolePDPlayer(),
//                new TitForTatPlayer(),
                new NydeggerPlayer(),
//                new GrofmanPlayer(),
//                new ShubikPlayer(),
//                new FriedmanPlayer(),
//                new DavisPlayer(),
                new DowningPlayer(),
//                new JossPlayer(),
//                new TullockPlayer(),
//                new RandomPlayer(),

//                new DetectivePlayer(),
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
