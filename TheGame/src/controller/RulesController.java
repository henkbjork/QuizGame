package controller;

import javafx.fxml.FXML;
import modell.MultiPlayerGame;

public class RulesController {

    private WindowManager windowManager;

    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    @FXML
    public void handleGoBackButton() {
        if(MultiPlayerGame.getQuizPlayers().size() > 0) {
            windowManager.gameWindow();
        } else {
            windowManager.startWindow();
        }
    }
}
