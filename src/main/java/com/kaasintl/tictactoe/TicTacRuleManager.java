package main.java.com.kaasintl.tictactoe;

import main.java.com.kaasintl.api.RuleManager;

/**
 * Created by Niek on 15-4-2015.
 */
public class TicTacRuleManager extends RuleManager {

    @Override
    public boolean isValid(int i){

        return true;
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
}
