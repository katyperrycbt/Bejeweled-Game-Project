package Bejeweled.InGameFunctions;

import Bejeweled.SizeGameMenu;
import Gems.Matrix2D;

import static Bejeweled.InGame.checkBug;

public class DrawSmoothSwap {
    public static void drawSmoothSwap() {
        if (checkBug)
            for (int i = 0; i < SizeGameMenu.numOfRow; i++) {
                for (int j = 0; j < SizeGameMenu.numOfColumn; j++) {
                    if (Matrix2D.matrix[i][j].y > Matrix2D.gemLocation[i][j].y) {
                        Matrix2D.matrix[i][j].y -= 10;
                    }

                    if (Matrix2D.matrix[i][j].y < Matrix2D.gemLocation[i][j].y) {
                        Matrix2D.matrix[i][j].y += 10;
                    }

                    if (Matrix2D.matrix[i][j].x < Matrix2D.gemLocation[i][j].x) {
                        Matrix2D.matrix[i][j].x += 10;
                    }

                    if (Matrix2D.matrix[i][j].x > Matrix2D.gemLocation[i][j].x) {
                        Matrix2D.matrix[i][j].x -= 10;
                    }
                }
            }
    }
}
