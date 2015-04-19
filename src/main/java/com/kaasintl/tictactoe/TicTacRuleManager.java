package main.java.com.kaasintl.tictactoe;

import main.java.com.kaasintl.api.RuleManager;
import main.java.com.kaasintl.main.GameManager;

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
    private GameManager gameManager;

    public TicTacRuleManager(GameManager g) {
        this.gameManager = g;
    }

    @Override
    public boolean isValid(int i){
        for(i = 0; i < 9; i++){
            if(i > 9 || i < 0){
                return false;
            }
        }
        return true;
    }

    public boolean isAWin( int side ) {
        if ((gameManager.getGameBoard().getField(0).getValue() == side) && (gameManager.getGameBoard().getField(4).getValue() == side) && (gameManager.getGameBoard().getField(8).getValue() == side)) {
            return true;
        } else if ((gameManager.getGameBoard().getField(2).getValue() == side) && (gameManager.getGameBoard().getField(4).getValue() == side) && (gameManager.getGameBoard().getField(6).getValue() == side)) {
            return true;
        } else if ((gameManager.getGameBoard().getField(0).getValue() == side) && (gameManager.getGameBoard().getField(3).getValue() == side) && (gameManager.getGameBoard().getField(6).getValue() == side)) {
            return true;
        } else if ((gameManager.getGameBoard().getField(1).getValue() == side) && (gameManager.getGameBoard().getField(4).getValue() == side) && (gameManager.getGameBoard().getField(7).getValue() == side)) {
            return true;
        } else if ((gameManager.getGameBoard().getField(2).getValue() == side) && (gameManager.getGameBoard().getField(5).getValue() == side) && (gameManager.getGameBoard().getField(8).getValue() == side)) {
            return true;
        }
        return false;
    }

    // Simple supporting routines
    public void clearBoard( )
    {
        for(int i = 0; i < 9; i++) {
            gameManager.getGameBoard().getField(i).setValue(EMPTY);
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
        for(int i  = 0; i < 9; i++) {
            if(squareIsEmpty(i))
                return false;
        }
        return true;
    }


    public boolean squareIsEmpty(int i)
    {
        return gameManager.getGameBoard().getField(i).getValue() == EMPTY;
    }
}
