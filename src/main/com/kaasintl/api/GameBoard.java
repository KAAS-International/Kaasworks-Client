package main.com.kaasintl.api;


import java.util.ArrayList;

/**
 * Created by Kevin on 4-4-2015.
 */
public abstract class GameBoard {
    public static ArrayList<Field> board = new ArrayList<Field>();

    public ArrayList<Field> getBoard() {
        return board;
    }

}
