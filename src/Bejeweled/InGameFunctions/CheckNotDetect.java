package Bejeweled.InGameFunctions;

import Gems.Matrix2D;

import static Bejeweled.InGame.matrix2D;
import static Bejeweled.InGame.temporaryGemsType;

public class CheckNotDetect {
    public static boolean checkIsNotDetected() {
        if (matrix2D.notDetectRight) {
            System.out.println("Right");
            temporaryGemsType = Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].typeGem;
            Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].typeGem = Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY + 1].typeGem;
            Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY + 1].typeGem = temporaryGemsType;

            matrix2D.notDetectRight = false;
        }

        if (matrix2D.notDetectLeft) {
            System.out.println("Left");
            temporaryGemsType = Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].typeGem;
            Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].typeGem = Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY - 1].typeGem;
            Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY - 1].typeGem = temporaryGemsType;

            matrix2D.notDetectLeft = false;
        }

        if (matrix2D.notDetectUp) {
            System.out.println("Up");
            temporaryGemsType = Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].typeGem;
            Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].typeGem = Matrix2D.matrix[matrix2D.activeX - 1][matrix2D.activeY].typeGem;
            Matrix2D.matrix[matrix2D.activeX - 1][matrix2D.activeY].typeGem = temporaryGemsType;

            matrix2D.notDetectUp = false;
        }

        if (matrix2D.notDetectDown) {
            System.out.println("Down");
            temporaryGemsType = Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].typeGem;
            Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].typeGem = Matrix2D.matrix[matrix2D.activeX + 1][matrix2D.activeY].typeGem;
            Matrix2D.matrix[matrix2D.activeX + 1][matrix2D.activeY].typeGem = temporaryGemsType;

            matrix2D.notDetectDown = false;
        }

        System.out.println("Not detect match!");
        matrix2D.showMatrix2D();
        return true;
    }
}
