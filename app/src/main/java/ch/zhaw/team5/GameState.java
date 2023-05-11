package ch.zhaw.team5;

import ch.zhaw.team5.model.gameobj.Enemy;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameState {
    private final int priceOfHealthLoading = 20;
    private final int healthDose = 20;
    public static final int upgradeTowerCost = 30;
    public static final int buyTowerCost = 50;

    private IntegerProperty money = new SimpleIntegerProperty();
    private DoubleProperty health = new SimpleDoubleProperty();
    private DoubleProperty progress = new SimpleDoubleProperty();
    private BooleanProperty renderNeeded = new SimpleBooleanProperty();
    private BooleanProperty gameEnded = new SimpleBooleanProperty();
    private StringProperty gamePhaseName = new SimpleStringProperty();

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

    public void addMoney(int money) {
        this.money.set(this.money.get() + money);
    }

    public void setHealth(double health) {
        this.health.set(health);
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

    public void buyHealth() {
        if (money.get() >= 20 && health.get() < 100) {
            health.set(Math.min(health.get() + healthDose, 100));
            money.set(money.get() - priceOfHealthLoading);
        }
    }

    public void enemyInvaded(Enemy enemy) {
        health.set(health.get() - enemy.getDamage());
    }

    public boolean upgradeTower() {
        if (money.get() >= upgradeTowerCost) {
            money.set(money.get() - upgradeTowerCost);
            return true;
        } else {
            return false;
        }
    }

    public boolean buyTower() {
        if (money.get() >= buyTowerCost) {
            money.set(money.get() - buyTowerCost);
            return true;
        } else {
            return false;
        }
    }
}
