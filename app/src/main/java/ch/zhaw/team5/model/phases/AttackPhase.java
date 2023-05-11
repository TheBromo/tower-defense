package ch.zhaw.team5.model.phases;

public class AttackPhase extends Phase {
    private int count = 0;

    public AttackPhase(int intveralSeconds, long totalTimeSeconds) {
        super(intveralSeconds, totalTimeSeconds);
    }

    @Override
    public void update() {

    }

    @Override
    public void increaseDifficulty() {
        count++;
    }

    @Override
    public int getEnemyAmount() {
        return 2;
    }

    @Override
    public String toString() {
        return "Attack " + count;
    }

}