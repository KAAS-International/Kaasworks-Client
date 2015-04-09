package nl.kaasintl.reversi;

import nl.kaasintl.api.AI;
import nl.kaasintl.api.Field;
import nl.kaasintl.api.GameBoard;

/**
 * Created by Kevin on 9-4-2015.
 */
public class ReversiAI extends AI {

    @Override
    public int nextMove() {
        return 0;
    }

    // TODO: Use minimax on all fields
    public int minimax() {
        for (Field field : GameBoard.board) {
            // Do something
        }

        return 0;
    }

    // TODO: Backtrack 10 moves forward in time
    public int[] backtrack() {
        return moves;
    }
}
