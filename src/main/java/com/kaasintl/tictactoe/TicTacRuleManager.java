package main.java.com.kaasintl.tictactoe;

import main.java.com.kaasintl.api.RuleManager;

/**
 * Created by Niek on 15-4-2015.
 */
public class TicTacRuleManager extends RuleManager {

    public static final int OPPONENT     = 0;
    public static final int COMPUTER     = 1;
    public static final int EMPTY        = 2;
    public static final int OPPONENT_WIN = 0;
    public static final int DRAW         = 1;
    public static final int UNCLEAR      = 2;
    public static final int COMPUTER_WIN = 3;
    public int [ ] [ ] board = new int[ 3 ][ 3 ];

    @Override
    public boolean isValid(int i){
        boolean isValid = true;
        for(i = 0; i < 10; i++){
            if(i>9 || i < 0){
                isValid= false;
            }else{
                isValid= true;
            }
        }

        return isValid;
    }

    public boolean isAWin( int side )
    {
        for(int i = 0; i < 3; i++) {
            if((board[i][0] == side) && (board[i][1] == side) && (board[i][2] == side)) {
                return true;
            }
        }

        for(int j = 0; j < 3; j++) {
            if((board[0][j] == side) && (board[1][j] == side) && (board[2][j] == side)) {
                return true;
            }
        }

        if((side == board[0][0]) && (side == board[1][1]) && (side == board[2][2])) {
            return true;
        } else if((side == board[2][0]) && (side == board[1][1]) && (side == board[0][2])) {
            return true;
        }

        return false;
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

    // Compute static value of current position (win, draw, etc.)
    public int positionValue( )
    {
        if (isAWin(OPPONENT)) {
            return OPPONENT_WIN;
        } else if (isAWin(COMPUTER)) {
            return COMPUTER_WIN;
        } else if (boardIsFull()) {
            return DRAW;
        } else {
            return UNCLEAR;
        }
    }

    private boolean boardIsFull( )
    {
        for(int i  = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(squareIsEmpty(i, j))
                    return false;
            }
        }
        return true;
    }


    public boolean squareIsEmpty( int row, int column )
    {
        return board[ row ][ column ] == EMPTY;
    }
}
