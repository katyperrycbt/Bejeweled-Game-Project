package Bejeweled.InGameFunctions;

import Bejeweled.MoveDetecter.MovingDown;
import Bejeweled.MoveDetecter.MovingLeft;
import Bejeweled.MoveDetecter.MovingRight;
import Bejeweled.MoveDetecter.MovingUp;

public class DetectMove {
    public static boolean detectMove() {
        if (MovingRight.move()) {
            return true;
        }
        if (MovingLeft.move()) {
            return true;
        }
        if (MovingUp.move()) {
            return true;
        }
        if (MovingDown.move()) {
            return true;
        }
        return false;
    }
}
