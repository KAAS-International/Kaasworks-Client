package main.java.com.kaasintl.tictactoe;

import main.java.com.kaasintl.api.Field;

/**
 * Created by Niek on 15-4-2015.
 */
public class TicTacAI {

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
        clearBoard();
    }
//    @Override
//    public int nextMove( int side) {
//        int opp;              // The other side
//        TicTacField reply;           // Opponent's best reply
//        int simpleEval;       // Result of an immediate evaluation
//        int bestRow = 0;
//        int bestColumn = 0;
//        int value;
//
//        if( ( simpleEval = positionValue( ) ) != UNCLEAR )
//     //       return new TicTacAI(simpleEval);
//
//        if(side == OPPONENT) {
//            value = COMPUTER_WIN;
//            opp = COMPUTER;
//        } else {
//            value = OPPONENT_WIN;
//            opp = OPPONENT;
//        }
//
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (squareIsEmpty(i, j)) {
//
//                    place(i, j, side);
//                    if (side == OPPONENT) {
//                        reply = nextMove(opp);
//                        place(i,j,EMPTY);
//                        if (reply.val < value) {
//                            bestRow = i;
//                            bestColumn = j;
//                            value = reply.val;
//                        }
//                    } else {
//        //                reply = nextMove(opp);
//                        place(i,j,EMPTY);
//                        if (reply.val > value) {
//                            bestRow = i;
//                            bestColumn = j;
//                            value = reply.val;
//                        }
//                    }
//                }
//            }
//        }
//
//   //    return new TicTacField(value, bestRow, bestColumn);
//
//        return
//   }

    // Compute static value of current position (win, draw, etc.)
    public int positionValue( )
    {
        if (ruleManager.isAWin(OPPONENT)) {
            return OPPONENT_WIN;
        } else if (ruleManager.isAWin(COMPUTER)) {
            return COMPUTER_WIN;
        } else if (boardIsFull()) {
            return DRAW;
        } else {
            return UNCLEAR;
        }
    }



    private boolean squareIsEmpty( int row, int column )
    {
        return board[ row ][ column ] == EMPTY;
    }

    public void place( int row, int column, int piece )
    {
        board[ row ][ column ] = piece;
    }

    private boolean boardIsFull( )
    {
        for(int i  = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(squareIsEmpty(i,j))
                    return false;
            }
        }
        return true;
    }

    // Simple supporting routines
    public void clearBoard( )
    {
        for(int i = 0; i < 3; i++) {
            board[0][i] = 2;
            board[1][i] = 2;
            board[2][i] = 2;
        }
    }



}
