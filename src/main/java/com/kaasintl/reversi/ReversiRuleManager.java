package main.java.com.kaasintl.reversi;

import main.java.com.kaasintl.api.RuleManager;

/**
 * Created by Kevin on 9-4-2015.
 */
public class ReversiRuleManager extends RuleManager {

    @Override
    public boolean isValid(int i) {

        for (ReversiField field : ReversiGameBoard.board) {
            if (field.getState() == 0) { // TODO: This does not work yet, check if field is adjacent to field with state 2. Also, check if that field has our field in a line. THIS IS CRAP. JUST SAYING.
                return true;
            }
        }

        return true;
    }

    @Override
    public boolean isWin(String player) {
        return true;
    }

    @Override
    public boolean isDraw(String player) {
        return true;
    }
}
