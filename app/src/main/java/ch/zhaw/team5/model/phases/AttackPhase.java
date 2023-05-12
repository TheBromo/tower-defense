package ch.zhaw.team5.model.phases;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ch.zhaw.team5.model.util.RandomUtil;

/**
 * AttackPhase Class extends Phase, and implements different attack phases with increasing level of difficulties. 
 *
 * @author strenman
 * @version 1.0.0
 */
public class AttackPhase extends Phase {
    private int count = 0, randomFactor = 0;
    private AttackPatterns attackPatterns;
    private int[] currentPattern;

    /**
     * Constructor for AttackPhase class
     * @param totalTimeSeconds represents the time the attack phase lasts
     */
    public AttackPhase( long totalTimeSeconds) {
        super( totalTimeSeconds);
        attackPatterns = new AttackPatterns();
        currentPattern = attackPatterns.getRandomPattern();
    }

    /**
     * Increases difficulty of phase by increasing enemies and duration
     */
    @Override
    public void increaseDifficulty() {
        randomFactor = RandomUtil.getInstance().getRandomInRange(0, count + 1);
        count++;
        totalTimeMS += TimeUnit.SECONDS.toMillis(10);
    }

    /**
     * Get the amount of enemies
     * @return the amount of enemies
     */
    @Override
    public int getEnemyAmount() {
        return getCurrentFactor(currentPattern) + count + randomFactor;
    }

    /**
     * 
     * @return Attack count
     */
    @Override
    public String toString() {
        return "Attack " + count;
    }

    /**
     * Get the current factor of the game state
     * @param pattern to provide the current pattern
     * @return value of pattern
     */
    public int getCurrentFactor(int[] pattern) {
        int index = (int) (Math.min((pattern.length - 1) / 100.0 * getPhaseProgress(), pattern.length - 1));
        return pattern[index];
    }

    /**
     * Inner Class AttackPatterns 
     * Provides the different attack patterns
     * @author strenman
     * @version 1.0.0
     */
    class AttackPatterns {
        private final List<int[]> patterns = new ArrayList<>();

        public AttackPatterns() {
            patterns.add(new int[] { 2, 2, 3, 4, 3, 3, 2 });
            patterns.add(new int[] { 2, 2, 2, 5, 2, 2 });
            patterns.add(new int[] { 3, 3, 3, 2, 2, 2 });
            patterns.add(new int[] { 2, 2, 2, 2, 4, 2 });
            patterns.add(new int[] { 2, 3, 3, 4, 2 });
        }

        /**
         * Get a random pattern 
         * @return a random pattern 
         */
        public int[] getRandomPattern() {
            var random = RandomUtil.getInstance();
            return random.getRandomCollectionElement(patterns);
        }
    }
}