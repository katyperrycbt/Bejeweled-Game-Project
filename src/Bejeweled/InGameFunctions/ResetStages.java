package Bejeweled.InGameFunctions;

public class ResetStages {
    public static void resetStages(){
        Play.Game.inGame1.setCount(0);
        Play.Game.inGame1.second = Play.Game.inGame1.setSecond;
        Play.Game.inGame1.timer = 0;

        Play.Game.inGame2.setCount(0);
        Play.Game.inGame2.second = Play.Game.inGame2.setSecond;
        Play.Game.inGame2.timer = 0;

        Play.Game.inGame3.setCount(0);
        Play.Game.inGame3.second = Play.Game.inGame3.setSecond;
        Play.Game.inGame3.timer = 0;
    }
}
