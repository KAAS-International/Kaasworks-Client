package main.java.com.kaasintl.reversi;

import main.java.com.kaasintl.api.AI;
import main.java.com.kaasintl.api.Field;

/**
 * Created by Kevin on 9-4-2015.
 */
public class ReversiAI extends AI
{
    public int best;

    @Override
    public int nextMove() {
        /* for (ReversiField field : ReversiGameBoard.board) {
            if (field.getState() == 0 && isValid(field)) { // TODO: Use RuleManager instead of AI
                return field.getCoordinate();
            }
        } */
        return -99;
    }

    public boolean isValid(ReversiField field) { // TODO: Use RuleManager instead of AI
        /*if (ReversiGameBoard.board.get(field.getCoordinate()).isEmpty() && field.getCoordinate() < ReversiGameBoard.board.size()-1 && field.getCoordinate() > 0) {
            return true;
        } else {
            return false;
        } */
        return true;
    }
}
