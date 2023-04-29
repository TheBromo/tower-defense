package ch.zhaw.team5;

import ch.zhaw.team5.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class GameViewController {

    private GameState playerDecorator;
    // private GameDecorator gameDecorator;

    @FXML
    private Label moneyLabel;

    @FXML
    private ProgressBar healthBar;
    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button buttonTower1;
    @FXML
    private Button buttonTower2;
    @FXML
    private Button buttonTower3;
    @FXML
    private Button buttonTower4;
    @FXML
    private Button buttonTower5;
    @FXML
    private Button buttonTower6;

    public void initializeListeners(Player player) {
        GameState gameState = new GameState(player);
        gameState.moneyProperty().addListener((observable, oldValue, newValue) -> {
            moneyLabel.setText(newValue + "$");
        });
        gameState.healthProperty().addListener((observable, oldValue, newValue) -> {
            healthBar.setProgress((double) newValue);
        });
        gameState.setHealth(player.getHealth());
        gameState.setMoney(player.getMoney());
    }

    public void onBuildTower1(ActionEvent event) {
        buildTower(1);
        buttonTower1.setDisable(true);
        buttonTower1.setText("Bought");
    }

    public void onBuildTower2(ActionEvent event) {
        buildTower(2);
        buttonTower2.setDisable(true);
        buttonTower2.setText("Bought");
    }

    public void onBuildTower3(ActionEvent event) {
        buildTower(3);
        buttonTower3.setDisable(true);
        buttonTower3.setText("Bought");
    }

    public void onBuildTower4(ActionEvent event) {
        buildTower(4);
        buttonTower4.setDisable(true);
        buttonTower4.setText("Bought");
    }

    public void onBuildTower5(ActionEvent event) {
        buildTower(5);
        buttonTower5.setDisable(true);
        buttonTower5.setText("Bought");
    }

    public void onBuildTower6(ActionEvent event) {
        buildTower(6);
        buttonTower6.setDisable(true);
        buttonTower6.setText("Bought");
    }

    private void buildTower(int towerIndex) {
        // TODO game.buildTower(towerIndex);
    }

}
