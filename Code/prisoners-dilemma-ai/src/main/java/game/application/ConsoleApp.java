package game.application;

import game.PDGame;
import game.PDGameLogs;
import game.player.ConsolePDPlayer;

/**
 * <p>
 *      Simple console app for Prisoner's Dilemma game.
 * </p>
 */
public class ConsoleApp {
    private static final int ITERATIONS = 6;

    public static void main(String[] args) {
        PDGame<ConsolePDPlayer, ConsolePDPlayer> game = new PDGameLogs<>(
                new ConsolePDPlayer(),
                new ConsolePDPlayer(),
                ITERATIONS);
        game.run();
    }
}
