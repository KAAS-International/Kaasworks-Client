package main.java.com.kaasintl.reversi;

import main.java.com.kaasintl.api.AI;
import main.java.com.kaasintl.api.Field;
import main.java.com.kaasintl.api.GameBoard;
import main.java.com.kaasintl.api.RuleManager;

/**
 * Created by Kevin on 9-4-2015.
 */
public class ReversiAI extends AI
{
    public int[] moves;
    public int best;

    @Override
    public int nextMove() {
        for (Field field : GameBoard.board) {
            if (0 == 0) { // TODO: This should call RuleManager to verify if field is valid
                best = backtrack(minimax(field.getCoordinate()));
            }
        }

        return best; // Return best possible move
    }

    // TODO: This method only includes fields with a positive value, needs to be extended with backtracking to include possible bad moves
    public int[] minimax(int i) {
        for (Field field : GameBoard.board) {
            if (field.getValue() > 0) {
                moves[moves.length] = field.getCoordinate();
            }
        }

        if (moves.length == 0) {
            for (Field field : GameBoard.board) {
                moves[moves.length] = field.getCoordinate();
            }
        }

        return moves;
    }

    // TODO: Backtrack 10 moves forward in time
    public int backtrack(int[] x) {

        for (int i = 0; i < 10; i++) {

            for (int y : x) {
                minimax(y);
            }

        }

        return 0;
    }
}
