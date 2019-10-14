package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application{

    private Stage primaryStage;
    private AnchorPane pane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        startWindow();
    }

    public void startWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/startView.fxml"));
            pane = loader.load();
            Scene scene = new Scene(pane);
            StartController startController = loader.getController();
            startController.setMain(this);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Unable to load file. Check file path.");
            e.printStackTrace();
        }
    }

    public void gameWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/gameView.fxml"));
            pane = loader.load();
            Scene scene = new Scene(pane);
            GameController gameController = loader.getController();
            gameController.setMain(this);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
