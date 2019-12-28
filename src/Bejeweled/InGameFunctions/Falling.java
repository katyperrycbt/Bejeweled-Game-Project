package Bejeweled.InGameFunctions;

import Bejeweled.SizeGameMenu;
import Gems.Gem;
import Gems.Matrix2D;

import static Bejeweled.InGame.checkBug;
import static Bejeweled.InGame.temporaryGemsType;

public class Falling {
    public static boolean falling() {
        if (!checkBug) {
            boolean repeat = false;
            for (int i = 0; i < SizeGameMenu.numOfRow; i++) {
                for (int j = 0; j < SizeGameMenu.numOfColumn; j++) {
                    if (Matrix2D.matrix[i][j].typeGem != 10) {
                        if ((i + 1) < SizeGameMenu.numOfRow) {
                            if (Matrix2D.matrix[i + 1][j].typeGem == 10) {

                                temporaryGemsType = Matrix2D.matrix[i][j].typeGem;
                                Matrix2D.matrix[i][j].typeGem = Matrix2D.matrix[i + 1][j].typeGem;
                                Matrix2D.matrix[i + 1][j].typeGem = temporaryGemsType;

                                Matrix2D.matrix[i + 1][j].x = Matrix2D.matrix[i][j].x;
                                Matrix2D.matrix[i + 1][j].isFalling = true;

                                repeat = true;
                                Matrix2D.showMatrix2D();

                            }
                        }
                    }
                }

            }


            for (int i = 0; i < SizeGameMenu.numOfRow; i++) {
                for (int j = 0; j < SizeGameMenu.numOfColumn; j++) {
                    if (Matrix2D.matrix[i][j].typeGem == 10) {
                        if (i == 0) {
                            Matrix2D.matrix[i][j].x = 70;
                            Matrix2D.matrix[i][j].isFalling = true;

                            Matrix2D.matrix[i][j].typeGem = Gem.getTypeGem();
                            System.out.println("Add new random gem at " + i + " " + j);

                            repeat = true;
                            Matrix2D.showMatrix2D();
                        }

                    }
                }
            }

            if (repeat) {
                falling();

            }

            System.out.println("Falling!");

        }
        return true;
    }
}
