package Gems;

public class TextEffects {
    public float x;
    public float y;
    public int typeTextEffect;
    public float moveSpeedY;
    public String string;
    public int typeScore;

    public boolean isDestroyed = false;
    public float counter = 0;
    public int counterDeleteLength = 60;
    public boolean moveScore = false;

    public TextEffects(float x, float y, int typeTextEffect, float moveSpeedY, String string, int typeScore) {
        this.x = x;
        this.y = y;
        this.typeTextEffect = typeTextEffect;
        this.moveSpeedY = moveSpeedY;
        this.string = string;
        this.typeScore = typeScore;
    }

    /**
     * Every time being called the text move a little bit, until they're destroyed
     */
    public void update() {
        y += moveSpeedY;
        counter += 0.5f;
        if (counter > counterDeleteLength) {
            isDestroyed = true;
        }
    }
}
