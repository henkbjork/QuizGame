package modell;

import java.io.Serializable;

public class QuizPlayer implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int score;

    public QuizPlayer() {
    }

    public QuizPlayer(String name) {
        this(name, 0);
    }

    public QuizPlayer(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public void changeScore(boolean fiftyFiftyButton) {
        if (fiftyFiftyButton) {
            score += 5;
        } else {
            score += 10;
        }
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Score: " + score;
    }
}
