package ch.zhaw.team5;

import java.io.IOException;
import java.util.ArrayList;

import org.checkerframework.checker.units.qual.g;

import ch.zhaw.team5.model.Player;
import ch.zhaw.team5.model.Tower;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartScreenController {
    @FXML
    public Button startButton;
    @FXML
    public Button quitButton;

    public void onStartButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader gameViewLoader = new FXMLLoader(getClass().getResource("GameView.fxml"));
        Parent root = (Parent) gameViewLoader.load();

        Stage gameStage = new Stage();
        gameStage.setTitle("Tower Defense");
        gameStage.setScene(new Scene(root));
        gameStage.setResizable(false);
        gameStage.show();

        GameViewController gameViewController = gameViewLoader.getController();
        gameViewController.initializeListeners(new Player(100, 50, new ArrayList<Tower>()), gameStage);

      


        Stage currentStage = (Stage) quitButton.getScene().getWindow();
        currentStage.close();
    }

    public void onQuitButtonClicked(ActionEvent event) {
        System.exit(0);
    }
}
