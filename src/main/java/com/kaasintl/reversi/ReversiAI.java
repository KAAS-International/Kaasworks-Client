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

    @Override
    public int nextMove() {
        for (Field field : GameBoard.board) {
            if (0 == 0) { // TODO: This should call RuleManager to verify if field is valid
                minimax(field.getCoordinate());
            }
        }

        return 0; // Return best possible move
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
    public int[] backtrack() {

        for (int i = 0; i < 10; i++) {

            for (int move : moves) {
                minimax(move);
            }

        }

        return moves;
    }
}
