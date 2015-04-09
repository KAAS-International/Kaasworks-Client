package main.com.kaasintl.reversi;

import main.com.kaasintl.api.AI;
import main.com.kaasintl.api.Field;
import main.com.kaasintl.api.GameBoard;

/**
 * Created by Kevin on 9-4-2015.
 */
public class ReversiAI extends AI
{
    public int[] moves;

    @Override
    public int nextMove() {
        return 0;
    }

    // TODO: This method only includes fields with a positive value, needs to be extended with backtracking to include possible bad moves
    public int[] minimax() {
        for (Field field : GameBoard.board) {
            if (field.getValue() > 0) {
                moves[moves.length] = field.getCoordinate();
            }
        }

        return moves;
    }

    // TODO: Backtrack 10 moves forward in time
    public int[] backtrack() {

        for (int i = 0; i < 10; i++) {
            // Do something (include method minimax() here?)
        }

        return moves;
    }
}
