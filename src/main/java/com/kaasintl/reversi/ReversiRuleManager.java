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
    private ReversiField[] moves;
    private GameManager gm;

    public ReversiRuleManager(GameManager gm) {
        this.gm = gm;
    }

    @Override
    public boolean isValid(int i) {
        ReversiField move = null;

        for (Field field : gm.getGameBoard().getBoard()) {
            if (field.getCoordinate() == i) {
                move = (ReversiField) field;
                break;
            }
        }

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
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if (f == null) break;
            row[i] = f.getCoordinate()-8;
        }

        for (int i : row) {
            for (Field field : gm.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                }
            }
        }

        for (ReversiField move : moves) {
            if (move.getState() == Field.STATE.Friendly) {
                return true;
            }
            else if (move.getState() == Field.STATE.Enemy) {
            }
        }

        return false;
    }

    public boolean isValidNE(Field f) {
        coordinate = f.getCoordinate();
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if (f == null) break;
            row[i] = f.getCoordinate()-8;
        }

        for (int i : row) {
            for (Field field : gm.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                }
            }
        }

        for (ReversiField move : moves) {
            if (move.getState() == Field.STATE.Friendly) {
                return true;
            }
            else if (move.getState() == Field.STATE.Enemy) {
            }
        }

        return false;
    }

    public boolean isValidE(Field f) {
        coordinate = f.getCoordinate();
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if (f == null) break;
            row[i] = f.getCoordinate()-8;
        }

        for (int i : row) {
            for (Field field : gm.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                }
            }
        }

        for (ReversiField move : moves) {
            if (move.getState() == Field.STATE.Friendly) {
                return true;
            }
            else if (move.getState() == Field.STATE.Enemy) {
            }
        }

        return false;
    }

    public boolean isValidSE(Field f) {
        coordinate = f.getCoordinate();
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if (f == null) break;
            row[i] = f.getCoordinate()-8;
        }

        for (int i : row) {
            for (Field field : gm.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                }
            }
        }

        for (ReversiField move : moves) {
            if (move.getState() == Field.STATE.Friendly) {
                return true;
            }
            else if (move.getState() == Field.STATE.Enemy) {
            }
        }

        return false;
    }

    public boolean isValidS(Field f) {
        coordinate = f.getCoordinate();
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if (f == null) break;
            row[i] = f.getCoordinate()-8;
        }

        for (int i : row) {
            for (Field field : gm.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                }
            }
        }

        for (ReversiField move : moves) {
            if (move.getState() == Field.STATE.Friendly) {
                return true;
            }
            else if (move.getState() == Field.STATE.Enemy) {
            }
        }

        return false;
    }

    public boolean isValidSW(Field f) {
        coordinate = f.getCoordinate();
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if (f == null) break;
            row[i] = f.getCoordinate()-8;
        }

        for (int i : row) {
            for (Field field : gm.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                }
            }
        }

        for (ReversiField move : moves) {
            if (move.getState() == Field.STATE.Friendly) {
                return true;
            }
            else if (move.getState() == Field.STATE.Enemy) {
            }
        }

        return false;
    }

    public boolean isValidW(Field f) {
        coordinate = f.getCoordinate();
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if (f == null) break;
            row[i] = f.getCoordinate()-8;
        }

        for (int i : row) {
            for (Field field : gm.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                }
            }
        }

        for (ReversiField move : moves) {
            if (move.getState() == Field.STATE.Friendly) {
                return true;
            }
            else if (move.getState() == Field.STATE.Enemy) {
            }
        }

        return false;
    }

    public boolean isValidNW(Field f) {
        coordinate = f.getCoordinate();
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if (f == null) break;
            row[i] = f.getCoordinate()-8;
        }

        for (int i : row) {
            for (Field field : gm.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                }
            }
        }

        for (ReversiField move : moves) {
            if (move.getState() == Field.STATE.Friendly) {
                return true;
            }
            else if (move.getState() == Field.STATE.Enemy) {
            }
        }

        return false;
    }
}
