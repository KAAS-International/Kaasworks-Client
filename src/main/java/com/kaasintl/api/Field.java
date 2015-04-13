package main.java.com.kaasintl.api;

/**
 * Created by Kevin on 4-4-2015.
 */
public abstract class Field {
    public int value = 0;
    public int coordinate = 0;
    public boolean empty = true;
    public int state; // 0 = open, 1 = occupied (friendly)m 2 = occupied (enemy) TODO: Move to ReversiField

    public int getState() {return state; }

    public int getValue() {
        return value;
    }

    public boolean isEmpty() { return empty; }

    public int getCoordinate() { return coordinate; }
}