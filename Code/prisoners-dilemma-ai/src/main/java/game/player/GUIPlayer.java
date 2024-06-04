package game.player;

import game.PDConstants;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 *     Player for Prisoner's Dilemma game adjusted for use in GUI applications.
 * </p>
 * Decisions are made in interaction with user.
 * Player is safe to use in multithreading environment.
 */
public class GUIPlayer extends AbstractPlayer {
    private final AtomicBoolean cooperateFlag = new AtomicBoolean(false);
    private final AtomicBoolean defectFlag = new AtomicBoolean(false);
    private final AtomicBoolean stopFlag = new AtomicBoolean(false);

    public GUIPlayer() {
        super();
    }

    @Override
    public synchronized int getDecision(List<Integer> otherDecisionHistory) {
        boolean cooperate = cooperateFlag.compareAndSet(true, false);
        boolean defect = defectFlag.compareAndSet(true, false);
        boolean stop = stopFlag.compareAndSet(true, false);

        while (!cooperate && !defect && !stop) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cooperate = cooperateFlag.compareAndSet(true, false);
            defect = defectFlag.compareAndSet(true, false);
            stop = stopFlag.compareAndSet(true, false);
        }

        int decision = PDConstants.ERROR;
        if (!stop) {
            decision = cooperate ? PDConstants.COOPERATE : PDConstants.DEFECT;
        }
        return decision;
    }

    @Override
    public void reset() {
        super.reset();
        setCooperateFlag(false);
        setDefectFlag(false);
        setStopFlag(false);
    }

    /**
     * <p>
     *     Sets new value for cooperation flag and notifies the sleeping thread.
     * </p>
     * @param value new value for cooperation flag
     */
    public synchronized void setCooperateFlag(boolean value) {
        cooperateFlag.set(value);
        notify();
    }

    /**
     * <p>
     *     Sets new value for defection flag and notifies the sleeping thread.
     * </p>
     * @param value new value for defection flag
     */
    public synchronized void setDefectFlag(boolean value) {
        defectFlag.set(value);
        notify();
    }

    /**
     * <p>
     *     Sets new value for stop flag and notifies the sleeping thread.
     * </p>
     * @param value new value for stop flag
     */
    public synchronized void setStopFlag(boolean value) {
        stopFlag.set(value);
        notify();
    }
}
