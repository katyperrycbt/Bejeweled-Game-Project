package Gems;

import Bejeweled.InGame;
import Bejeweled.SizeGameMenu;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

/**
 * Create 2D Gem array (by Matrix 2D class) and draw
 */
public class DrawGem {

    public Matrix2D matrix2D;
    public ArrayList<TextEffects> textEffectsArrayList;
    public Image image;

    public static float speed = 10;

    public DrawGem() throws SlickException {
        //Call directly static instances
        this.matrix2D = InGame.matrix2D;
        this.textEffectsArrayList = InGame.textEffectsArrayList;
        for (int i = 0; i < SizeGameMenu.numOfRow; i++) {
            for (int j = 0; j < SizeGameMenu.numOfColumn; j++) {
                if (Matrix2D.matrix[i][j].x >= 145) {
                    boolean isClicked = Matrix2D.matrix[i][j].isGemActive;
                    //This line to draw the clicked-gem with gray-color filter
                    if (!isClicked) {
                        //Draw normally
                        Draw(i, j);
                    } else if (isClicked) {
                        //Draw with gray filter
                        DrawClicked(i, j);
                    }

                }

            }
        }
        for (TextEffects textEffects : InGame.textEffectsArrayList) {
            //Draw texts movement, delete when reached condition in TextEffects.update() method
            textEffects.update();
            if (textEffects.typeTextEffect == 0) {
                InGame.scoreAppear.DrawWithGameFont(textEffects.x, textEffects.y, textEffects.string, Color.green);
            }
            if (textEffects.typeTextEffect == 1) {
                InGame.scoreAppear.DrawWithGameFont(textEffects.x, textEffects.y, textEffects.string, Color.blue);
            }
            if (textEffects.typeTextEffect == 2) {
                InGame.scoreAppear.DrawWithGameFont(textEffects.x, textEffects.y, textEffects.string, Color.yellow);
            }
            if (textEffects.typeTextEffect == 3) {
                InGame.scoreAppear.DrawWithGameFont(textEffects.x, textEffects.y, textEffects.string, Color.orange);
            }
            if (textEffects.typeTextEffect == 4) {
                InGame.scoreAppear.DrawWithGameFont(textEffects.x, textEffects.y, textEffects.string, Color.lightGray);
            }
            if (textEffects.typeTextEffect == 5) {
                InGame.scoreAppear.DrawWithGameFont(textEffects.x, textEffects.y, textEffects.string, Color.red);
            }
            if (textEffects.typeTextEffect == 6) {
                InGame.scoreAppear.DrawWithGameFont(textEffects.x, textEffects.y, textEffects.string, Color.cyan);
            }
        }
    }

    public void Draw(int i, int j) throws SlickException {
        getImage(i, j);
        image.draw(Matrix2D.matrix[i][j].y, Matrix2D.matrix[i][j].x);
    }

    public void DrawClicked(int i, int j) throws SlickException {
        getImage(i, j);
        image.draw(Matrix2D.matrix[i][j].y, Matrix2D.matrix[i][j].x, Color.gray);
    }

    public void getImage(int i, int j) throws SlickException {
        int n = Matrix2D.matrix[i][j].typeGem;
        if (n < 10) {
            if (n == 0) {
                image = new Image("gameImage/White_Jewelry.png");
            } else if (n == 1) {
                image = new Image("gameImage/Yellow_Jewelry.png");
            } else if (n == 2) {
                image = new Image("gameImage/Red_Jewelry.png");
            } else if (n == 3) {
                image = new Image("gameImage/Blue_Jewelry.png");
            } else if (n == 4) {
                image = new Image("gameImage/Orange_Jewelry.png");
            } else if (n == 5) {
                image = new Image("gameImage/Purple_Jewelry.png");
            } else if (n == 6) {
                image = new Image("gameImage/Green_Jewelry.png");
            }
        }
    }
}