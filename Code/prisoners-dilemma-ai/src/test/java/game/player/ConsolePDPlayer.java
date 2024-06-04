package game.player;

import game.PDConstants;

import java.util.List;
import java.util.Scanner;

/**
 * <p>
 *     Model of a player for Prisoner's Dilemma game that plays using the console input.
 * </p>
 * User can make decisions by inputting C for cooperation and D for defection.
 */
public class ConsolePDPlayer extends AbstractPlayer {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int getDecision(List<Integer> otherDecisionHistory) {
        System.out.print("Player" + getIndex() + " input: ");
        String input = scanner.nextLine();

        return input.equalsIgnoreCase("c") ? PDConstants.COOPERATE : PDConstants.DEFECT;
    }
}
