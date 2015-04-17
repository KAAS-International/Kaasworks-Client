package main.java.com.kaasintl.reversi;

import main.java.com.kaasintl.api.Field;
import main.java.com.kaasintl.api.RuleManager;
import main.java.com.kaasintl.main.GameManager;

/**
 * Created by Kevin on 9-4-2015.
 */
public class ReversiRuleManager extends RuleManager {
    private int[] row = new int[8];
    private ReversiField[] moves = new ReversiField[64];
    private GameManager gameManager;

    public static final int OPPONENT = 0;
    public static final int AI = 1;

    public ReversiRuleManager(GameManager gm) {
        this.gameManager = gm;
    }

    @Override
    public boolean isValid(int i) {
        ReversiField move = null;

        for (Field field : gameManager.getGameBoard().getBoard()) {
            if (field.getCoordinate() == i) {
                move = (ReversiField) field;
                break;
            }
        }

        if (isOccupied(i)) return false;
        if (isValidE(move) || isValidN(move) || isValidNE(move) || isValidNW(move) || isValidS(move) || isValidSE(move) || isValidSW(move) || isValidW(move)) return true;
        return false;
    }

    public void flip(int i) {
        ReversiField move = null;

        for (Field field : gameManager.getGameBoard().getBoard()) {
            if (field.getCoordinate() == i) {
                move = (ReversiField) field;
                break;
            }
        }
        flipN(move);
        flipNE(move);
        flipE(move);
        flipSE(move);
        flipS(move);
        flipSW(move);
        flipW(move);
        flipNW(move);
    }

    @Override
    public boolean isOccupied(int i) {
        if (gameManager.getGameBoard().getBoard().get(i).getState() == Field.STATE.Open) {
            return false;
        } else {
            return true;
        }
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
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if (f == null) break;
                if(i == 0) {
                    row[i] = f.getCoordinate() - 8;
                } else {
                    row[i] = row[i - 1] -8;
                }
        }

        for (int i : row) {
            for (Field field : gameManager.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                    break;
                }
            }
        }

        if (moves[0].getState() == Field.STATE.Friendly) {
            return false;
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
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if (f == null) break;
            if(i == 0) {
                row[i] = f.getCoordinate() - 7;
            } else {
                row[i] = row[i - 1] - 7;
            }
        }

        for (int i : row) {
            for (Field field : gameManager.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                    break;
                }
            }
        }

        if (moves[0].getState() == Field.STATE.Friendly) {
            return false;
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
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if (f == null) break;
            if(i == 0) {
                row[i] = f.getCoordinate() + 1;
            } else {
                row[i] = row[i - 1] + 1;
            }
        }

        for (int i : row) {
            for (Field field : gameManager.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                    break;
                }
            }
        }

        if (moves[0].getState() == Field.STATE.Friendly) {
            return false;
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
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if (f == null) break;
            if(i == 0) {
                row[i] = f.getCoordinate() + 9;
            } else {
                row[i] = row[i - 1] + 9;
            }
        }

        for (int i : row) {
            for (Field field : gameManager.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                    break;
                }
            }
        }

        if (moves[0].getState() == Field.STATE.Friendly) {
            return false;
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
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if (f == null) break;
            if(i == 0) {
                row[i] = f.getCoordinate() + 8;
            } else {
                row[i] = row[i - 1] + 8;
            }
        }

        for (int i : row) {
            for (Field field : gameManager.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                    break;
                }
            }
        }

        if (moves[0].getState() == Field.STATE.Friendly) {
            return false;
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
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if (f == null) break;
            if(i == 0) {
                row[i] = f.getCoordinate() + 7;
            } else {
                row[i] = row[i - 1] + 7;
            }
        }

        for (int i : row) {
            for (Field field : gameManager.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                    break;
                }
            }
        }

        if (moves[0].getState() == Field.STATE.Friendly) {
            return false;
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
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if (f == null) break;
            if(i == 0) {
                row[i] = f.getCoordinate() - 1;
            } else {
                row[i] = row[i - 1] - 1;
            }
        }

        for (int i : row) {
            for (Field field : gameManager.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                    break;
                }
            }
        }

        if (moves[0].getState() == Field.STATE.Friendly) {
            return false;
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
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if (f == null) break;
            if(i == 0) {
                row[i] = f.getCoordinate() - 9;
            } else {
                row[i] = row[i - 1] - 9;
            }
        }

        for (int i : row) {
            for (Field field : gameManager.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                    break;
                }
            }
        }

        if (moves[0].getState() == Field.STATE.Friendly) {
            return false;
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

    public void flipN(Field f) {
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if(i == 0) {
                row[i] = f.getCoordinate() - 8;
            } else {
                row[i] = row[i - 1] - 8;
            }
        }

        for (int i : row) {
            for (Field field : gameManager.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                    break;
                }
            }
        }

        for (ReversiField move : moves) {
            if (move.getState() == Field.STATE.Enemy) {
                move.setState(Field.STATE.Friendly);
            }
            else {
                break;
            }
        }
    }

    public void flipNE(Field f) {
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if(i == 0) {
                row[i] = f.getCoordinate() - 7;
            } else {
                row[i] = row[i - 1] - 7;
            }
        }

        for (int i : row) {
            for (Field field : gameManager.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                    break;
                }
            }
        }

        for (ReversiField move : moves) {
            if (move.getState() == Field.STATE.Enemy) {
                move.setState(Field.STATE.Friendly);
            }
            else {
                break;
            }
        }
    }

    public void flipE(Field f) {
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if(i == 0) {
                row[i] = f.getCoordinate() + 1;
            } else {
                row[i] = row[i - 1] + 1;
            }
        }

        for (int i : row) {
            for (Field field : gameManager.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                    break;
                }
            }
        }

        for (ReversiField move : moves) {
            if (move.getState() == Field.STATE.Enemy) {
                move.setState(Field.STATE.Friendly);
            }
            else {
                break;
            }
        }
    }

    public void flipSE(Field f) {
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if(i == 0) {
                row[i] = f.getCoordinate() + 9;
            } else {
                row[i] = row[i - 1] + 9;
            }
        }

        for (int i : row) {
            for (Field field : gameManager.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                    break;
                }
            }
        }

        for (ReversiField move : moves) {
            if (move.getState() == Field.STATE.Enemy) {
                move.setState(Field.STATE.Friendly);
            }
            else {
                break;
            }
        }
    }

    public void flipS(Field f) {
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if(i == 0) {
                row[i] = f.getCoordinate() + 8;
            } else {
                row[i] = row[i - 1] + 8;
            }
        }

        for (int i : row) {
            for (Field field : gameManager.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                    break;
                }
            }
        }

        for (ReversiField move : moves) {
            if (move.getState() == Field.STATE.Enemy) {
                move.setState(Field.STATE.Friendly);
            }
            else {
                break;
            }
        }
    }

    public void flipSW(Field f) {
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if(i == 0) {
                row[i] = f.getCoordinate() + 7;
            } else {
                row[i] = row[i - 1] + 7;
            }
        }

        for (int i : row) {
            for (Field field : gameManager.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                    break;
                }
            }
        }

        for (ReversiField move : moves) {
            if (move.getState() == Field.STATE.Enemy) {
                move.setState(Field.STATE.Friendly);
            }
            else {
                break;
            }
        }
    }

    public void flipW(Field f) {
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if(i == 0) {
                row[i] = f.getCoordinate() - 1;
            } else {
                row[i] = row[i - 1] - 1;
            }
        }

        for (int i : row) {
            for (Field field : gameManager.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                    break;
                }
            }
        }

        for (ReversiField move : moves) {
            if (move.getState() == Field.STATE.Enemy) {
                move.setState(Field.STATE.Friendly);
            }
            else {
                break;
            }
        }
    }

    public void flipNW(Field f) {
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            if(i == 0) {
                row[i] = f.getCoordinate() - 9;
            } else {
                row[i] = row[i - 1] - 9;
            }
        }

        for (int i : row) {
            for (Field field : gameManager.getGameBoard().getBoard()) {
                if (field.getCoordinate() == i) {
                    moves[counter] = (ReversiField) field;
                    break;
                }
            }
        }

        for (ReversiField move : moves) {
            if (move.getState() == Field.STATE.Enemy) {
                move.setState(Field.STATE.Friendly);
            }
            else {
                break;
            }
        }
    }
}
