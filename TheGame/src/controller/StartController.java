package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import modell.MultiPlayerGame;
import modell.SaveAndLoad;

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
        if(MultiPlayerGame.getPlayers().size() < 6) {
            multiPlayerGame.addPlayer(playerNameField.getText());
            playerNameField.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Max 6 players");
            alert.showAndWait();
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
        SaveAndLoad.loadFile();
    }
}
