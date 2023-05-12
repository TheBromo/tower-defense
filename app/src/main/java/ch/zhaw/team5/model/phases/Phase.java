package ch.zhaw.team5.model.phases;

import java.util.concurrent.TimeUnit;

/**
 * This class represents the abstract Phase class.
 *
 * @author strenman
 * @version 1.0.0
 */
public abstract class Phase {
    protected long intervalMS;
    protected long phaseStart;
    protected long lastUpdate;
    protected long totalTimeMS;

    /**
     * Constructor of Phase class.
     *
     * @param totalTimeSeconds represents the amount of time the phase lasts.
     */
    public Phase(long totalTimeSeconds) {
        intervalMS = 50;
        totalTimeMS = TimeUnit.SECONDS.toMillis(totalTimeSeconds);
        phaseStart = System.currentTimeMillis();
        lastUpdate = System.currentTimeMillis();
    }

    /**
     * Method to get the progress of the phase between the value from 0 to 100.
     *
     * @return the time in a double value of the current phase progress
     */
    public double getPhaseProgress() {
        return (100.0 / totalTimeMS) * (lastUpdate - phaseStart);
    }

    /**
     * Checks if the phase has ended and the time is over the limit.
     *
     * @return boolean if the phase has ended
     */
    public boolean hasEnded() {
        return lastUpdate - phaseStart > totalTimeMS;
    }

    /**
     * Updates the time of the phase.
     */
    public void updatePhase() {
        if (System.currentTimeMillis() - lastUpdate >= intervalMS && !hasEnded()) {
            lastUpdate = System.currentTimeMillis();
        }
    }

    /**
     * Restart the timer.
     */
    public void restartTimer() {
        phaseStart = System.currentTimeMillis();
        lastUpdate = System.currentTimeMillis();
    }

    /**
     * abstract method to increase the difficulty.
     */
    public abstract void increaseDifficulty();

    /**
     * abstract method to get the amount of the enemies.
     *
     * @return enemy amount
     */
    public abstract int getEnemyAmount();

    /**
     * abstract method to implement the toString() method.
     *
     * @return phase printed out as a string
     */
    public abstract String toString();
}
