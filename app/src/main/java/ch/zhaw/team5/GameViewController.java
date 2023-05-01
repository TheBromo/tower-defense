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

    public void onBuildTower(ActionEvent event) {
        Button pressedButton = (Button) event.getSource();

        switch (pressedButton.getId()) {
            case "buttonTower1" -> System.out.println("here will tower 1 be built"); // TODO game.buildTower(1);
            case "buttonTower2" -> System.out.println("here will tower 2 be built"); // TODO game.buildTower(2);
            case "buttonTower3" -> System.out.println("here will tower 3 be built"); // TODO game.buildTower(3);
            case "buttonTower4" -> System.out.println("here will tower 4 be built"); // TODO game.buildTower(4);
            case "buttonTower5" -> System.out.println("here will tower 5 be built"); // TODO game.buildTower(5);
            case "buttonTower6" -> System.out.println("here will tower 6 be built"); // TODO game.buildTower(6);
        }

        pressedButton.setDisable(true);
        pressedButton.setText("Bought");
    }

}
