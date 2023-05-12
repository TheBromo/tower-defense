package ch.zhaw.team5.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * This class acts as a controller for the start screen in the Tower Defense game.
 * @author kuengpas
 * @version 1.0.0
 */
public class StartScreenController {
    @FXML
    public Button startButton;
    @FXML
    public Button quitButton;

    /**
     * Handles the click event of the start button. This method will load the game
     * view, create a new stage for it, and then close the current stage.
     *
     * @param event the ActionEvent to be handled
     * @throws IOException if there's an error loading the FXML file
     */
    public void onStartButtonClicked(ActionEvent event) throws IOException {

        FXMLLoader gameViewLoader = new FXMLLoader(getClass().getResource("GameView.fxml"));
        Parent root = (Parent) gameViewLoader.load();

        Stage gameStage = new Stage();
        gameStage.setTitle("Tower Defense");
        gameStage.setScene(new Scene(root));
        gameStage.setResizable(false);
        gameStage.show();

        GameViewController gameViewController = gameViewLoader.getController();
        gameViewController.initializeListeners(gameStage);

        Stage currentStage = (Stage) quitButton.getScene().getWindow();
        currentStage.close();
    }

    /**
     * Handles the click event of the quit button. This method will exit the
     * application.
     *
     * @param event the ActionEvent to be handled
     */
    public void onQuitButtonClicked(ActionEvent event) {
        System.exit(0);
    }
}
