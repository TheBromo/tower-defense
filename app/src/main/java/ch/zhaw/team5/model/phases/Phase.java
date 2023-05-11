package ch.zhaw.team5.model.phases;

import java.util.concurrent.TimeUnit;

public abstract class Phase {
    protected long intveralMS;
    protected long phaseStart;
    protected long lastUpdate;
    protected long totalTimeMS;

    public Phase(long totalTimeSeconds) {
        intveralMS = 50;
        totalTimeMS = TimeUnit.SECONDS.toMillis(totalTimeSeconds);
        phaseStart = System.currentTimeMillis();
        lastUpdate = System.currentTimeMillis();
    }

    public double getPhaseProgress() {
        return (100.0 / totalTimeMS) * (lastUpdate - phaseStart);
    }

    public boolean hasEnded() {
        return lastUpdate - phaseStart > totalTimeMS;
    }

    public void updatePhase() {
        if (System.currentTimeMillis() - lastUpdate >= intveralMS && !hasEnded()) {
            lastUpdate = System.currentTimeMillis();
        }
    }

    public void restartTimer() {
        phaseStart = System.currentTimeMillis();
        lastUpdate = System.currentTimeMillis();
    }

    public abstract void increaseDifficulty();

    public abstract int getEnemyAmount();

    public abstract String toString();
}
