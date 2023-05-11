package ch.zhaw.team5.model.phases;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ch.zhaw.team5.model.util.RandomUtil;

public class AttackPhase extends Phase {
    private int count = 0, randomFactor = 0;
    private AttackPatterns attackPatterns;
    private int[] currentPattern;

    public AttackPhase( long totalTimeSeconds) {
        super( totalTimeSeconds);
        attackPatterns = new AttackPatterns();
        currentPattern = attackPatterns.getRandomPattern();
    }

    @Override
    public void increaseDifficulty() {
        randomFactor = RandomUtil.getInstance().getRandomInRange(0, count + 1);
        count++;
        totalTimeMS += TimeUnit.SECONDS.toMillis(10);
    }

    @Override
    public int getEnemyAmount() {
        return getCurrentFactor(currentPattern) + count + randomFactor;
    }

    @Override
    public String toString() {
        return "Attack " + count;
    }

    public int getCurrentFactor(int[] pattern) {
        int index = (int) (Math.min((pattern.length - 1) / 100.0 * getPhaseProgress(), pattern.length - 1));
        return pattern[index];
    }

    class AttackPatterns {
        private final List<int[]> patterns = new ArrayList<>();

        public AttackPatterns() {
            patterns.add(new int[] { 0, 1, 2, 3, 2, 1, 0 });
            patterns.add(new int[] { 0, 1, 1, 4, 0, 0 });
            patterns.add(new int[] { 2, 2, 2, 0, 1, 0 });
            patterns.add(new int[] { 0, 2, 1, 1, 3, 0 });
            patterns.add(new int[] { 1, 2, 2, 3, 0 });
        }

        public int[] getRandomPattern() {
            var random = RandomUtil.getInstance();
            return random.getRandomCollectionElement(patterns);
        }
    }
}