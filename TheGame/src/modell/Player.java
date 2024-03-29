package modell;

import java.io.Serializable;

public class Player implements Serializable {

    private static final long serialVersionUID = 2L;
    private int score;
    private String name;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void changeScore(boolean fiftyFiftyButton) {
        if (fiftyFiftyButton) {
            score += 5;
        } else {
            score += 10;
        }
    }
}
