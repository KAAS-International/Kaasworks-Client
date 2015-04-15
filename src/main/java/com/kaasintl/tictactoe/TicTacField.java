package main.java.com.kaasintl.tictactoe;

import main.java.com.kaasintl.api.AI;

/**
 * Created by Niek on 15-4-2015.
 */
public class TicTacField extends AI{
    public int best;

    public int nextMove() {
        return best;
    }
}
