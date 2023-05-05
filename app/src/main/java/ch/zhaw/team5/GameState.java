package ch.zhaw.team5;

import ch.zhaw.team5.model.Player;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameState {

    private Player player;
    private IntegerProperty money = new SimpleIntegerProperty();
    private DoubleProperty health = new SimpleDoubleProperty();
    private BooleanProperty renderNeeded = new SimpleBooleanProperty();

    public GameState(Player player) {
        this.player = player;
    }

    public final IntegerProperty moneyProperty() {
        return money;
    }

    public final DoubleProperty healthProperty() {
        return health;
    }

    public final BooleanProperty renderNeededProperty(){
        return renderNeeded;
    }

    public void setMoney(int money) {
        this.money.set(money);
    }

    public void setHealth(double health) {
        this.health.set(health);
    }

    public void setRenderNeeded(boolean renderNeeded){
        this.renderNeeded.set(renderNeeded);
    }

}
