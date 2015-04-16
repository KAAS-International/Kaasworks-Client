package main.java.com.kaasintl.tictactoe;

import main.java.com.kaasintl.api.Field;
import main.java.com.kaasintl.api.GameBoard;
import main.java.com.kaasintl.api.RuleManager;

import java.util.ArrayList;

/**
 * Created by Niek on 15-4-2015.
 */
public class TicTacBoard extends GameBoard {

    public TicTacBoard(RuleManager ruleManager) {
        super(ruleManager);
        this.ruleManager = ruleManager;
	    boardHeight = 3;

	    //populate board
	    for (int i = 0; i < 9; i++) {
		    board.add(new TicTacField(1));
	    }
    }

    public ArrayList<Field> getBoard() { return board; }

    public Field getField(int index) {
        return board.get(index);
    }
}
