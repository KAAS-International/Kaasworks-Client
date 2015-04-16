package main.java.com.kaasintl.tictactoe;

import main.java.com.kaasintl.api.Field;
import main.java.com.kaasintl.api.GameBoard;
import main.java.com.kaasintl.api.RuleManager;

import java.util.ArrayList;

/**
 * Created by Niek on 15-4-2015.
 */
public class TicTacBoard extends GameBoard {

    public RuleManager ruleManager;

    public ArrayList<Field> board = new ArrayList<Field>();

    public TicTacBoard(RuleManager ruleManager) {
        super(ruleManager);
        this.ruleManager = ruleManager;
    }

    public ArrayList<Field> getBoard() { return board; }
}