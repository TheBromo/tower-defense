package ch.zhaw.team5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;

public class GameViewController {

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


    
    public void onBuildTower1(ActionEvent event) {
        buildTower(1);
        buttonTower1.setDisable(true);
        buttonTower1.setText("Bought");
    }

    public void onBuildTower2(ActionEvent event) {
        buildTower(2);
        buttonTower2.setDisable(true);
        buttonTower2.setText("Bought");
    }

    public void onBuildTower3(ActionEvent event) {
        buildTower(3);
        buttonTower3.setDisable(true);
        buttonTower3.setText("Bought");
    }

    public void onBuildTower4(ActionEvent event) {
        buildTower(4);
        buttonTower4.setDisable(true);
        buttonTower4.setText("Bought");
    }

    public void onBuildTower5(ActionEvent event) {
        buildTower(5);
        buttonTower5.setDisable(true);
        buttonTower5.setText("Bought");
    }

    public void onBuildTower6(ActionEvent event) {
        buildTower(6);
        buttonTower6.setDisable(true);
        buttonTower6.setText("Bought");
    }

    private void buildTower(int towerIndex) {
        //TODO game.buildTower(towerIndex);
    }
    

}
