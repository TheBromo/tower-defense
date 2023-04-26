package ch.zhaw.team5.model;

public class Wall {
    int healthOfWall;
    int initialHealth = 100;
    boolean hasWallPositiveHealth;

    public Wall() {
        healthOfWall = initialHealth;
        hasWallPositiveHealth = true;
    }

    public int reduceHealthOfWall() {
        healthOfWall -= 5;
        return healthOfWall;
    }


    public int recoverHealthOfWall() {
        healthOfWall += 5;
        return healthOfWall;
    }

    public boolean checkHealthOfWall() {
        if (healthOfWall < 0) {
            hasWallPositiveHealth = false;
        } else {
            hasWallPositiveHealth = true;
        }
        return hasWallPositiveHealth;
    }
}
