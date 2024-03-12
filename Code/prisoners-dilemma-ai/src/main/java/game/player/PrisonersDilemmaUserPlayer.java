package game.player;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 *     Model of a player for client usage.
 * </p>
 * User can make decisions by pressing C for cooperation and D for deflection.
 */
public class PrisonersDilemmaUserPlayer implements IPlayer {
    private final List<Integer> scoreHistory = new LinkedList<>();
    private int score = 0;
    private int index = 0;

    @Override
    public boolean getDecision(List<Integer> otherScoreHistory) {
        System.out.print("Player" + index + " input: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        return input.equalsIgnoreCase("c");
    }

    @Override
    public void addPoints(int points) {
        score += points;
        scoreHistory.add(points);
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void resetScore() {
        score = 0;
        scoreHistory.clear();
    }

    @Override
    public List<Integer> getScoreHistory() {
        return scoreHistory;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }
}
