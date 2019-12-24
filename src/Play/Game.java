package Play;

import Bejeweled.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {
    public static int WIDTH = 894;
    public static int HEIGHT = 894;
    public static final int FPS = 60;

    static public InGame inGame1;
    static public InGame inGame2;
    static public InGame inGame3;

    public Game(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        inGame1 = new InGame(1, 60, new Image("gameImage/Stage 1.jpg"), new GameSounds("gameSound/The-Journey-Begin.ogg"));
        inGame2 = new InGame(2, 45, new Image("gameImage/Stage 2.png"), new GameSounds("gameSound/LightStorm.ogg"));
        inGame3 = new InGame(3, 30, new Image("gameImage/Stage 3.png"), new GameSounds("gameSound/Jewels-Of-Denial.ogg"));
        addState(new UserRegistration());
        addState(new StartMenu());
        addState(inGame1);
        addState(inGame2);
        addState(inGame3);
        addState(new GameOver());
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer appGameContainer = new AppGameContainer(new Game("Bejeweled 2"));
        appGameContainer.setDisplayMode(WIDTH, HEIGHT, false);
        appGameContainer.setTargetFrameRate(FPS);
        appGameContainer.start();
    }
}
