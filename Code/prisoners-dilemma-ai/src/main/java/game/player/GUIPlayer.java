package game.player;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class GUIPlayer extends AbstractPlayer {
    private final AtomicBoolean cooperateFlag = new AtomicBoolean(false);
    private final AtomicBoolean deflectFlag = new AtomicBoolean(false);
    private final AtomicBoolean stopFlag = new AtomicBoolean(false);

    public GUIPlayer() {
        super();
    }

    @Override
    public synchronized int getDecision(List<Integer> otherDecisionHistory) {
        boolean cooperate = cooperateFlag.compareAndSet(true, false);
        boolean deflect = deflectFlag.compareAndSet(true, false);
        boolean stop = stopFlag.compareAndSet(true, false);

        while (!cooperate && !deflect && !stop) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cooperate = cooperateFlag.compareAndSet(true, false);
            deflect = deflectFlag.compareAndSet(true, false);
            stop = stopFlag.compareAndSet(true, false);
        }

        int decision = 0;
        if (!stop) {
            if (cooperate) {
                decision = 1;
            }
            else {
                decision = -1;
            }
        }
        getDecisionHistory().add(decision);

        return decision;
    }

    public synchronized void setCooperateFlag(boolean value) {
        cooperateFlag.set(value);
        notify();
    }

    public synchronized void setDeflectFlag(boolean value) {
        deflectFlag.set(value);
        notify();
    }

    public synchronized void setStopFlag(boolean value) {
        stopFlag.set(value);
        notify();
    }
}
