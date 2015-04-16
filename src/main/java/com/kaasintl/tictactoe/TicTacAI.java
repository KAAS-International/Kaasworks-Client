package main.java.com.kaasintl.tictactoe;

import main.java.com.kaasintl.api.AI;
import main.java.com.kaasintl.api.Field;
import main.java.com.kaasintl.api.RuleManager;

/**
 * Created by Niek on 15-4-2015.
 */
public class TicTacAI extends AI {

    TicTacRuleManager ruleManager;

    public TicTacAI(RuleManager r){
        this.ruleManager = (TicTacRuleManager) r;
        ruleManager.clearBoard();
    }

    @Override
    public Field nextMove(){
	    TicTacField best = (TicTacField) nextMove(TicTacRuleManager.COMPUTER);
	    return best;
    }

    public Field nextMove( int side) {
        int opp;              // The other side
        TicTacField reply;    // Opponent's best reply
        int simpleEval;       // Result of an immediate evaluation
        int bestRow = 0;
        int bestColumn = 0;
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

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ruleManager.squareIsEmpty(i, j)) {

                    place(i, j, side);
	                if (side == TicTacRuleManager.OPPONENT) {
		                reply = (TicTacField)nextMove(opp);
		                place(i, j, TicTacRuleManager.EMPTY);
		                if (reply.value < value) {
			                bestRow = i;
                            bestColumn = j;
			                value = reply.value;
		                }
                    } else {
                        reply = (TicTacField)nextMove(opp);
		                place(i, j, TicTacRuleManager.EMPTY);
		                if (reply.value > value) {
			                bestRow = i;
                            bestColumn = j;
			                value = reply.value;
		                }
                    }
                }
            }
        }

        return new TicTacField(value, bestRow, bestColumn);

   }

    public void place( int row, int column, int piece )
    {
        ruleManager.board[ row ][ column ] = piece;
    }

}
