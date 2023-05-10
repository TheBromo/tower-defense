package ch.zhaw.team5.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import ch.zhaw.team5.GameState;
import ch.zhaw.team5.model.Game;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class GameViewController {

    private GameState gameState;
    private Game game;
    private ExecutorService gameThread;
    // private GameDecorator gameDecorator;

    @FXML
    private Label moneyLabel;
    @FXML
    private Canvas canvas;
    @FXML
    private ProgressBar healthBar;
    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button buyHealthButton;

    public void initializeListeners(Stage parent) {
        gameState = new GameState();
        this.game = new Game(gameState, canvas);
        initGameState(gameState);

        gameThread = Executors.newCachedThreadPool();
        gameThread.submit(() -> game.loop());

        setOnClose(parent);
    }

    private void initGameState(GameState gameState) {
        gameState.moneyProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> moneyLabel.setText(newValue + "$"));
        });
        gameState.healthProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println((double) newValue / 100);
            System.out.println(newValue);
            Platform.runLater(() -> healthBar.setProgress((double) newValue / 100));
        });
        gameState.gameEndProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("we looosing !!!!!" + newValue);
            if (newValue == true) {
                closeGame();
            }
        });
        gameState.renderNeededProperty().addListener(render());
    }

    private ChangeListener<Boolean> render() {
        // Sephamore is used so the game thread only runs after rendering (used for not
        // overusing the game thread)
        return (observable, oldValue, renderNeeded) -> {
            Semaphore semaphore = new Semaphore(0);
            Platform.runLater(() -> {
                if (renderNeeded) {
                    game.render(canvas);
                }
                semaphore.release();
            });
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    private void setOnClose(Stage parent) {
        parent.setOnCloseRequest(event -> {
            closeGame();
        });
    }

    private void closeGame() {
        gameThread.shutdown();
        Platform.exit();
        System.exit(0);
    }

    public void onBuildTower(ActionEvent event) {
        Button pressedButton = (Button) event.getSource();

        switch (pressedButton.getId()) {
            case "buttonTower1" -> {
                buildOrUpgradeTower(1, pressedButton);
            }
            case "buttonTower2" -> {
                buildOrUpgradeTower(2, pressedButton);
            }
            case "buttonTower3" -> {
                buildOrUpgradeTower(3, pressedButton);
            }
            case "buttonTower4" -> {
                buildOrUpgradeTower(4, pressedButton);
            }
            case "buttonTower5" -> {
                buildOrUpgradeTower(5, pressedButton);
            }
            case "buttonTower6" -> {
                buildOrUpgradeTower(6, pressedButton);
            }
            default -> {
                System.err.println("unknown action");
            }
        }
    }

    private void buildOrUpgradeTower(int id, Button pressedButton) {
        if (game.canUpgradeOrBuildTower(id)) {
            game.buildOrUpgradeTower(id);
            pressedButton.setText("Upgrade Tower " + id + " (" + GameState.upgradeTowerCost + "$)");
            pressedButton.setDisable(!game.canUpgradeOrBuildTower(id));
        }
    }

    public void onBuyHealth() {
        gameState.buyHealth();
    }
}
