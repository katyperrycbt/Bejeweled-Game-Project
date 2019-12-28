package Bejeweled.InGameFunctions;

import Bejeweled.SizeGameMenu;
import Gems.Matrix2D;

import static Bejeweled.InGame.*;

public class IfGemIsClicked {
    public static boolean detectIfGemIsClicked() {
        for (int i = 0; i <= SizeGameMenu.numOfRow; i++) {
            for (int j = 0; j < SizeGameMenu.numOfColumn; j++) {
                if (isLeftMouseClicked) {
                    if (xMouse > 95 + 80 * j && xMouse < 95 + 80 + 80 * j && yMouse > 150 + 80 * i && yMouse < 230 + 80 * i) {
                        Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].isGemActive = false;
                        Matrix2D.matrix[i][j].isGemActive = true;
                        matrix2D.isMatrix2DActive = true;
                        matrix2D.activeX = i;
                        matrix2D.activeY = j;
                        System.out.println("Active : x = " + matrix2D.activeX + "," + " y  = " + matrix2D.activeY + "  typeCandy: " +
                                Matrix2D.matrix[matrix2D.activeX][matrix2D.activeY].typeGem);
                        System.out.println("Gem at x = " + Matrix2D.matrix[i][j].y + " , y = " + Matrix2D.matrix[i][j].x + " is clicked");
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
