package main.java.com.kaasintl.reversi;

import main.java.com.kaasintl.api.AI;
import main.java.com.kaasintl.api.Field;
import main.java.com.kaasintl.api.RuleManager;
import main.java.com.kaasintl.main.GameManager;

import java.util.ArrayList;

/**
 * Created by Kevin on 9-4-2015.
 */
public class ReversiAI extends AI
{
    private Field best;
    private GameManager gameManager;
    private ReversiRuleManager ruleManager;
    private ArrayList<Field> moves = new ArrayList<Field>(); // Stores Field + fieldValue
    private ArrayList<Field> options = new ArrayList<Field>(); // Stores possible moves determined by minimax()

    public ReversiAI(GameManager gm, RuleManager r) {
        this.gameManager = gm;
        this.ruleManager = (ReversiRuleManager) r;
    }

    @Override
    public Field nextMove() {
        for (Field move : gameManager.getGameBoard().getBoard()) {
            if (ruleManager.isValid(move.getCoordinate())) {
                moves.add(move);
            }
        }

        /* for (Field move : moves) {
            options.add(minimax(move));
        }

        return options.get(0); */

        for (Field move : moves) {
            if (best == null) {
                best = move;
            } else if (move.getValue() > best.getValue()) {
                best = move;
            }
        }

        ruleManager.flip(best.getCoordinate());
        return best;
    }

    /* public Field minimax(Field move) {
        return null;
    } */
}
