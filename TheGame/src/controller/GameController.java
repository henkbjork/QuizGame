package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import modell.*;
import java.net.URL;
import java.util.*;

public class GameController implements Initializable {

    @FXML private Label questionLabel, answerA, answerB, answerC, answerD, player1Label, player2Label, player3Label, player4Label, player5Label, player6Label;
    @FXML private Button saveButton, fiftyFiftyBtn, newQuestionButton;
    private WindowManager windowManager;
    private static List<Label> playerLabels;
    private List<QuizPlayer> quizPlayers;
    private List<Label> answers;
    private String correctAnswer;
    private int playerLabelIndex;
    private boolean fiftyFiftyButton;

    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quizPlayers = MultiPlayerGame.getQuizPlayers();
        playerLabels = new ArrayList<>();
        answers = new ArrayList<>();
        playerLabels.add(player1Label);
        playerLabels.add(player2Label);
        playerLabels.add(player3Label);
        playerLabels.add(player4Label);
        playerLabels.add(player5Label);
        playerLabels.add(player6Label);
        answers.add(answerA);
        answers.add(answerB);
        answers.add(answerC);
        answers.add(answerD);
        playerLabelIndex = 0;
        fiftyFiftyButton = false;
        setPlayerNameAndScore();
    }

    @FXML
    private void setPlayerNameAndScore() {
        for (int i = 0; i < quizPlayers.size(); i++) {
            playerLabels.get(i).setText(quizPlayers.get(i).getName() + ": " + quizPlayers.get(i).getScore());
        }
    }

    private void updateScore() {
        quizPlayers.get(playerLabelIndex).changeScore(fiftyFiftyButton);
        if(quizPlayers.get(playerLabelIndex).getScore() >= 50) {
            windowManager.winnerWindow();
            disableButton();
        }
        setPlayerNameAndScore();
    }

    private void disableButton() {
        for(Label answer : answers) {
            answer.setDisable(true);
        }
        newQuestionButton.setDisable(true);
        fiftyFiftyBtn.setDisable(true);
        saveButton.setDisable(true);
    }

    @FXML
    public void handleNewQuestion() {
        setStyleToNormal();
        changePlayerTurn();
        for (Label answerLabel : answers) {
            answerLabel.setDisable(false);
        }
        List<String> questionAndAnswer = QuestionAndAnswer.getQuestionAndAnswer();
        correctAnswer = questionAndAnswer.get(1);
        questionLabel.setText(questionAndAnswer.get(0));

        displayPossibleAnswers(questionAndAnswer);
    }

    private void setStyleToNormal() {
        fiftyFiftyButton = false;
        for (Label answerLabel : answers) {
            answerLabel.setStyle(null);
            answerLabel.setVisible(true);
        }
        for (Label playerLabel : playerLabels) {
            playerLabel.setUnderline(false);
        }
    }

    private void displayPossibleAnswers(List<String> questionAndAnswer) {
        Label[] labels = new Label[4];
        labels[0] = answerA;
        labels[1] = answerB;
        labels[2] = answerC;
        labels[3] = answerD;

        questionAndAnswer.remove(0); //deleting the question so just the answers remain in the list

        List<String> randomQuestion = shuffleArray(questionAndAnswer);
        for (int i = 0; i < labels.length; i++) {
            labels[i].setText(randomQuestion.get(i));
        }
    }

    private List<String> shuffleArray(List<String> arrList) {
        Collections.shuffle(arrList);
        return arrList;
    }

    private void changePlayerTurn() {
        playerLabels.get(playerLabelIndex).setUnderline(true);
    }

    @FXML
    public void handleAnswerClick(MouseEvent event) {
        if (event.toString().contains(correctAnswer)) {
            updateScore();
            handleShowAnswer();
        } else {
            Label label = (Label) event.getSource();
            label.setStyle("-fx-background-color: #da4a3b; -fx-opacity: 1.0");
            handleShowAnswer();
        }
        for (Label answerLabel : answers) {
            answerLabel.setDisable(true);
        }
        changePlayerIndex();
    }

    private void changePlayerIndex() {
        playerLabelIndex++;
        if (playerLabelIndex >= quizPlayers.size()) {
            playerLabelIndex = 0;
        }
    }

    @FXML
    private void handleShowAnswer() {
        if (answerA.toString().contains(correctAnswer)) {
            answerA.setStyle("-fx-background-color: #bdf27e; -fx-opacity: 1.0");
        } else if (answerB.toString().contains(correctAnswer)) {
            answerB.setStyle("-fx-background-color: #bdf27e; -fx-opacity: 1.0");
        } else if (answerC.toString().contains(correctAnswer)) {
            answerC.setStyle("-fx-background-color: #bdf27e; -fx-opacity: 1.0");
        } else if (answerD.toString().contains(correctAnswer)) {
            answerD.setStyle("-fx-background-color: #bdf27e; -fx-opacity: 1.0");
        }
    }

    @FXML
    public void handleFiftyFiftyButton() {
        fiftyFiftyButton = true;
        Random random = new Random();
        Set<Integer> randomNumbers = new HashSet<>();
        while (randomNumbers.size() < 2) {
            int randomNumber = random.nextInt(4);
            if (!answers.get(randomNumber).toString().contains(correctAnswer)) {
                randomNumbers.add(randomNumber);
            }
        }
        randomNumbers.forEach(number -> answers.get(number).setVisible(false));
    }

    @FXML
    public void handleQuitButton() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("If you quit without saving your game will be lost.");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.OK) {
            windowManager.startWindow();
        }
    }

    @FXML
    public void handleSaveButton() {
        FileManager.saveToFile();
    }

    @FXML
    public void handleMenuSaveGame() {
        handleSaveButton();
    }

    @FXML
    public void handleMenuExit() {
        handleQuitButton();
    }

    @FXML
    public void handleMenuRules() {
        windowManager.rulesWindow();
    }

}
