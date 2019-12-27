package Bejeweled;

import UserInfo.User;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegistration extends BasicGameState {
    private TextField textField;
    private Image okButton;
    private Image cancelButton;
    private Image userRegistrationImage;
    private GameSounds gameSounds;
    private GameSounds saveNameSound;
    private GameFonts gameFonts;
    private int count = 0;
    private String userName = null;

    FileOutputStream f;
    FileInputStream f1;

    ObjectOutputStream o;
    @Override
    public int getID() {
        return 10;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        userRegistrationImage = new Image("gameImage/UserRegistration.jpg");
        okButton = new Image("gameImage/OKButton.png");
        cancelButton = new Image("gameImage/CancelButton.png");

        saveNameSound = new GameSounds("gameSound/User.ogg");
        gameSounds = new GameSounds("gameSound/UserName.ogg");
        gameFonts = new GameFonts("gameFont/nimavisual_moonhouse/moonhouse.ttf", 60);

        textField = new TextField(gameContainer, gameFonts.getUniFont(), 200,200,500,70);

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        userRegistrationImage.draw(0,0);
        okButton.draw(30, 400);
        cancelButton.draw(800, 400);
        textField.render(gameContainer, graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        while (count == 0){
            if (checkUserInfo()){
                stateBasedGame.enterState(0);
            } else {
                gameSounds.myPlaySound();
            }
            count++;
        }
        userName = textField.getText();

        float xMouse = gameContainer.getInput().getMouseX();
        float yMouse = gameContainer.getInput().getMouseY();
        boolean isMouseClicked = gameContainer.getInput().isMousePressed(0);

        String regex = "^[a-zA-z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userName);

        if (xMouse>=30 && xMouse <=90 && yMouse >=400 && yMouse<=460 && isMouseClicked){
            if (matcher.find()) {
                try {
                    saveUserInfo(new User(userName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                saveNameSound.myPlaySound();
                try {
                    Thread.sleep(1700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stateBasedGame.enterState(0, new FadeOutTransition(), new FadeInTransition());
            } else {
                gameSounds.myPlaySound();
            }
        }
        if (xMouse>=800 && xMouse<=860&& yMouse>=400 && yMouse <=460 && isMouseClicked){
            textField.setText("");
        }
    }

    public void saveUserInfo(User user) throws IOException {
        f = new FileOutputStream(new File("myUser.txt"));
        o = new ObjectOutputStream(f);

        o.writeObject(user);

        o.close();
        f.close();
    }
    public boolean checkUserInfo() {
        try {
            f1 = new FileInputStream(new File("myUser.txt"));
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }
}
