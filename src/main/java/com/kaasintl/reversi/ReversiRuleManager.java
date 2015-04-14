package main.java.com.kaasintl.reversi;

import main.java.com.kaasintl.api.RuleManager;
import main.java.com.kaasintl.main.GameManager;

/**
 * Created by Kevin on 9-4-2015.
 */
public class ReversiRuleManager extends RuleManager {

    @Override
    public boolean isValid(int i) {
        ReversiField move = null;

        /* for (ReversiField field : GameManager.getGameBoard().getBoard().board) {
            if (field.getCoordinate() == i) {
                move = field;
            }
        } */

        // TODO: Replace "== 0" with field+x.getState() == 2
        // TODO: This will only check for adjacent opponent fields, not yet if a piece of ours is on the same line. because fuck that. its difficult.
        if ((move.getCoordinate()-1) == 0 && (move.getCoordinate()+1) == 0 && (move.getCoordinate()-8) == 0
                && (move.getCoordinate()-9) == 0 && (move.getCoordinate()-7) == 0 && (move.getCoordinate()+8) == 0
                && (move.getCoordinate()+7) == 0 && (move.getCoordinate()+9) ==0) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isWin(String player) {
        return true;
    }

    @Override
    public boolean isDraw(String player) {
        return true;
    }
}
