package main.java.com.kaasintl.reversi;

import main.java.com.kaasintl.reversi.ReversiField;
import main.java.com.kaasintl.api.GameBoard;

import java.util.ArrayList;

/**
 * Created by Kevin on 9-4-2015.
 */
public class ReversiGameBoard extends GameBoard {
    public static ArrayList<ReversiField> board = new ArrayList<ReversiField>();

    public ArrayList<ReversiField> getBoard() {
        return board;
    }
}
