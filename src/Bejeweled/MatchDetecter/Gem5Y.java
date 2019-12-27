package Bejeweled.MatchDetecter;

import Bejeweled.InGame;
import Bejeweled.SizeGameMenu;
import Gems.Matrix2D;

import java.util.Random;

import static Bejeweled.InGame.isMatching;
import static Bejeweled.InGame.score;

public class Gem5Y {
    static Random random = new Random();

    public static boolean match() {
        for (int i = 0; i < SizeGameMenu.numOfColumn; i++) {
            for (int j = 0; j < SizeGameMenu.numOfRow; j++) {
                if (Matrix2D.matrix[i][j].typeGem != 10) {
                    if ((i + 4) < SizeGameMenu.numOfColumn) {
                        if (Matrix2D.matrix[i][j].typeGem == Matrix2D.matrix[i + 1][j].typeGem && Matrix2D.matrix[i][j].typeGem == Matrix2D.matrix[i + 2][j].typeGem &&
                                Matrix2D.matrix[i][j].typeGem == Matrix2D.matrix[i + 3][j].typeGem && Matrix2D.matrix[i][j].typeGem == Matrix2D.matrix[i + 4][j].typeGem) {
                            InGame.addText(random.nextFloat() * 810, random.nextFloat() * (894 - 150) + 150, Matrix2D.matrix[i][j].typeGem, 5, "+50 Points", 5);
                            InGame.addText(500, 50, Matrix2D.matrix[i][j].typeGem, 1, "+02 Seconds", 0);
                            for (int k = 0; k < 5; k++) {
                                Matrix2D.matrix[i + k][j].typeGem = 10;
                            }
                            System.out.println("Find Gem 5Y at " + i + " " + j);
                            isMatching = true;
                            score += 50;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
