package main.java.com.kaasintl.reversi;

import main.java.com.kaasintl.api.Field;
import main.java.com.kaasintl.api.RuleManager;
import main.java.com.kaasintl.main.GameManager;

/**
 * Created by Kevin on 9-4-2015.
 */
public class ReversiRuleManager extends RuleManager {
    private int coordinate;
    private int[] row;

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
        return (move.getCoordinate() - 1) == 0 && (move.getCoordinate() + 1) == 0 && (move.getCoordinate() - 8) == 0
                && (move.getCoordinate() - 9) == 0 && (move.getCoordinate() - 7) == 0 && (move.getCoordinate() + 8) == 0
                && (move.getCoordinate() + 7) == 0 && (move.getCoordinate() + 9) == 0;

    }

    @Override
    public boolean isWin(String player) {
        return true;
    }

    @Override
    public boolean isDraw(String player) {
        return true;
    }

    public boolean isValidN(Field f) {
        coordinate = f.getCoordinate();

        for (int i = 0; i < 0; i++) { // TODO: Instead of < 0 validate length of row
            row[i] = f.getCoordinate()-8;
        }

        /* TODO: This is pseudo code
            for each (ReversiField f in ReversiGameBoard) {
                if (f.getSTATE == friendly) {
                    return true;
                }
                else {
                    return false;
                }
            }
         */

        return true;
    }

    public boolean isValidNE(Field f) {
        return true;
    }

    public boolean isValidE(Field f) {
        return true;
    }

    public boolean isValidSE(Field f) {
        return true;
    }

    public boolean isValidS(Field f) {
        return true;
    }

    public boolean isValidSW(Field f) {
        return true;
    }

    public boolean isValidW(Field f) {
        return true;
    }

    public boolean isValidNW(Field f) {
        return true;
    }
}
