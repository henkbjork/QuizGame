package modell;

import java.io.Serializable;

public class QuizPlayer extends Player implements Serializable {

    private static final long serialVersionUID = 1L;
    private int score;

    public QuizPlayer() {
    }

    public QuizPlayer(String name) {
        super(name);
    }

    public QuizPlayer(String name, int score) {
        super(name);
        this.score = score;
    }

    public void changeScore(boolean fiftyFiftyButton) {
        if (fiftyFiftyButton) {
            score += 5;
        } else {
            score += 10;
        }
    }

    @Override
    public int getScore() {
        return score;
    }
}
