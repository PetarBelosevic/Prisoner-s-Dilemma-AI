package game.player;

import java.util.List;
import java.util.Scanner;

/**
 * <p>
 *     Model of a player for Prisoner's Dilemma game that plays using the console input.
 * </p>
 * User can make decisions by inputting C for cooperation and D for deflection.
 */
public class ConsolePDPlayer extends AbstractPlayer {

    @Override
    public int getDecision(List<Integer> otherDecisionHistory) {
        System.out.print("Player" + index + " input: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        getDecisionHistory().add(input.equalsIgnoreCase("c") ? 1 : -1);
        return input.equalsIgnoreCase("c") ? 1 : -1;
    }
}