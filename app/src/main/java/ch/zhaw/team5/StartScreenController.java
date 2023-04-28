package ch.zhaw.team5;

import java.io.IOException;

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
        FXMLLoader resultWindowLoader = new FXMLLoader(getClass().getResource("GameView.fxml"));
        Parent root = (Parent) resultWindowLoader.load();

        Stage gameStage = new Stage();
        gameStage.setTitle("Tower Defense");
        gameStage.setScene(new Scene(root));
        gameStage.setResizable(false);
        gameStage.show();

        Stage currentStage = (Stage) quitButton.getScene().getWindow();
        currentStage.close();
    }

    public void onQuitButtonClicked(ActionEvent event) {
        System.exit(0);
    }
}
