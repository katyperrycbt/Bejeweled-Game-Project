package Bejeweled.InGameFunctions;

import Bejeweled.SizeGameMenu;
import Gems.Matrix2D;

import static Bejeweled.InGame.checkBug;

public class TestFalling {
    public static void testFalling() {
        if (!checkBug) {
            for (int i = 0; i < SizeGameMenu.numOfRow; i++) {
                for (int j = 0; j < SizeGameMenu.numOfColumn; j++) {
                    if (Matrix2D.matrix[i][j].isFalling) {
                        if (Matrix2D.matrix[i][j].x < Matrix2D.gemLocation[i][j].x) {
                            Matrix2D.matrix[i][j].x += 20;
                        } else if (Matrix2D.matrix[i][j].x == Matrix2D.gemLocation[i][j].x) {
                            Matrix2D.matrix[i][j].isFalling = false;
                        }
                    }
                }
            }
        }
    }
}
