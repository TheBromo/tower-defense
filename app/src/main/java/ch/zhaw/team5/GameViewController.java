package ch.zhaw.team5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import ch.zhaw.team5.model.Game;
import ch.zhaw.team5.model.Player;
import ch.zhaw.team5.model.Wall;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GameViewController {

    private GameState playerDecorator;
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

    public void initializeListeners(Player player, Stage parent) {
        GameState gameState = new GameState(player);
        this.game = new Game(player, gameState, canvas);
        gameState.moneyProperty().addListener((observable, oldValue, newValue) -> {
            moneyLabel.setText(newValue + "$");
        });
        gameState.healthProperty().addListener((observable, oldValue, newValue) -> {
            healthBar.setProgress((double) newValue);
        });
        gameState.setHealth(player.getHealth());
        gameState.setMoney(player.getMoney());
        gameState.renderNeededProperty().addListener((observable, oldValue, newValue) -> {
            Semaphore semaphore = new Semaphore(0);
            Platform.runLater(() -> {
                if (newValue) {
                    game.render(canvas);
                }
                semaphore.release();
            });
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        gameThread = Executors.newCachedThreadPool();
        gameThread.submit(() -> game.loop());

        parent.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                gameThread.shutdown();
                Platform.exit();
                System.exit(0);
            }
        });

    }

    public void onBuildTower(ActionEvent event) {
        Button pressedButton = (Button) event.getSource();

        switch (pressedButton.getId()) {
            case "buttonTower1" -> game.buildTower(1);
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
