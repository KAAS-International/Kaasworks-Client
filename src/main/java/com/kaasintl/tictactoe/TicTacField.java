package main.java.com.kaasintl.tictactoe;

import main.java.com.kaasintl.api.Field;

/**
 * Created by Niek on 15-4-2015.
 */
public class TicTacField extends Field {

    int coordinate;

    public TicTacField( int v ) {
        this( v, 0);
    }

    public TicTacField( int v, int c){
	    value = v;
	    coordinate = c;

	    switch (value) {
		    case 0:
			    token = " ";
			    break;
		    case 1:
			    token = "X";
			    break;
		    case 2:
			    token = "O";
	    }
    }

}
