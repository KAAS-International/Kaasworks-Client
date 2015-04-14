package main.java.com.kaasintl.main;

import main.java.com.kaasintl.api.Field;
import main.java.com.kaasintl.api.GameBoard;
import main.java.com.kaasintl.api.RuleManager;

import java.util.ArrayList;

/**
 * Created by David on 4-4-2015.
 */
public class GameManager
{
    ArrayList<String> playerList;
    ArrayList<String> gameList;
    RuleManager ruleManager;
    private NetManager netManager;
    private GUI gui;
    private String opponent;
    private String gameType;
    private boolean isTurn;
    private String turnMessage;
    private int isValid = 0;
    private GameBoard gameBoard;

    /**
     * Creates an instance of the GameManager, with no GUI provided. This will cause it to make a GUI itself
     */
    public GameManager() {
        netManager = new NetManager(this);
        playerList = new ArrayList<>();
        gameList = new ArrayList<>();

        gui = new GUI();
    }

    /**
     * Creates an instance of the GameManager, with the provided GUI object as it's gui
     *
     * @param gui The GUI
     */
    public GameManager(GUI gui)
    {
        this.gui = gui;
        netManager = new NetManager(this);
        playerList = new ArrayList<>();
        gameList = new ArrayList<>();
    }

    /**
     * Returns the current game's gameboard
     *
     * @return
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * Sets the current game's gameboard to the provided Gameboard
     *
     * @param gameBoard
     */
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * Log into server with a specific name
     *
     * @param name
     */
    public void login(String name) {
        netManager.login(name);
    }

    public boolean getValidation() {
        boolean val = false;

        while(this.isValid == 0) {;}

        if(this.isValid == 1) {
            val = true;
            this.isValid = 0;
        } else {
            val = false;
            this.isValid = 0;
        }
        return val;
    }

    /**
     * Validates the
     * @param val
     */
    public void setValidation(String val) {
        if(val.equals("ok")) {
            this.isValid = 1;
        } else {
            this.isValid = -1;
        }
    }

    // TODO: Subscribe to Gametype
    public boolean subscribe(String game)
    {
        return true;
    }

    // TODO: Quit the game
    public boolean quit()
    {
        return true;
    }

    /**
     * sets the challenge to be accepted
     * @param challenger
     * @param challengeNumber
     * @param gameType
     */
    public void setChallenge(String challenger, int challengeNumber, String gameType) {

    }

    // TODO: Accept other player's challenge
    public boolean acceptChallenge()
    {
        return true;
    }

    // TODO: Challenge other player
    public boolean challenge(String player, String game)
    {
        return true;
    }

    // TODO: Get the list of players
    public ArrayList<String> getPlayerList()
    {
        //netManager.getPlayerList();
        return playerList;
    }

    /**
     * Sets the playerlist field
     *
     * @param playerList
     */
    public void setPlayerList(ArrayList<String> playerList)
    {
        this.playerList = playerList;
    }

    /**
     * Gets the current rulemanager
     * @return the current RuleManager
     */
    public RuleManager getRuleManager()
    {
        return ruleManager;
    }

    /**
     * Sets the rulemanager
     *
     * @param ruleManager
     */
    public void setRuleManager(RuleManager ruleManager)
    {
        this.ruleManager = ruleManager;
    }

    /**
     * Ends the game with a certain result
     * @param winloss 1 means win, 0 means draw, -1 means loss
     * @param player1Score
     * @param player2Score
     * @param message
     */
    public void endGame(int winloss, int player1Score, int player2Score, String message)
    {
        switch (winloss)
        {

        }
    }

    /**
     * Notify gameManager when new game starts
     * TODO: notify GUI of new game and move
     * @param playerToMove
     * @param gameType
     * @param opponent
     */
    public void setMatch(String playerToMove, String gameType, String opponent) {
        setOpponent(opponent);
        if(!playerToMove.equals(opponent)) {

        }
    }

    // TODO: Get list of supported games
    public ArrayList<String> getGameList()
    {
        return gameList;
    }

    /**
     * Sets the gameList variable
     *
     * @param gameList
     */
    public void setGameList(ArrayList<String> gameList)
    {
        this.gameList = gameList;
    }

    // TODO: Forfeit the game
    public boolean forfeit()
    {
        return true;
    }

    // TODO: Make a move
    public boolean makeMove(int move)
    {
        return true;
    }

    // TODO: Return if it is your turn
    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(String s) {
        this.turnMessage = s;
        // TODO: set isTurn false somewhere
        this.isTurn = true;
    }

    // TODO: Return the game's board
    public GameBoard getGameboard()
    {
        return null;
    }

    // TODO: Reset game
    public boolean reset()
    {
        return true;
    }

    // TODO: Check if move is valid
    public boolean isValid(Field f) {
        if (f == f) return true;
        return false;
    }

    /**
     * Gets opponent name
     * @return String - opponent
     */
    public String getOpponent() {
        return opponent;
    }

    /**
     * Sets opponent name
     * @param opponent
     */
    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    /**
     * Returns the current game type
     * @return
     */
    public String getGameType() {
        return gameType;
    }

    /**
     * Sets the current game type
     * @param gameType
     */
    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    /**
     * processes the move just played
     * @param player
     * @param move
     * @param details
     */
    public void setMove(String player, int move, String details) {

    }
}
