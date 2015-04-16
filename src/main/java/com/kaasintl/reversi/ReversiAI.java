package main.java.com.kaasintl.reversi;

import main.java.com.kaasintl.api.AI;
import main.java.com.kaasintl.api.Field;
import main.java.com.kaasintl.main.GameManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Kevin on 9-4-2015.
 */
public class ReversiAI extends AI
{
    private int best;
    private GameManager gm;
    private HashMap<Field, Integer> moves = new HashMap<Field, Integer>(); // Stores Field + fieldValue
    private HashMap<Field, Integer> fields = new HashMap<Field, Integer>(); // Stores Field + coordinate
    private ArrayList<Field> options = new ArrayList<Field>(); // Stores possible moves determined by minimax()

    public ReversiAI(GameManager gm) {
        this.gm = gm;
    }

    @Override
    public Field nextMove() {
        for (Field move : gm.getGameBoard().getBoard()) {
            if (gm.getRuleManager().isValid(move.getCoordinate())) {
                moves.put(move, move.getValue());
                fields.put(move, move.getCoordinate());
            }
        }

        Iterator it = moves.entrySet().iterator();

        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            options.add(minimax((Field) pair.getKey()));
            it.remove();
        }

        /* for (HashMap.Entry<Field, Integer> move : moves.entrySet()) {
            options.add(minimax(move.getKey()));
        } */

        return options.get(0);
    }

    public Field minimax(Field move) {
        return null;
    }
}
