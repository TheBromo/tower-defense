package ch.zhaw.team5.model.phases;

import java.util.concurrent.TimeUnit;
/**
 * PausePhase Class extends Phase. 
 *
 * @author strenman
 * @version 1.0.0
 */
public class PausePhase extends Phase {
    private int count = 0;

    /**
     * Constructor of PausePhase
     * @param totalTimeSeconds represents the time of pause
     */
    public PausePhase( long totalTimeSeconds) {
        super( totalTimeSeconds);
    }

    /**
     * Increase difficulty
     */
    @Override
    public void increaseDifficulty() {
        totalTimeMS += TimeUnit.SECONDS.toMillis(10);
        count++;
    }

    /**
     * Get the amount of enemies
     * @return 0
     */
    @Override
    public int getEnemyAmount() {
        return 0;
    }

    /**
     * Override toString() method
     * @return a string with the amount
     */
    @Override
    public String toString() {
        return "Pause " + count;
    }

}
