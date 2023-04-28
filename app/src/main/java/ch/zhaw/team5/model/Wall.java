package ch.zhaw.team5.model;

public class Wall {
    private int health;
    private final int reductionOfHealth=5;
    private final int increaseOfHealth=5;

    private final int initialHealth = 100;
    private boolean hasWallPositiveHealth;

    public Wall() {
        health = initialHealth;
        hasWallPositiveHealth = true;
    }

    public int reduceHealthOfWall() {
        health -= reductionOfHealth;
        return health;
    }


    public int recoverHealthOfWall() {
        health += increaseOfHealth;
        return health;
    }

    public boolean checkHealthOfWall() {
        if (health < 0) {
            hasWallPositiveHealth = false;
        } else {
            hasWallPositiveHealth = true;
        }
        return hasWallPositiveHealth;
    }
}
