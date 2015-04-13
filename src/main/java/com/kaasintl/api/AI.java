package main.java.com.kaasintl.api;

import java.util.Map;

/**
 * Created by Kevin on 4-4-2015.
 */
public abstract class AI {
    public int best;
    public Map<Field, Integer> moves;

    public int nextMove() {
        return best;
    }

    public Map minimax() {
        return moves;
    }

    public int backtrack(int i) {
        return 0;
    }

}