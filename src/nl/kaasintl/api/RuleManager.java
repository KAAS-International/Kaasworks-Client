package nl.kaasintl.api;

/**
 * Created by David on 4-4-2015.
 */
public abstract class RuleManager {

    // Validate if a move is legal according to game rules
    public boolean isValid(int i) { return true; }

    // Validate if a move is within the game board borders
    // TODO: This method essentially is the same as isEmpty() in field, we should consider removing it
    public boolean isOccupied(int i) {
        if (GameBoard.board.get(i).isEmpty()) return false;

        return true;
    }

    // Validate if a move conflicts with an already occupied field
    public boolean isReal(int i) {
        if (i < 0) return false;
        if (i > GameBoard.board.size()-1) return false;

        return true;
    }

    // Validate if side has met win conditions
    // TODO: Replace player with actual object
    public boolean isWin(String player) {
        return true;
    }

    // TODO: See above, same issue here
    public boolean isDraw(String player) {
        return true;
    }
}
