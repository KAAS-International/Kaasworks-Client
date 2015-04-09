package nl.kaasintl.reversi;

import nl.kaasintl.api.RuleManager;

/**
 * Created by Kevin on 9-4-2015.
 */
public class ReversiRuleManager extends RuleManager {

    @Override
    public boolean isValid(int i) {
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
