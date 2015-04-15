package main.java.com.kaasintl.reversi;

import main.java.com.kaasintl.api.Field;
import main.java.com.kaasintl.api.GameBoard;
import main.java.com.kaasintl.api.RuleManager;

import java.util.ArrayList;

/**
 * Created by Kevin on 9-4-2015.
 */
public class ReversiGameBoard extends GameBoard {

    public RuleManager ruleManager;

    public ArrayList<Field> board = new ArrayList<Field>();

    public ReversiGameBoard(RuleManager ruleManager)
    {
        super(ruleManager);
        this.ruleManager = ruleManager;
    }

    public ArrayList<Field> getBoard()
    {
        return board;
    }
}
