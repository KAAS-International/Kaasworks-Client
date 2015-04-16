package main.java.com.kaasintl.reversi;

import main.java.com.kaasintl.api.Field;
import main.java.com.kaasintl.api.GameBoard;
import main.java.com.kaasintl.api.RuleManager;

import java.util.ArrayList;

/**
 * Created by Kevin on 9-4-2015.
 */
public class ReversiGameBoard extends GameBoard {

    public ReversiGameBoard(RuleManager ruleManager)
    {
        super(ruleManager);
        this.ruleManager = ruleManager;

	    boardHeight = 8;
	    //init board

	    for (int i = 0; i < 64; i++) {
		    ReversiField f = new ReversiField();
		    getBoard().add(f);
		    f.setState(Field.STATE.Open);
	    }

	    getBoard().get(27).setState(Field.STATE.Friendly);
	    getBoard().get(36).setState(Field.STATE.Friendly);

	    getBoard().get(28).setState(Field.STATE.Enemy);
	    getBoard().get(35).setState(Field.STATE.Enemy);
    }

    public ArrayList<Field> getBoard()
    {
        return board;
    }
}
