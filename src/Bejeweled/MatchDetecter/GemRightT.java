package Bejeweled.MatchDetecter;

import Bejeweled.InGame;
import Bejeweled.SizeGameMenu;
import Gems.Matrix2D;

import java.util.Random;

import static Bejeweled.InGame.isMatching;
import static Bejeweled.InGame.score;

public class GemRightT {
    static Random random = new Random();

    public static boolean match() {
        for (int i = 0; i < SizeGameMenu.numOfColumn; i++) {
            for (int j = 0; j < SizeGameMenu.numOfRow; j++) {
                if (Matrix2D.matrix[i][j].typeGem != 10) {
                    if ((j + 1) < SizeGameMenu.numOfColumn && (j - 1) >= 0 && (i - 2) >= 0) {
                        if (Matrix2D.matrix[i][j].typeGem == Matrix2D.matrix[i][j + 1].typeGem && Matrix2D.matrix[i][j].typeGem == Matrix2D.matrix[i][j - 1].typeGem
                                && Matrix2D.matrix[i][j].typeGem == Matrix2D.matrix[i - 1][j].typeGem && Matrix2D.matrix[i][j].typeGem == Matrix2D.matrix[i - 2][j].typeGem) {
                            InGame.addText(random.nextFloat() * 810, random.nextFloat() * (894 - 150) + 150, Matrix2D.matrix[i][j].typeGem, 5, "+70 Points", 7);
                            InGame.addText(500, 50, Matrix2D.matrix[i][j].typeGem, 1, "+05 Seconds", 0);
                            for (int k = 0; k < 3; k++) {
                                Matrix2D.matrix[i][j + k - 1].typeGem = 10;
                                Matrix2D.matrix[i - k][j].typeGem = 10;
                            }
                            System.out.println("Find Gem T-Right at " + i + " " + j);
                            isMatching = true;
                            score += 70;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
