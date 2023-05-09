package ch.zhaw.team5.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import ch.zhaw.team5.GameState;
import ch.zhaw.team5.model.Game;
import ch.zhaw.team5.model.Player;
import ch.zhaw.team5.model.gameobj.Wall;
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
    private Wall wall;
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

    private boolean tower1built = false;
    private boolean tower2built = false;
    private boolean tower3built = false;
    private boolean tower4built = false;
    private boolean tower5built = false;
    private boolean tower6built = false;

    public void initializeListeners(Player player, Stage parent) {
        gameState = new GameState(player);
        this.game = new Game(player, gameState, canvas);
        initGameState(player, gameState);

        gameThread = Executors.newCachedThreadPool();
        gameThread.submit(() -> game.loop());

        setOnClose(parent);
    }

    private void initGameState(Player player, GameState gameState) {
        gameState.moneyProperty().addListener((observable, oldValue, newValue) -> {
            moneyLabel.setText(newValue + "$");
        });
        gameState.healthProperty().addListener((observable, oldValue, newValue) -> {
            healthBar.setProgress((double) newValue);
        });
        gameState.setHealth(player.getHealth());
        gameState.setMoney(player.getMoney());
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
            gameThread.shutdown();
            Platform.exit();
            System.exit(0);
        });
    }

    public void onBuildTower(ActionEvent event) {
        Button pressedButton = (Button) event.getSource();

        switch (pressedButton.getId()) {
            case "buttonTower1" -> {
                if (!tower1built) {
                    game.buildTower(1);
                    tower1built = true;
                    pressedButton.setText("Upgrade Tower 1");
                } else {
                    game.upgradeTower(1);
                    pressedButton.setText("Built");
                    pressedButton.setDisable(true);
                }
            }
            case "buttonTower2" -> {
                if (!tower2built) {
                    game.buildTower(2);
                    tower2built = true;
                    pressedButton.setText("Upgrade Tower 2");
                } else {
                    game.upgradeTower(2);
                    pressedButton.setText("Built");
                    pressedButton.setDisable(true);
                }
            }
            case "buttonTower3" -> {
                if (!tower3built) {
                    game.buildTower(3);
                    tower3built = true;
                    pressedButton.setText("Upgrade Tower 3");
                } else {
                    game.upgradeTower(3);
                    pressedButton.setText("Built");
                    pressedButton.setDisable(true);
                }
            }
            case "buttonTower4" -> {
                if (!tower4built) {
                    game.buildTower(4);
                    tower4built = true;
                    pressedButton.setText("Upgrade Tower 4");
                } else {
                    game.upgradeTower(4);
                    pressedButton.setText("Built");
                    pressedButton.setDisable(true);

                }
            }
            case "buttonTower5" -> {
                if (!tower5built) {
                    game.buildTower(5);
                    tower5built = true;
                    pressedButton.setText("Upgrade Tower 5");
                } else {
                    game.upgradeTower(5);
                    pressedButton.setText("Built");
                    pressedButton.setDisable(true);

                }
            }
            case "buttonTower6" -> {
                if (!tower6built) {
                    game.buildTower(6);
                    tower6built = true;
                    pressedButton.setText("Upgrade Tower 6");
                } else {
                    game.upgradeTower(6);
                    pressedButton.setText("Built");
                    pressedButton.setDisable(true);
                }
            }
            default -> {
                System.err.println("unknown action");
            }
        }
    }

    public void onBuyHealth() {
        gameState.setBuyHealth();

    }
}
