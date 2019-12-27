package Bejeweled;

import Bejeweled.InGameFunctions.ResetStages;
import Play.Game;
import UserInfo.BestScore;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GameOver extends BasicGameState {

    private Image gameOverImage;
    private int score;
    private GameFonts gameOverFont;

    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        gameOverImage = new Image("gameImage/GameOver.jpg");
        gameOverFont = new GameFonts("gameFont/zorque/zorque.ttf", 40);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        gameOverImage.draw(0, 0);
        gameOverFont.DrawWithGameFont(325, 300, "Game Over!", Color.white);
        gameOverFont.DrawWithGameFont(300, 400, "Your Score: " + score, Color.white);
        gameOverFont.DrawWithGameFont(100, 600, "Press Space Bar To Play Again!", Color.white);
        try {
            bestScore();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        this.score = InGame.score;
        if (gameContainer.getInput().isKeyDown(Input.KEY_SPACE)) {
            InGame.score = 0;
            ResetStages.resetStages();
            stateBasedGame.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }
    }

    public int readScoreFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(new File("best.txt"));
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        BestScore bestScore = (BestScore) objectInputStream.readObject();
        return bestScore.getScore();
    }

    public void bestScore() throws IOException, ClassNotFoundException {
        String bestScoreString = "Best of you: " + readScoreFile();
        gameOverFont.DrawWithGameFont(300, 500, bestScoreString, Color.white);
    }
}
