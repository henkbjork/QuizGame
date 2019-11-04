package modell;

import java.io.Serializable;

public class QuizPlayer extends Player implements Serializable {

    private static final long serialVersionUID = 1L;

    public QuizPlayer() {

    }

    public QuizPlayer(String name) {
        super(name, 0);
    }

    @Override
    public String toString() {
        return "Name: " + super.getName() + ", Score: " + super.getScore();
    }
}
