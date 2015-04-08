package nl.kaasintl.api;


import java.util.ArrayList;

/**
 * Created by David on 4-4-2015.
 */
public abstract class GameBoard {
    // Gameboard
    public static ArrayList<Field> board = new ArrayList<Field>();

    public ArrayList<Field> getBoard() {
        return board;
    }

}
