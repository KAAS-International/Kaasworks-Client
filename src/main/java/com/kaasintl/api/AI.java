package main.java.com.kaasintl.api;

import java.util.Map;

/**
 * Created by Kevin on 4-4-2015.
 */
public abstract class AI {
    public Field best;

    public Field nextMove() {
        return best;
    }

}