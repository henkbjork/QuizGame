package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class WindowManager extends Application {

    private Stage primaryStage;

    public void run() {
        launch(null);
    }

    @Override
    public void start(Stage stage){
        this.primaryStage = stage;
        stage.setTitle("The Game - A quiz game");
        primaryStage.setResizable(false);
        startWindow();
    }

    public void startWindow() {
        try {
            FXMLLoader loader = loader(new FXMLLoader(Main.class.getResource("/view/startView.fxml")));
            StartController startController = loader.getController();
            startController.setWindowManager(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gameWindow() {
        try {
            FXMLLoader loader = loader(new FXMLLoader(Main.class.getResource("/view/gameView.fxml")));
            GameController gameController = loader.getController();
            gameController.setWindowManager(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void winnerWindow() {
        try {
            FXMLLoader loader = loader(new FXMLLoader(Main.class.getResource("/view/winnerView.fxml")));
            WinnerController winnerController = loader.getController();
            winnerController.setWindowManager(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rulesWindow() {
        try {
            FXMLLoader loader = loader(new FXMLLoader(Main.class.getResource("/view/rulesView.fxml")));
            RulesController rulesController = loader.getController();
            rulesController.setWindowManager(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FXMLLoader loader(FXMLLoader loader) throws IOException {
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
        return loader;
    }
}
