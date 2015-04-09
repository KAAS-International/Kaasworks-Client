package nl.kaasintl.api;

/**
 * Created by Kevin on 4-4-2015.
 */
public abstract class AI {
    public int[] moves;
    public int bestmove;
    public int movevalue;

    // TODO: Determine next move
    public int nextMove() {
        minimax();
        backtrack();

        // Determine best move here
        return bestmove;
    }

    // TODO: Use minimax on all fields
    public int minimax() {

        movevalue = 0;
        bestmove = 0;

        // TODO: Extend to not use all fields, only occupied
        for (Field field : GameBoard.board) {
            if (field.getValue() > movevalue) {
                movevalue = field.getValue();
                bestmove = field.getNumber();
            }
        }

        return bestmove;
    }

    // TODO: Backtrack 10 moves forward in time
    public int[] backtrack() {
        return moves;
    }

}