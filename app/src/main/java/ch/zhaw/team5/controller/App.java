package ch.zhaw.team5.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class represents the main application for a Tower Defense game.
 * It extends the JavaFX Application class to create a JavaFX application.
 * @author kuengpas
 * @version 1.0.0
 */
public class App extends Application {

    /**
     * This method is overridden from Application class. 
     * It is the main entry point for all JavaFX applications.
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set. 
     * @throws Exception if something goes wrong, like an issue with loading the FXML layout.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("StartScreen.fxml"));
        primaryStage.setTitle("Tower Defense");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    /**
     * The main method which launches the JavaFX application.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
