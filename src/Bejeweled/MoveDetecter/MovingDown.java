package Bejeweled.MoveDetecter;

import Bejeweled.SizeGameMenu;
import Gems.Matrix2D;

import static Bejeweled.InGame.*;

public class MovingDown {
    public static boolean move() {
        if (matrix2D.isMatrix2DActive) {
            if (matrix2D.activeX + 1 <= SizeGameMenu.numOfRow - 1) {
                // We check if the lower gem is falling or not
                if (Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].typeGem != 10 && Matrix2D.matrix[matrix2D.activeX + 1][matrix2D.activeY].typeGem != 10) {
                    // If mouse hit the lower Gem
                    if (yMouse > Matrix2D.matrix[matrix2D.activeX + 1][matrix2D.activeY].x && yMouse < (Matrix2D.matrix[matrix2D.activeX + 1][matrix2D.activeY].x + 80)
                            && xMouse > Matrix2D.matrix[matrix2D.activeX + 1][matrix2D.activeY].y && xMouse < Matrix2D.matrix[matrix2D.activeX + 1][matrix2D.activeY].y + 80) {

                        //Remove 'action' state to swap
                        matrix2D.isMatrix2DActive = false;
                        Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].isGemActive = false;

                        //Swap
                        temporaryGemsType = Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].typeGem;
                        Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].typeGem = Matrix2D.matrix[matrix2D.activeX + 1][matrix2D.activeY].typeGem;
                        Matrix2D.matrix[matrix2D.activeX + 1][matrix2D.activeY].typeGem = temporaryGemsType;

                        Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].x += 80;
                        Matrix2D.matrix[matrix2D.activeX + 1][matrix2D.activeY].x -= 80;

                        matrix2D.notDetectDown = true;

                        System.out.println("After swap: ");
                        Matrix2D.showMatrix2D();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
