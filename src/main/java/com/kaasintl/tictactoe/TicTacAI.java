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
        TicTacField best = (TicTacField)nextMove(ruleManager.COMPUTER);
        return best;
    }

    public Field nextMove( int side) {
        int opp;              // The other side
        TicTacField reply;    // Opponent's best reply
        int simpleEval;       // Result of an immediate evaluation
        int bestRow = 0;
        int bestColumn = 0;
        int value;

        if( ( simpleEval = ruleManager.positionValue() ) != ruleManager.UNCLEAR )
            return new TicTacField(simpleEval);

        if(side == ruleManager.OPPONENT) {
            value = ruleManager.COMPUTER_WIN;
            opp = ruleManager.COMPUTER;
        } else {
            value = ruleManager.OPPONENT_WIN;
            opp = ruleManager.OPPONENT;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ruleManager.squareIsEmpty(i, j)) {

                    place(i, j, side);
                    if (side == ruleManager.OPPONENT) {
                        reply = (TicTacField)nextMove(opp);
                        place(i,j,ruleManager.EMPTY);
                        if (reply.val < value) {
                            bestRow = i;
                            bestColumn = j;
                            value = reply.val;
                        }
                    } else {
                        reply = (TicTacField)nextMove(opp);
                        place(i,j,ruleManager.EMPTY);
                        if (reply.val > value) {
                            bestRow = i;
                            bestColumn = j;
                            value = reply.val;
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
