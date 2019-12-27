package Bejeweled;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class GameSounds {
    public Sound sound;
    String res;

    public GameSounds(String res) throws SlickException {
        this.res = res;
        sound = new Sound(res);
    }

    void myPlaySound() {
        sound.play();
    }

    void myPlayLoopSound() {
        sound.loop();
    }

    void stop() {
        sound.stop();
    }
}

