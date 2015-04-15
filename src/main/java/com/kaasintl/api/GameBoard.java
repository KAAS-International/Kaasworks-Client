package main.java.com.kaasintl.api;


import java.util.ArrayList;

/**
 * Created by Kevin on 4-4-2015.
 */
public abstract class GameBoard {

    public RuleManager ruleManager;

    public ArrayList<Field> board = new ArrayList<Field>();

    public GameBoard(RuleManager ruleManager)
    {
        this.ruleManager = ruleManager;
    }

    public ArrayList<Field> getBoard()
    {
        return board;
    }
}
