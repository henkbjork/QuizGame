package modell;

import java.io.Serializable;

public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void changeScore() {
        score += 10;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Score: " + score;
    }
}
