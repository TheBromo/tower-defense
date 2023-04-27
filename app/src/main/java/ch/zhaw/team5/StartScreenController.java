package ch.zhaw.team5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScreenController {
    @FXML
    public Button startButton;
    @FXML
    public Button quitButton;

    public void onStartButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader resultWindowLoader = FXMLLoader.load(getClass().getResource("GameWindow.fxml"));
        Parent root1 = (Parent) resultWindowLoader.load();

        GameViewController controller = resultWindowLoader.getController();
        Stage stage = new Stage();
        stage.setTitle("Result Window");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void onQuitButtonClicked(ActionEvent event) {
        System.exit(0);
    }
}
