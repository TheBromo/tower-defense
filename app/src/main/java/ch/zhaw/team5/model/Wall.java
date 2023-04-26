package ch.zhaw.team5.model;

public class Wall {
    int healthOfWall;
    boolean hasWallPositiveHealth;

    public Wall(int healthOfWall, boolean hasWallPositiveHealth) {
        this.healthOfWall = 100;
        this.hasWallPositiveHealth=true;
    }

    public int reduceHealthOfWall () {
        healthOfWall = healthOfWall-5;
        return healthOfWall;
    }


    public int recoverHealthOfWall () {
        healthOfWall = healthOfWall+5;
        return healthOfWall;
    }

    public boolean checkHealthOfWall() {
        if (healthOfWall<0) {
            hasWallPositiveHealth = false;
        } else  {
            hasWallPositiveHealth = true;
        }
        return hasWallPositiveHealth;
    }
}
