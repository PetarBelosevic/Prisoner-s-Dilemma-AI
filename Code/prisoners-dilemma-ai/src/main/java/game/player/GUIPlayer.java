package game.player;

import game.GameObserver;

import java.util.LinkedList;
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

        if (stop) {
            return 0;
        }

        if (cooperate) {
            return 1;
        }
        else {
            return -1;
        }
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
