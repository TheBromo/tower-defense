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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


/**
 * This class acts as a controller for the game view in a Tower Defense game.
 * @author strenman
 * @version 1.0.0
 */

public class GameViewController {

    private GameState gameState;
    private Game game;
    private ExecutorService gameThread;
    // private GameDecorator gameDecorator;

    @FXML
    private Label moneyLabel;
    @FXML
    private Label phaseLabel;
    @FXML
    private Canvas canvas;
    @FXML
    private ProgressBar healthBar;
    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button buyHealthButton;

    /**
     * Initializes game listeners and starts game loop.
     *
     * @param parent the parent stage of the application
     */

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
        gameState.gamePhaseNameProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> phaseLabel.setText(newValue));
        });
        gameState.healthProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> healthBar.setProgress((double) newValue / 100));
        });
        gameState.progressProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> progressBar.setProgress((double) newValue / 100));
        });
        gameState.gameEndProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                if (newValue == true) {
                    gameThread.shutdown();
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("You've lost the game");
                    alert.setHeaderText("Oops! it's seems like you've lost the Game");
                    alert.setContentText("Restart to try again :D");

                    alert.showAndWait();
                    closeGame();
                }
            });
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
    /**
     * Handles build tower actions based on the pressed button.
     *
     * @param event the ActionEvent to be handled
     */
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
            if (game.buildOrUpgradeTower(id)) {
                pressedButton.setText("Upgrade Tower " + id + " (" + GameState.upgradeTowerCost + "$)");
                pressedButton.setDisable(!game.canUpgradeOrBuildTower(id));
            }
        }
    }
    /**
     * Handles buy health action.
     */
    public void onBuyHealth() {

        gameState.buyHealth();
    }
}
