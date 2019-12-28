package Bejeweled.InGameFunctions;

import Gems.TextEffects;

import static Bejeweled.InGame.*;

public class CollectScore {
    public static boolean collectScore() {
        for (TextEffects t : textEffectsArrayList) {
            // if mouse hit the text
            if (xMouse >= t.x && xMouse <= (t.x + 70) && yMouse >= t.y && yMouse <= (t.y + 70)) {

                t.moveScore = true;
                t.isDestroyed = true;

//                scoredSound.myPlaySound();
                if (t.typeScore == 3)
                    score += 30;
                else if (t.typeScore == 4)
                    score += 40;
                else if (t.typeScore == 5)
                    score += 50;
                else if (t.typeScore == 6)
                    score += 60;
                else if (t.typeScore == 7)
                    score += 70;
                return true;
            }
        }
        return false;
    }
}
