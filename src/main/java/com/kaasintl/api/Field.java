package main.java.com.kaasintl.api;

/**
 * Created by Kevin on 4-4-2015.
 */
public abstract class Field {
    public int value = 0; // Value used in Minimax() methods
    public int coordinate = 0; // Coordinate of field on 2D board

    // Determine if field is used, and if so, by who
    public enum STATE {
        Open,
        Friendly,
        Enemy
    }

    public STATE state;

    public STATE getState() { return state; }

    public String toString() {
        return state.toString();
    }

    public int getValue() {
        return value;
    }

    public int getCoordinate() { return coordinate; }
}