package ch.zhaw.team5;

import ch.zhaw.team5.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class GameViewController {

    private PlayerDecorator playerDecorator;
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

    public void setPlayerModel(Player player) {
        playerDecorator = new PlayerDecorator(player);
        playerDecorator.addListener(new IsObserver() {
            @Override
            public void update() {
                moneyLabel.setText(String.valueOf(player.getMoney()) + "$");
            }
        });
        playerDecorator.addListener(new IsObserver() {
            @Override
            public void update() {
                healthBar.setProgress(Double.valueOf(player.getHealth()));
            }
        });
        playerDecorator.initialize();
    }

    public void setGameDecorator() {
        // TODO initialize as soon as game is ready
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
