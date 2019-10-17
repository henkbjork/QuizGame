package modell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MultiPlayerGame implements Serializable {

    private static final long serialVersionUID = 3L;
    private static List<QuizPlayer> quizPlayers;
    private static String difficulty;
    private static String category;

    public MultiPlayerGame() {
        quizPlayers = new ArrayList<>();
    }

    public MultiPlayerGame(List<QuizPlayer> quizPlayers, String category, String difficulty) {
        MultiPlayerGame.quizPlayers = quizPlayers;
        MultiPlayerGame.category = category;
        MultiPlayerGame.difficulty = difficulty;
    }

    public void addPlayer(String name) {
        quizPlayers.add(new QuizPlayer(name));
    }

    public void addPlayer(String name, int score) {
        quizPlayers.add(new QuizPlayer(name, score));
    }

    public void setDifficulty(String str) {
        difficulty = str;
    }

    public void setCategory(String str) {
        category = str;
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public static String getCategory() {
        return category;
    }

    public static List<QuizPlayer> getQuizPlayers() {
        return quizPlayers;
    }
}
