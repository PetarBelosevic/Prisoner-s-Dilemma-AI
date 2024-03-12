package game.player;

import java.util.List;

/**
 * Player model for iterated Prisoner's Dilemma
 */
public interface IPlayer {
    /**
     * <p>
     *     Gives decision (cooperate or deflect) based on the game history (scores of both players).
     * </p>
     * @param otherScoreHistory scores of other player
     * @return true if cooperates, false if deflects
     */
    boolean getDecision(List<Integer> otherScoreHistory);

    /**
     * <p>
     *     Add points to the player.
     * </p>
     * Points should also be added in the score history of a player.
     *
     * @param points to be added
     */
    void addPoints(int points);

    /**
     * <p>
     *      Returns current score of the player.
     * </p>
     * @return current score
     */
    int getScore();

    /**
     * <p>
     *     Resets current score to 0.
     * </p>
     * History of scores is also deleted.
     */
    void resetScore();

    /**
     * <p>
     *      Returns previously scored points.
     * </p>
     * @return List of previous points
     */
    List<Integer> getScoreHistory();

    /**
     * <p>
     *     Returns index of a player (1 or 2).
     * </p>
     * @return 1 or 2
     */
    int getIndex();

    /**
     * <p>
     *      Sets index of a player (1 or 2).
     * </p>
     * @param index index of a player (1 or 2)
     */
    void setIndex(int index);
}
