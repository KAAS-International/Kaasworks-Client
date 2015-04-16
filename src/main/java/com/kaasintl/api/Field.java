package main.java.com.kaasintl.api;

/**
 * Created by Kevin on 4-4-2015.
 */
public abstract class Field {
    public int value = 0; // Value used in Minimax() methods
    public int coordinate = 0; // Coordinate of field on 2D board
    public String token;
    public STATE state;

    public STATE getState() { return state; }

	public void setState(STATE s)
	{
		state = s;
		token = determineToken(s);
	}

	public String determineToken(STATE state)
	{
		switch (state)
		{
			case Open:
				return " ";
			case Friendly:
				return "X";
			case Enemy:
				return "O";
			default:
				return "?";
		}
	}

    public String toString() { return token; }
    public int getValue() { return value; }

    public void setValue(int i) { value = i; }

	public int getCoordinate()
	{
		return coordinate;
	}

    public void setCoordinate(int i) { coordinate = i; }

	// Determine if field is used, and if so, by who
	public enum STATE
	{
		Open,
		Friendly,
		Enemy
	}


}