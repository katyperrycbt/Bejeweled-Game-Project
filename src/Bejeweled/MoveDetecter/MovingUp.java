package Bejeweled.MoveDetecter;

import Gems.Matrix2D;

import static Bejeweled.InGame.*;

public class MovingUp {
    public static boolean move() {
        if (matrix2D.isMatrix2DActive) {
            if (matrix2D.activeX - 1 >= 0) {
                // We check if the upper gem is falling or not
                if (Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].typeGem != 10 && Matrix2D.matrix[matrix2D.activeX - 1][matrix2D.activeY].typeGem != 10) {
                    // If mouse hit the upper Gem
                    if (yMouse > Matrix2D.matrix[matrix2D.activeX - 1][matrix2D.activeY].x && yMouse < (Matrix2D.matrix[matrix2D.activeX - 1][matrix2D.activeY].x + 80)
                            && xMouse > Matrix2D.matrix[matrix2D.activeX - 1][matrix2D.activeY].y && xMouse < Matrix2D.matrix[matrix2D.activeX - 1][matrix2D.activeY].y + 80) {

                        //Remove 'action' state
                        matrix2D.isMatrix2DActive = false;
                        Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].isGemActive = false;

                        //Swap
                        temporaryGemsType = Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].typeGem;
                        Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].typeGem = Matrix2D.matrix[matrix2D.activeX - 1][matrix2D.activeY].typeGem;
                        Matrix2D.matrix[matrix2D.activeX - 1][matrix2D.activeY].typeGem = temporaryGemsType;

                        Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].x -= 80;
                        Matrix2D.matrix[matrix2D.activeX - 1][matrix2D.activeY].x += 80;

                        matrix2D.notDetectUp = true;

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
