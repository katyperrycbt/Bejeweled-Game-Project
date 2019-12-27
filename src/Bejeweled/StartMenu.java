package Bejeweled;

import Bejeweled.InGameFunctions.ResetStages;
import UserInfo.User;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;

import java.io.*;

/**
 * The start menu of Game
 */
public class StartMenu extends BasicGameState {
    private Image imageStartTheme;
    private GameFonts stringDisplay;
    private static int count = 0;
    private int count1 = 0;
    User user;
    public static String userName;

    FileInputStream f;
    ObjectInputStream o;
    //ID stage
    @Override
    public int getID() {
        return 0;
    }

    /**
     * Initialize the StartMenu class
     * @param gameContainer
     * @param stateBasedGame
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        imageStartTheme = new Image("gameImage/StartImage2894.png");
        stringDisplay = new GameFonts("gameFont/halo/HALO____.TTF", 40);
    }

    /**
     * Draw again and again by render(...) method
     * @param gameContainer
     * @param stateBasedGame
     * @param graphics
     * @throws SlickException
     */
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, org.newdawn.slick.Graphics graphics) throws SlickException {
        imageStartTheme.draw(0, 0);
        try {
            f = new FileInputStream( new File("myUser.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            o = new ObjectInputStream(f);
            user = (User) o.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        userName = user.getName();
        String stringStartMenu = "Welcome " + userName + "! \nPress Space Bar To Start!";
        stringDisplay.DrawWithGameFont(25, 500, stringStartMenu, Color.white);
        /**
         * We want to call this sound once, so we set it's condition
         * Without this below condition, the sound replays infinitely
         */
        while (count1 == 0) {
            GameSounds mySound = new GameSounds("gameSound/Welcome.ogg");
            mySound.myPlaySound();
            count1++;
        }
    }

    /**
     * Update() method to update the game's state
     * Just like render() method, this one is being called again and again (after 1000 millisecond)
     * @param gameContainer
     * @param stateBasedGame
     * @param i
     * @throws SlickException
     */
    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        /**
         * We also want these sounds below is called once in this stage ever
         *      Limited by count == 0.
         */
        if (gameContainer.getInput().isKeyDown(Input.KEY_SPACE)) {
            while (count == 0) {
                GameSounds getReady = new GameSounds("gameSound/Get_ready.ogg");

                GameSounds go = new GameSounds("gameSound/Go.ogg");
                getReady.myPlaySound();
                //Sounds play in order.
                try {
                    Thread.sleep(1000);
                    go.myPlaySound();
                    Thread.sleep(1200);
                    stateBasedGame.enterState(1, new FadeOutTransition(), new FadeInTransition());
                    ResetStages.resetStages();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
            }
        }
    }

    public static void setCount(int count) {
        StartMenu.count = count;
    }
}
