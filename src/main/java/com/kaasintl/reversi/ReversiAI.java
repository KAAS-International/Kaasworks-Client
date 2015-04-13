package main.java.com.kaasintl.reversi;

import main.java.com.kaasintl.api.AI;
import main.java.com.kaasintl.api.Field;
import main.java.com.kaasintl.api.GameBoard;

/**
 * Created by Kevin on 9-4-2015.
 */
public class ReversiAI extends AI
{
    public int best;

    @Override
    public int nextMove() {
        for (Field field : GameBoard.board) {
            if (field.getState() == 0 && isValid(field)) {
                return field.getCoordinate();
            }
        }
        return -99;
    }

    public boolean isValid(Field field) { // TODO: Move to ReversiRuleManager
        if (GameBoard.board.get(field.getCoordinate()).isEmpty() && field.getCoordinate() < GameBoard.board.size()-1 && field.getCoordinate() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
