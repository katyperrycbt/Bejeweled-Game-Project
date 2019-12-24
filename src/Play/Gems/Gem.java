package Gems;

import java.util.Random;

/**
 * Properties of gem
 */
public class Gem {
    static Random random = new Random();

    public int typeGem;

    public float x;
    public float y;

    public boolean isGemActive = false;
    public boolean isFalling = false;

    public Gem(int typeGem, float x, float y) {
        this.typeGem = typeGem;
        this.x = x;
        this.y = y;
    }

    //Pick gem randomly here
    public static int getTypeGem() {
        return random.nextInt(7);
    }
}
