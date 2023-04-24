package ch.zhaw.team5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartScreenController {
    @FXML
    public Button startButton;
    @FXML
    public Button quitButton;

    @FXML
    private void initialize(){
        quitButton.setOnAction(this::onQuitButtonClicked);
        startButton.setOnAction(this::onStartButtonClicked);
    }

    public void onStartButtonClicked (ActionEvent event) {
        // TODO: Hier kommt dann Logik von Micha
    }

    public void onQuitButtonClicked (ActionEvent event) {
        System.exit(0);
    }
}
