package game;

/**
 * <p>
 *     Observer for IGame objects.
 * </p>
 */
public interface GameObserver {
    /**
     * <p>
     *     Method should be called when the game is stopped before it's regular end.
     * </p>
     */
    void gameStopped();
}
