package ch.zhaw.team5.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    public int health;
    private int money;

    List<Tower> towerList = new ArrayList<Tower>();

    public Player(int health, int money, List<Tower> towers) {

        this.health = health;
        this.money = money;
        this.towerList = towers;

    }

    public int getHealth() {
        return this.health;
    }

    public int setHealth(int health) {
        return health;
    }

    public int getMoney() {
        return this.money;
    }

    public int setMoney(int money) {
        return money;
    }

    public List<Tower> getTowers() {
        return this.towerList;
    }

    public void addTower(Tower towers) {
        towerList.add(towers);
    }

}
