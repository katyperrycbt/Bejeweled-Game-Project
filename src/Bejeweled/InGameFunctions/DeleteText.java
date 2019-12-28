package Bejeweled.InGameFunctions;

import Gems.TextEffects;

import static Bejeweled.InGame.textEffectsArrayList;

public class DeleteText {
    public static void deleteText() {
        //Recursion here
        boolean repeat = false;
        for (TextEffects t : textEffectsArrayList) {
            if (t.isDestroyed) {
                textEffectsArrayList.remove(t);
                repeat = true;
                break;
            }
        }
        if (repeat) {
            deleteText();
        }
    }
}
