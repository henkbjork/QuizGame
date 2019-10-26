package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import modell.FileManager;
import modell.MultiPlayerGame;

import java.util.Objects;

public class StartController {

    @FXML private TextField playerNameField;
    @FXML private RadioButton easy, medium, hard, mixed, sport, geography, science;
    @FXML private ToggleGroup difficultyGroup, categoryGroup;
    private Main main;
    private MultiPlayerGame multiPlayerGame = new MultiPlayerGame();

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    public void handleAddPlayer() {
        if(MultiPlayerGame.getQuizPlayers().size() < 6) {
            multiPlayerGame.addPlayer(playerNameField.getText());
            playerNameField.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Max 6 players");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleKeyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            handleAddPlayer();
        }
    }

    @FXML
    public void handleDifficulty() {
        String choice = "easy";
        if(difficultyGroup.getSelectedToggle().equals(easy)) {
            choice = "easy";
        } else if(difficultyGroup.getSelectedToggle().equals(medium)) {
            choice = "medium";
        } else if(difficultyGroup.getSelectedToggle().equals(hard)) {
            choice = "hard";
        }
        multiPlayerGame.setDifficulty(choice);
    }

    @FXML
    public void handleCategory() {
        String choice = "";
        if(categoryGroup.getSelectedToggle().equals(mixed)) {
            choice = "";
        } else if(categoryGroup.getSelectedToggle().equals(sport)) {
            choice = "21";
        } else if(categoryGroup.getSelectedToggle().equals(geography)) {
            choice = "22";
        } else if(categoryGroup.getSelectedToggle().equals(science)) {
            choice = "17";
        }
        multiPlayerGame.setCategory(choice);
    }

    @FXML
    public void handleStartGameButton() {
        main.gameWindow();
    }

    @FXML
    public void handleLoadGameButton() {
        new MultiPlayerGame(Objects.requireNonNull(FileManager.loadFromFile()));
        multiPlayerGame.setDifficulty(MultiPlayerGame.getDifficulty());
        multiPlayerGame.setCategory(MultiPlayerGame.getCategory());
        main.gameWindow();
    }

    @FXML
    void handleMenuLoadGame() {
        handleLoadGameButton();
    }

    @FXML
    void handleMenuRules() {
        main.rulesWindow();
    }

    @FXML
    void handleMenuExit() {
        Platform.exit();
        System.exit(0);
    }
}
