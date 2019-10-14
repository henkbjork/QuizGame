package modell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MultiPlayerGame extends Game implements Serializable {
    private static List<Player> players;
    private static String difficulty;
    private static String category;

    public MultiPlayerGame() {
        players = new ArrayList<>();
    }

    public void addPlayer(String name) {
        players.add(new Player(name));
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

    public static List<Player> getPlayers() {
        return players;
    }
}
