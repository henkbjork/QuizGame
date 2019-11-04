package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class WindowManager extends Application {

    private Stage primaryStage;
    private AnchorPane pane;

    @Override
    public void start(Stage stage){
        this.primaryStage = stage;
        stage.setTitle("The Game - A quiz game");
        primaryStage.setResizable(false);
        startWindow();
    }

    public void run() {
        launch(null);
    }

    public void startWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/startView.fxml"));
            pane = loader.load();
            Scene scene = new Scene(pane);
            StartController startController = loader.getController();
            startController.setWindowManager(this);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gameWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/gameView.fxml"));
            pane = loader.load();
            Scene scene = new Scene(pane);
            GameController gameController = loader.getController();
            gameController.setWindowManager(this);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void winnerWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/winnerView.fxml"));
            pane = loader.load();
            Scene scene = new Scene(pane);
            WinnerController winnerController = loader.getController();
            winnerController.setWindowManager(this);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rulesWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/rulesView.fxml"));
            pane = loader.load();
            Scene scene = new Scene(pane);
            RulesController rulesController = loader.getController();
            rulesController.setWindowManager(this);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
