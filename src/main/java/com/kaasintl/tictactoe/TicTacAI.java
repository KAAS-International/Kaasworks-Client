package main.java.com.kaasintl.tictactoe;

import main.java.com.kaasintl.api.AI;
import main.java.com.kaasintl.api.Field;

/**
 * Created by Niek on 15-4-2015.
 */
public class TicTacAI extends AI {

    private static final int OPPONENT     = 0;
    private static final int COMPUTER     = 1;
    public  static final int EMPTY        = 2;

    public  static final int OPPONENT_WIN = 0;
    public  static final int DRAW         = 1;
    public  static final int UNCLEAR      = 2;
    public  static final int COMPUTER_WIN = 3;
    private int [ ] [ ] board = new int[ 3 ][ 3 ];
    TicTacRuleManager ruleManager;

    public TicTacAI(){
        ruleManager.clearBoard();
    }

    @Override
    public Field nextMove(){
        TicTacField best=(TicTacField)nextMove(COMPUTER);
        return best;
    }

    public Field nextMove( int side) {
        int opp;              // The other side
        TicTacField reply;           // Opponent's best reply
        int simpleEval;       // Result of an immediate evaluation
        int bestRow = 0;
        int bestColumn = 0;
        int value;

        if( ( simpleEval = ruleManager.positionValue() ) != UNCLEAR )
            return new TicTacField(simpleEval);

        if(side == OPPONENT) {
            value = COMPUTER_WIN;
            opp = COMPUTER;
        } else {
            value = OPPONENT_WIN;
            opp = OPPONENT;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ruleManager.squareIsEmpty(i, j)) {

                    place(i, j, side);
                    if (side == OPPONENT) {
                        reply = (TicTacField)nextMove(opp);
                        place(i,j,EMPTY);
                        if (reply.val < value) {
                            bestRow = i;
                            bestColumn = j;
                            value = reply.val;
                        }
                    } else {
                        reply = (TicTacField)nextMove(opp);
                        place(i,j,EMPTY);
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
        board[ row ][ column ] = piece;
    }

}
