package Bejeweled.MoveDetecter;

import Gems.Matrix2D;

import static Bejeweled.InGame.*;

public class MovingLeft {
    public static boolean move() {
        if (matrix2D.isMatrix2DActive) {
            if (matrix2D.activeY - 1 >= 0) {
                // We check if the Left gem is falling or not
                if (Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].typeGem != 10 && Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY - 1].typeGem != 10) {
                    // If mouse hit the left Gem
                    if (yMouse > Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY - 1].x && yMouse < (Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY - 1].x + 80)
                            && xMouse > Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY - 1].y && xMouse < Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY - 1].y + 80) {

                        //Remove 'active' state to swap
                        matrix2D.isMatrix2DActive = false;
                        Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].isGemActive = false;
                        // Swap
                        temporaryGemsType = Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].typeGem;
                        Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].typeGem = Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY - 1].typeGem;
                        Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY - 1].typeGem = temporaryGemsType;

                        Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].y -= 80;
                        Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY - 1].y += 80;

                        matrix2D.notDetectLeft = true;

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
