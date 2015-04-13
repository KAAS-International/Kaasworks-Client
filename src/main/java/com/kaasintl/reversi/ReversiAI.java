package main.java.com.kaasintl.reversi;

import main.java.com.kaasintl.api.AI;
import main.java.com.kaasintl.api.Field;
import main.java.com.kaasintl.api.GameBoard;
import java.util.Map;

/**
 * Created by Kevin on 9-4-2015.
 */
public class ReversiAI extends AI
{
    public int best;
    public int bestValue;
    public Map<Field, Integer> moves; // Field, Value (using .getValue() method)
    public Map<Field, Integer> possibilities; // Field, moveValue (using math)

    @Override
    public int nextMove() {

        // TODO: Call minimax() method here

        for (Map.Entry<Field, Integer> possibility : possibilities.entrySet()) {
            if (possibility.getKey().getValue() > bestValue) {
                bestValue = possibility.getValue();
                best = possibility.getKey().getCoordinate();
            }
        }
        return best;
    }

    public Map minimax() {
        for (Field field : GameBoard.board) {
            moves.put(field, field.getValue()); // TODO: Add minimax to this method
        }

        for (Map.Entry<Field, Integer> move : moves.entrySet()) {
            backtrack(move.getKey().getCoordinate());
        }

        return moves;
    }

    public int backtrack(int i) {
        for (Map.Entry<Field, Integer> possilibity : possibilities.entrySet()) {
            // TODO: Check moveValue
        }

        return 0;
    }
}
