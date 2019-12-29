package Bejeweled.InGameFunctions;

import Gems.Matrix2D;

import static Bejeweled.InGame.gameOver;
import static Bejeweled.InGame.isMatching;
import static Bejeweled.InGame.xName;
import static Bejeweled.InGame.yName;
import static Bejeweled.InGame.matrix2D;


public class ResetWhenGameOver {
    public static void resetWhenGameIsOver() {
        if (gameOver) {
            isMatching = false;
            xName = 0;
            yName = 118;
            matrix2D = new Matrix2D();
            gameOver = false;
        }
    }
}
