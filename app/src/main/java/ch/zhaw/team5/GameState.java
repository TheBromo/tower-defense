package ch.zhaw.team5;

import ch.zhaw.team5.model.gameobj.Enemy;
import javafx.beans.property.*;

/**
 * The GameState class is responsible for maintaining the state of the game.
 * It includes information about the player's money, health, game progress, and other game state data.
 *
 * @author strenman, hartmdo1, paskueng, mettlmi1
 * @version 1.0.0
 */
public class GameState {
    public static final int upgradeTowerCost = 70;
    public static final int buyTowerCost = 50;
    private final int priceOfHealthLoading = 20;
    private final int healthDose = 20;
    private final IntegerProperty money = new SimpleIntegerProperty();
    private final DoubleProperty health = new SimpleDoubleProperty();
    private final DoubleProperty progress = new SimpleDoubleProperty();
    private final BooleanProperty renderNeeded = new SimpleBooleanProperty();
    private final BooleanProperty gameEnded = new SimpleBooleanProperty();
    private final StringProperty gamePhaseName = new SimpleStringProperty();

    /**
     * Initializes a new GameState object.
     */
    public GameState() {
        gameEnded.set(false);
        health.set(100);
        money.set(150);
    }

    public final IntegerProperty moneyProperty() {
        return money;
    }

    public final DoubleProperty healthProperty() {
        return health;
    }

    public final DoubleProperty progressProperty() {
        return progress;
    }

    public final BooleanProperty renderNeededProperty() {
        return renderNeeded;
    }

    public final BooleanProperty gameEndProperty() {
        return gameEnded;
    }

    public final StringProperty gamePhaseNameProperty() {
        return gamePhaseName;
    }

    /**
     * Adds a certain amount of money to the player's total money.
     *
     * @param money the amount of money to add
     */
    public void addMoney(int money) {
        this.money.set(this.money.get() + money);
    }

    public void setProgress(double progress) {
        this.progress.set(progress);
    }

    public void setRenderNeeded(boolean renderNeeded) {
        this.renderNeeded.set(renderNeeded);
    }

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded.set(gameEnded);
    }

    public void setGamePhaseName(String gamePhase) {
        this.gamePhaseName.set(gamePhase);
    }

    /**
     * Makes the player buy health if the player has enough money and the health is less than 100.
     */
    public void buyHealth() {
        if (money.get() >= 20 && health.get() < 100) {
            health.set(Math.min(health.get() + healthDose, 100));
            money.set(money.get() - priceOfHealthLoading);
        }
    }

    /**
     * Decreases the player's health based on the damage done by an enemy.
     *
     * @param enemy the enemy that invaded
     */
    public void enemyInvaded(Enemy enemy) {
        health.set(health.get() - enemy.getDamage());
    }

    /**
     * Upgrades a tower if the player has enough money.
     *
     * @return true if the tower was upgraded, false otherwise
     */
    public boolean upgradeTower() {
        if (money.get() >= upgradeTowerCost) {
            money.set(money.get() - upgradeTowerCost);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Buys a tower if the player has enough money.
     *
     * @return true if the tower was bought, false otherwise
     */
    public boolean buyTower() {
        if (money.get() >= buyTowerCost) {
            money.set(money.get() - buyTowerCost);
            return true;
        } else {
            return false;
        }
    }

    public int getHealthDose() {
        return healthDose;
    }

    public int getPriceOfHealthLoading() {
        return priceOfHealthLoading;
    }

    public int getUpgradeTowerCost() {
        return upgradeTowerCost;
    }

    public int getBuyTowerCost() {
        return buyTowerCost;
    }
}
