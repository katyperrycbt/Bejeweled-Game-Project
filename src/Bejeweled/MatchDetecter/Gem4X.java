package Bejeweled.MatchDetecter;

import Bejeweled.InGame;
import Bejeweled.SizeGameMenu;
import Gems.Matrix2D;

import java.util.Random;

import static Bejeweled.InGame.isMatching;
import static Bejeweled.InGame.score;

public class Gem4X {
    static Random random = new Random();

    public static boolean match() {
        for (int i = 0; i < SizeGameMenu.numOfColumn; i++) {
            for (int j = 0; j < SizeGameMenu.numOfRow; j++) {
                if (Matrix2D.matrix[i][j].typeGem != 10) {
                    if ((j + 3) < SizeGameMenu.numOfColumn) {
                        if (Matrix2D.matrix[i][j].typeGem == Matrix2D.matrix[i][j + 1].typeGem && Matrix2D.matrix[i][j].typeGem == Matrix2D.matrix[i][j + 2].typeGem &&
                                Matrix2D.matrix[i][j].typeGem == Matrix2D.matrix[i][j + 3].typeGem) {
                            InGame.addText(random.nextFloat() * 810, random.nextFloat() * (894 - 150) + 150, Matrix2D.matrix[i][j].typeGem, 5, "+40 Points", 4);
                            InGame.addText(500, 50, Matrix2D.matrix[i][j].typeGem, 1, "+01 Seconds", 0);
                            for (int k = 0; k < 4; k++) {
                                Matrix2D.matrix[i][j + k].typeGem = 10;
                            }
                            System.out.println("Find Gem 4X at " + i + " " + j);
                            isMatching = true;
                            score += 40;
                            return true;

                        }
                    }
                }
            }
        }
        return false;
    }
}
