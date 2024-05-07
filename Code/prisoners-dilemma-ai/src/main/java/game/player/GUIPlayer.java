package game.player;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class GUIPlayer extends AbstractPlayer {
    private final AtomicBoolean cooperateFlag = new AtomicBoolean(false);
    private final AtomicBoolean deflectFlag = new AtomicBoolean(false);

    public GUIPlayer() {
        super();
    }

    @Override
    public synchronized int getDecision(List<Integer> otherDecisionHistory) {
        boolean cooperate = cooperateFlag.compareAndSet(true, false);
        boolean deflect = deflectFlag.compareAndSet(true, false);

//        boolean cooperate = false;
//        boolean deflect = false;

        while (!cooperate && !deflect) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cooperate = cooperateFlag.compareAndSet(true, false);
            deflect = deflectFlag.compareAndSet(true, false);
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
}
