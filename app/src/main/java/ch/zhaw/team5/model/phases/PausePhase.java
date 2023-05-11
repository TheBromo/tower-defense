package ch.zhaw.team5.model.phases;

import java.util.concurrent.TimeUnit;

public class PausePhase extends Phase {
    private int count = 0;

    public PausePhase(int intveralSeconds, long totalTimeSeconds) {
        super(intveralSeconds, totalTimeSeconds);
    }

    @Override
    public void update() {

    }

    @Override
    public void increaseDifficulty() {
        totalTimeMS += TimeUnit.SECONDS.toMillis(10);
        count++;
    }

    @Override
    public int getEnemyAmount() {
        return 0;
    }

    @Override
    public String toString() {
        return "Pause " + count;
    }

}
