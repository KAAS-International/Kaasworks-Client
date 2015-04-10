package main.java.com.kaasintl.api;

/**
 * Created by Kevin on 4-4-2015.
 */
public abstract class Field {
    public int value = 0;
    public int coordinate = 0;
    public boolean empty = true;

    public int getValue() {
        return value;
    }

    public boolean isEmpty() { return empty; }

    public int getCoordinate() { return coordinate; }
}