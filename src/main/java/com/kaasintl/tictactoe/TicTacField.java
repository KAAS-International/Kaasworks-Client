package main.java.com.kaasintl.tictactoe;

import main.java.com.kaasintl.api.Field;

/**
 * Created by Niek on 15-4-2015.
 */
public class TicTacField extends Field {

    int row;
    int column;
    int val;

    public TicTacField( int v ) {
        this( v, 0, 0 );
    }

    public TicTacField( int v, int r, int c ){
        val = v;
        row = r;
        column = c;
    }

}
