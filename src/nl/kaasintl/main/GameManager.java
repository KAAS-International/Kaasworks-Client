package nl.kaasintl.main;

/**
 * Created by David on 4-4-2015.
 */
public class GameManager
{
    // Constructor
    public GameManager() {}

    // TODO: Return if it is your turn
    public boolean isTurn() {
        return true;
    }

    // TODO: Reset game
    public void reset() {}

    // TODO: Check if move is valid
    public boolean isValid(Field f) {
        if (f == f) return true;
        return false;
    }

}
