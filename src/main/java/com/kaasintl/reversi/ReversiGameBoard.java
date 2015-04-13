package main.java.com.kaasintl.reversi;

import main.java.com.kaasintl.api.Field;
import main.java.com.kaasintl.api.GameBoard;

import java.util.ArrayList;

/**
 * Created by Kevin on 9-4-2015.
 */
public class ReversiGameBoard extends GameBoard {
    public static ArrayList<Field> board = new ArrayList<Field>();

    public ArrayList<Field> getBoard()
    {
        return board;
    }
}
