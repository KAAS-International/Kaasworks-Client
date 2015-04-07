package nl.kaasintl.main;

/**
 * Created by David on 4-4-2015.
 */
public class GameManager
{
    public GameManager() {}

    public boolean isTurn() {
        return true;
    }

    public void reset() {}

    public boolean isValid(Field f) {
        if (f == f) return true;
        return false;
    }



}
