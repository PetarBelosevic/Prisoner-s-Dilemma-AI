package game.player;

import java.util.List;

/**
 * Player model for simple games modeled by IGame interface.
 */
public interface IPlayer {
    /**
     * <p>
     *     Gives decision based on the game history (past decisions of other player).
     * </p>
     * This method must update decision history of this player.
     *
     * @param otherDecisionHistory decisions of other player
     * @return true or false
     */
    int getDecision(List<Integer> otherDecisionHistory);

    /**
     * <p>
     *     Add points to the player.
     * </p>
     * Points should also be added in the score history of this player.
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
     * History of scores and decisions are also deleted.
     */
    void reset();

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

    /**
     * <p>
     *     Returns list of past decisions of this player.
     * </p>
     *
     * @return list of past decisions
     */
    List<Integer> getDecisionHistory();
}
