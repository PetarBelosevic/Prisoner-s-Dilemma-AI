package utils;

import game.player.strategies.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     Class with various globally shared variables.
 * </p>
 */
public class Constants {
    public static final double RANDOMNESS_LEVEL = 0.1;
    public static final Path DEFAULT_STORAGE = Path.of("src/main/resources/");
    public static final Path DEFAULT_TEST_STORAGE = Path.of("src/test/resources/");
    public static final List<AbstractStrategyPlayer> PD_STRATEGIES = new ArrayList<>();

    static {
        PD_STRATEGIES.add(new TitForTatPlayer());
        PD_STRATEGIES.add(new NydeggerPlayer());
        PD_STRATEGIES.add(new GrofmanPlayer());
        PD_STRATEGIES.add(new ShubikPlayer());
        PD_STRATEGIES.add(new FriedmanPlayer());
        PD_STRATEGIES.add(new DavisPlayer());

        PD_STRATEGIES.add(new DowningPlayer());
        PD_STRATEGIES.add(new JossPlayer());
        PD_STRATEGIES.add(new TullockPlayer());
        PD_STRATEGIES.add(new RandomPlayer());

        PD_STRATEGIES.add(new DetectivePlayer());
    }
}
