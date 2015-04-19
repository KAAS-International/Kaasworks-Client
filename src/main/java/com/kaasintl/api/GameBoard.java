package main.java.com.kaasintl.api;


import java.util.ArrayList;

/**
 * Created by Kevin on 4-4-2015.
 */
public abstract class GameBoard {

	public int boardHeight = 0;
	public RuleManager ruleManager;

    public ArrayList<Field> board = new ArrayList<Field>();

    public GameBoard(RuleManager ruleManager)
    {
        this.ruleManager = ruleManager;
    }

    public ArrayList<Field> getBoard()
    {
        return board;
    }

    public Field getField(int index) { return board.get(index); }

	public int getBoardHeight()
	{
		return boardHeight;
	}

	public void setBoardHeight(int boardHeight)
	{
		this.boardHeight = boardHeight;
	}
}
