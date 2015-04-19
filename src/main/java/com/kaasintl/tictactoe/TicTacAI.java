package main.java.com.kaasintl.tictactoe;

import main.java.com.kaasintl.api.AI;
import main.java.com.kaasintl.api.Field;
import main.java.com.kaasintl.api.RuleManager;
import main.java.com.kaasintl.main.GameManager;

/**
 * Created by Niek on 15-4-2015.
 */
public class TicTacAI extends AI {

    TicTacRuleManager ruleManager;
    GameManager gameManager;

    public TicTacAI(RuleManager r, GameManager g){
        this.ruleManager = (TicTacRuleManager) r;
        ruleManager.clearBoard();
        this.gameManager = g;
    }

    @Override
    public Field nextMove(){
	    return nextMove(TicTacRuleManager.COMPUTER);
    }

    public Field nextMove( int side) {
        int opp;              // The other side
        TicTacField reply;    // Opponent's best reply
        int simpleEval;       // Result of an immediate evaluation
        int coordinate = 0;
        int value;

	    if ((simpleEval = ruleManager.positionValue()) != TicTacRuleManager.UNCLEAR)
		    return new TicTacField(simpleEval);

	    if (side == TicTacRuleManager.OPPONENT) {
		    value = TicTacRuleManager.COMPUTER_WIN;
		    opp = TicTacRuleManager.COMPUTER;
	    } else {
		    value = TicTacRuleManager.OPPONENT_WIN;
		    opp = TicTacRuleManager.OPPONENT;
	    }

        for (int i = 0; i < 9; i++) {
            if (ruleManager.squareIsEmpty(i)) {
                place(i, side);
                if (side == TicTacRuleManager.OPPONENT) {
                    reply = (TicTacField)nextMove(opp);
                    place(i, TicTacRuleManager.EMPTY);
                    if (reply.value < value) {
                        coordinate = i;
                        value = reply.value;
                    }
                } else {
                    reply = (TicTacField)nextMove(opp);
                    place(i, TicTacRuleManager.EMPTY);
                    if (reply.value > value) {
                        coordinate = i;
                        value = reply.value;
                    }
                }
            }
        }
        return new TicTacField(value, coordinate);
   }

    public void place( int coordinate, int piece )
    {
        gameManager.getGameBoard().getField(coordinate).setValue(piece);
    }

}
