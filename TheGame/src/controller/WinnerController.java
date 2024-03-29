package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import modell.MultiPlayerGame;
import modell.QuizPlayer;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class WinnerController implements Initializable {

    @FXML private Label winnerLabel;
    private WindowManager windowManager;


    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<QuizPlayer> quizPlayers = MultiPlayerGame.getQuizPlayers();
        for(QuizPlayer player : quizPlayers) {
            if(player.getScore() >= 50) {
                winnerLabel.setText(player.getName());
            }
        }
    }

    @FXML
    public void handleNewGameButton() {
        windowManager.startWindow();
    }

    @FXML
    public void handleExitButton() {
        Platform.exit();
        System.exit(0);
    }
}