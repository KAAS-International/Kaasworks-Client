package nl.kaasintl.api;

/**
 * Created by David on 4-4-2015.
 */
public abstract class AI {
    public int[] moves;
    public int bestmove;

    // TODO: Determine next move
    public int nextMove() {
        minimax();
        backtrack();

        // Determine best move here

        return bestmove;
    }

    // TODO: Use minimax on all fields
    public int minimax() {
        return 0;
    }

    // TODO: Backtrack 10 moves forward in time
    public int[] backtrack() {
        return moves;
    }

}