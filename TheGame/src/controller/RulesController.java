package controller;

import javafx.fxml.FXML;

public class RulesController {

    private Main main;

    public Main setMain(Main main) {
        this.main = main;
        return main;
    }

    @FXML
    public void handleGoBackButton() {
        main.startWindow();
    }

}
