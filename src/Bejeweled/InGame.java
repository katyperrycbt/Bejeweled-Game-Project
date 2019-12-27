package Bejeweled;

import Bejeweled.InGameFunctions.*;
import Bejeweled.MatchDetecter.*;
import Gems.DrawGem;
import Gems.Matrix2D;
import Gems.TextEffects;
import UserInfo.BestScore;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.io.*;
import java.util.ArrayList;

/**
 * In-Game class
 */
public class InGame extends BasicGameState {
    /**
     * Create static instances => refer directly instances and methods
     */
    public static Matrix2D matrix2D;
    public static ArrayList<TextEffects> textEffectsArrayList;

    private Image imageInGame;
    DrawGem drawGem;

    private int stage;
    //Just want to show a running sign-text
    public static String name = "Game by THUCKATY!";
    public static float xName = 0;
    public static float yName = 118;
    //Mouse's properties
    public static boolean isLeftMouseClicked;
    public static float xMouse;
    public static float yMouse;
    //Switch gems
    public static int temporaryGemsType;

    public static boolean isMatching = false;
    public static boolean gameOver = false;
    //Tell what will be processed next
    public int stateInGame = 0;
    //Timer = down-counter clock
    public int timer = 0;
    //Set second through setSecond variable
    public int setSecond;
    public int second = 0;
    //We want the score remains through different stages
    public static int score = 0;
    //Here, we just want to check the condition of running sign-text
    private boolean isSignMoveRight = true;
    //Some fonts
    private GameFonts scoredFont;
    private static GameFonts nameFont;
    public static GameFonts scoreAppear;
    private static GameFonts bestScoreFont;

    private String time;
    //Some sounds
    GameSounds clickedSound;
    GameSounds swappedSound;
    GameSounds gemsOf3;
    GameSounds gemsOf4;
    GameSounds gemsOf5;
    GameSounds scoredSound;
    GameSounds bejeweledSound;
    GameSounds excellent;
    //Some strings to be displayed
    String mouse;
    String scoreString;
    String bestScore;
    //Check bugs
    public static boolean checkBug = false;
    //Condition of sounds playing
    private int count = 0;
    private int excellentSoundCount;


    /**
     * Stages are only an object of InGame class, the ID is defined when we initialize the stage.
     *
     * @return
     */
    @Override
    public int getID() {
        return stage;
    }

    /**
     * Just like before (in StartMenu class), this method is used to initialize values for instances defined above
     *
     * @param gameContainer
     * @param stateBasedGame
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        matrix2D = new Matrix2D();
        textEffectsArrayList = new ArrayList<>();

        scoredFont = new GameFonts("gameFont/neuropol-x-free/neuropol x rg.ttf", 25);
        nameFont = new GameFonts("gameFont/perfect-dark-brk/pdark.ttf", 25);
        scoreAppear = new GameFonts("gameFont/zorque/zorque.ttf", 25);
        bestScoreFont = new GameFonts("gameFont/nimavisual_moonhouse/moonhouse.ttf", 25);

        clickedSound = new GameSounds("gameSound/Button_press.ogg");
        swappedSound = new GameSounds("gameSound/SwapSound.ogg");
        gemsOf3 = new GameSounds("gameSound/Match.ogg");
        gemsOf4 = new GameSounds("gameSound/Match3.ogg");
        gemsOf5 = new GameSounds("gameSound/Match5.ogg");
        scoredSound = new GameSounds("gameSound/CollectScore.ogg");
        excellent = new GameSounds("gameSound/excellent1.ogg");

        excellentSoundCount = 0;
    }

    /**
     * Render class, as it's name, is to render, sounds and images...
     *
     * @param gameContainer
     * @param stateBasedGame
     * @param graphics
     * @throws SlickException
     */
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, org.newdawn.slick.Graphics graphics) throws SlickException {
        //We want plays this sound once
        while (count == 0) {
            bejeweledSound.myPlayLoopSound();
            count++;
        }
        imageInGame.draw(0, 0);

        //Draw the blur Round Rectangle behind the gem's matrix
        graphics.setColor(new Color(0, 0, 70, 100));
        graphics.fillRoundRect(80, 140, 730, 730, 30, 30);
        //Display score and sign-text
        scoredFont.DrawWithGameFont(250, 50, "Hi "+ StartMenu.userName
                +"\n"+scoreString + "   " + time, Color.white);
        nameFont.DrawWithGameFont(xName, yName, name, Color.green);
        //Display Gems
        try {
            bestScore();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        drawGem = new DrawGem();
    }

    /**
     * Update what will change in the game playing process
     *
     * @param gameContainer
     * @param stateBasedGame
     * @param i              = 1000 milliseconds
     * @throws SlickException
     */
    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        //First change is the sign-text, just some movement directions
        if (isSignMoveRight) {
            xName += 0.5f;
            if (xName == 635.0) {
                isSignMoveRight = false;
            }
        } else {
            yName -= 0.5f;
            if (yName == 81) {
                isSignMoveRight = true;
            }
        }

        timer += i;
        scoreString = "Score: " + score;
        //Time left calculation
        time = "Time: " + (second - (timer / 1000));
        //Just back to the Start Menu if you press the BackSpace button
        if (gameContainer.getInput().isKeyDown(Input.KEY_BACK)) {
            StartMenu.setCount(0);
            bejeweledSound.stop();
            score = 0;
            stateBasedGame.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }
        //Get the mouse location to get action
        xMouse = gameContainer.getInput().getMouseX();
        yMouse = gameContainer.getInput().getMouseY();
        //We "used to" want to display the mouse's location
        mouse = xMouse + " ; " + yMouse;
        //Check if mouse is clicked or not
        isLeftMouseClicked = gameContainer.getInput().isMousePressed(0);
        //Again, we want to play some actions in the "count2" condition only one times.
        int count2 = 0;
        /**
         * Check if we pass the current stage or not
         * This "score > (500 * 0.75 * stage)" is the easy condition to check stages
         */
        if (score > (700*0.75*stage*stage)) {
            if (stage < 3) {
                xName = 0;
                yName = 118;
                stateBasedGame.enterState(stage + 1, new FadeOutTransition(), new FadeInTransition());
                matrix2D = new Matrix2D();
                GameSounds gameSounds = new GameSounds("gameSound/Level_Complete.ogg");
                gameSounds.myPlaySound();
                bejeweledSound.stop();
            } else {
                GameSounds gameSounds = new GameSounds("gameSound/excellent1.ogg");
                GameSounds gameSounds1 = new GameSounds("gameSound/World_Complete.ogg");
                gameSounds.myPlaySound();
                try {
                    Thread.sleep(1200);
                    gameSounds1.myPlaySound();
                    stateBasedGame.enterState(4, new FadeOutTransition(), new FadeInTransition());
                    bejeweledSound.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //the boolean gameOver is set to 'true' to reset when game over
                gameOver = true;
            }
        }
        //Time's Up = Game Over
        if (second - (timer / 1000) <= 0) {
            gameOver = true;
            while (count2 == 0) {

                GameSounds mySound = new GameSounds("gameSound/Game_Over.ogg");
                GameSounds mySound1 = new GameSounds("gameSound/Time_Up.ogg");
                mySound1.myPlaySound();
                try {
                    Thread.sleep(1000);
                    mySound.myPlaySound();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count2++;
            }
            stateBasedGame.enterState(4, new FadeOutTransition(), new FadeInTransition());
            bejeweledSound.stop();
        }

        //Delete Score Text when the mouse touch the text
        DeleteText.deleteText();

        //Collect score when the mouse touch the text
        if (CollectScore.collectScore()) {
            scoredSound.myPlaySound();
        }

        //Just a test of falling action,
        //TestFalling.testFalling();

        //When we swap gems, we want it to be as smooth as we can,
        //  this method draw the smooth swap if the gem is not at the location that they're supposed to be.
        DrawSmoothSwap.drawSmoothSwap();

        //Tell the update() method what's next to do
        //First, the stateInGame = 0
        if (stateInGame == 0) {
            if (IfGemIsClicked.detectIfGemIsClicked()) {
                clickedSound.myPlaySound();
            }
            //If detect move (means that there's already changes in gems)
            if (DetectMove.detectMove()) {
                swappedSound.myPlaySound();
                stateInGame = 1;
            }
        }
        if (stateInGame == 1) {
            //Check they collapse or not
            detectMatch();
        }
        if (stateInGame == 2) {
            //If they collapsed, falling action happens & create new gems
            if (Falling.falling()) {
                stateInGame = 1;
            }
        }
        if (stateInGame == 3) {
            //If detect if the swap gems are destroyed or not, if not, return them in their's previous location
            if (CheckNotDetect.checkIsNotDetected()) {
                stateInGame = 0;
            }
        }

        if (checkScoreFile()){
            try {
                if (readScoreFile() < score){
                    while (excellentSoundCount == 0){
                        excellent.myPlaySound();
                        excellentSoundCount++;
                    }
                    writeScoreFile(score);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                writeScoreFile(score);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //If gameOver is true
        ResetWhenGameOver.resetWhenGameIsOver();
    }

    /**
     * Constructor to initialize many stages
     *
     * @param stage
     * @param setSecond
     * @param imageInGame
     * @param bejeweledSound
     */
    public InGame(int stage, int setSecond, Image imageInGame, GameSounds bejeweledSound) {
        this.imageInGame = imageInGame;
        this.stage = stage;
        this.setSecond = setSecond;
        this.bejeweledSound = bejeweledSound;
        second += this.setSecond;
    }

    public void detectMatch() {
        isMatching = false;

        if (GemUpT.match()) {
            gemsOf5.myPlaySound();
            second += 5;
        }
        if (GemDownT.match()) {
            gemsOf5.myPlaySound();
            second += 5;
        }
        if (GemLeftT.match()) {
            gemsOf5.myPlaySound();
            second += 5;
        }
        if (GemRightT.match()) {
            gemsOf5.myPlaySound();
            second += 5;
        }
        if (GemUpL.match()) {
            gemsOf5.myPlaySound();
            second += 4;
        }
        if (GemDownL.match()) {
            gemsOf5.myPlaySound();
            second += 4;
        }
        if (GemLeftL.match()) {
            gemsOf5.myPlaySound();
            second += 4;
        }
        if (GemRightL.match()) {
            gemsOf5.myPlaySound();
            second += 4;
        }
        if (Gem5X.match()) {
            gemsOf5.myPlaySound();
            second += 2;
        }
        if (Gem5Y.match()) {
            gemsOf5.myPlaySound();
            second += 2;
        }
        if (Gem4X.match()) {
            gemsOf4.myPlaySound();
            second += 1;
        }
        if (Gem4Y.match()) {
            gemsOf4.myPlaySound();
            second += 1;
        }
        if (Gem3X.match()) {
            gemsOf3.myPlaySound();
        }
        if (Gem3Y.match()) {
            gemsOf3.myPlaySound();
        }

        if (isMatching) {
            checkBug = false;
            matrix2D.notDetectRight = false;
            matrix2D.notDetectLeft = false;
            matrix2D.notDetectUp = false;
            matrix2D.notDetectDown = false;
            //falling's active
            stateInGame = 2;
        } else {
            checkBug = true;
            //checkIsNotDetected's active
            stateInGame = 3;
        }
    }

    public static void addText(float x, float y, int type, float moveSpeedY, String text, int typeScore) {
        textEffectsArrayList.add(new TextEffects(x, y, type, moveSpeedY, text, typeScore));
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static int getScore() {
        return score;
    }

    public boolean checkScoreFile(){
        try {
            new FileInputStream(new File("best.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Notfound Score File");
            return false;
        }
        return true;
    }

    public void writeScoreFile(int bestScore) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File("best.txt"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(new BestScore(bestScore));
    }

    public int readScoreFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(new File("best.txt"));
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        BestScore bestScore = (BestScore) objectInputStream.readObject();
        return bestScore.getScore();
    }

    public void bestScore() throws IOException, ClassNotFoundException {
        bestScore = "Best of you: " + readScoreFile();
        bestScoreFont.DrawWithGameFont(500, 0, bestScore, Color.white);
    }
}
