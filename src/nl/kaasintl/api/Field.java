package nl.kaasintl.api;

import java.lang.Object;

/**
 * Created by David on 4-4-2015.
 */
public abstract class Field {
    // Field number on board
    public int number = 0;

    // Value used in minimax
    public int value = 0;

    public int getNumber() {
        return number;
    }

    public int getValue() {
        return value;
    }
}