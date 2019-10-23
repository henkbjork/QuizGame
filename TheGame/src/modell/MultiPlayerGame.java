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

    public MultiPlayerGame(MultiPlayerGame multiPlayerGame) {
        quizPlayers = multiPlayerGame.getQuizPlayers();
        category = multiPlayerGame.getCategory();
        difficulty = multiPlayerGame.getDifficulty();
    }

    public MultiPlayerGame(List<QuizPlayer> loadedPlayers, String loadedCategory, String loadedDifficulty) {
        quizPlayers = loadedPlayers;
        category = loadedCategory;
        difficulty = loadedDifficulty;
    }

    public void addPlayer(String name) {
        quizPlayers.add(new QuizPlayer(name));
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
