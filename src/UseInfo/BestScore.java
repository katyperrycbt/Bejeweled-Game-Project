package UserInfo;

import java.io.Serializable;

public class BestScore implements Serializable {
    private static final long serialVersionUID = 1L;
    private int score;

    public BestScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
