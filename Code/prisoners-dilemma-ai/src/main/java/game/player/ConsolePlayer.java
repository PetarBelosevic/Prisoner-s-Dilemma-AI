package game.player;

import java.util.List;
import java.util.Scanner;

/**
 * <p>
 *     Model of a player for client usage.
 * </p>
 * User can make decisions by pressing C for cooperation and D for deflection.
 */
public class ConsolePlayer extends AbstractPlayer {

    @Override
    public boolean getDecision(List<Integer> otherScoreHistory) {
        System.out.print("Player" + index + " input: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        getDecisionHistory().add(input.equalsIgnoreCase("c") ? 1 : -1);
        return input.equalsIgnoreCase("c");
    }
}
