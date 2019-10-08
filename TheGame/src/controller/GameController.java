package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import modell.*;
import java.net.URL;
import java.util.*;

public class GameController implements Initializable {

    @FXML private Label questionLabel, answerA, answerB, answerC, answerD, player1Label, player2Label, player3Label, player4Label, player5Label, player6Label;
    private Main main;
    private List<Label> playerLabels;
    private List<Player> players;
    private String correctAnswer;
    private int index = 0;

     void setMain(Main main) {
         this.main = main;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         players = MultiPlayerGame.getPlayers();
         playerLabels = new ArrayList<>();
         playerLabels.add(player1Label);
         playerLabels.add(player2Label);
         playerLabels.add(player3Label);
         playerLabels.add(player4Label);
         playerLabels.add(player5Label);
         playerLabels.add(player6Label);
         setPlayerNameAndScore();
    }

    @FXML
    void handleFiftyFiftyButton() {

    }

    @FXML
    void handleQuitButton() {
        main.startWindow();
    }

    @FXML
    void handleSaveButton() {

    }

    private void playerTurn() {

    }

    @FXML
    private void setPlayerNameAndScore() {
         for(int i=0; i<players.size(); i++) {
            playerLabels.get(i).setText(players.get(i).getName() + ": " + players.get(i).getScore());
         }
    }

    private void updateScore() {
         players.get(0).changeScore();
         setPlayerNameAndScore();
    }

    @FXML
    public void handleNewQuestion() {
         setLabelStyleToNormal();
         changePlayerTurn();
         List<String> questionAndAnswer = QuestionAndAnswer.getQuestionAndAnswer();
         correctAnswer = questionAndAnswer.get(1);
         questionLabel.setText(questionAndAnswer.get(0));
         Label[] labels = new Label[4];
         labels[0] = answerA;
         labels[1] = answerB;
         labels[2] = answerC;
         labels[3] = answerD;

         questionAndAnswer.remove(0); //deleting the question so just the answers remain in the list
         List<String> randomQuestion = shuffleArray(questionAndAnswer);
         for(int i=0; i<labels.length; i++) {
             labels[i].setText(randomQuestion.get(i));
         }
    }

    private void changePlayerTurn() {
        System.out.println("playerlabel size: " + playerLabels.size() + ", and index: " + index);
         playerLabels.get(index).setUnderline(true);
         index++;
         if(index >= players.size()) {
             index = 0;
         }
    }

    private void setLabelStyleToNormal() {
         answerA.setStyle(null);
         answerB.setStyle(null);
         answerC.setStyle(null);
         answerD.setStyle(null);
         player1Label.setUnderline(false);
         player2Label.setUnderline(false);
         player3Label.setUnderline(false);
         player4Label.setUnderline(false);
         player5Label.setUnderline(false);
         player6Label.setUnderline(false);
    }

    private List<String> shuffleArray(List<String> arrList){
        Collections.shuffle(arrList);
        return arrList;
    }

    @FXML
    public void handleAnswerClick(MouseEvent event) {
        if(event.toString().contains(correctAnswer)) {
            updateScore();
            handleShowAnswer();
        } else {
            Label label = (Label) event.getSource();
            label.setStyle("-fx-background-color: #da4a3b");
            handleShowAnswer();
        }
    }

    @FXML
    private void handleShowAnswer() {
        if(answerA.toString().contains(correctAnswer)) {
            answerA.setStyle("-fx-background-color: #bdf27e");
        } else if(answerB.toString().contains(correctAnswer)) {
            answerB.setStyle("-fx-background-color: #bdf27e");
        } else if(answerC.toString().contains(correctAnswer)) {
            answerC.setStyle("-fx-background-color: #bdf27e");
        } else if(answerD.toString().contains(correctAnswer)) {
            answerD.setStyle("-fx-background-color: #bdf27e");
        }
    }
}
