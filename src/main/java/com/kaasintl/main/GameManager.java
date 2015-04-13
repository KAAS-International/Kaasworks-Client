package main.java.com.kaasintl.main;

import main.java.com.kaasintl.api.Field;
import main.java.com.kaasintl.api.GameBoard;

import java.util.ArrayList;

/**
 * Created by David on 4-4-2015.
 */
public class GameManager
{
    ArrayList<String> playerList;
    ArrayList<String> gameList;
    private Thread netManager;
    private GUI gui;


    /**
     * Creates an instance of the GameManager, with no GUI provided. This will cause it to make a GUI itself
     */
    public GameManager() {
        netManager = new NetManager(this);
        netManager.start();
        playerList = new ArrayList<>();
        gameList = new ArrayList<>();

        gui = new GUI();
    }

    /**
    /**
     * Creates an instance of the GameManager, with the provided GUI object as it's gui
     *
     * @param gui The GUI
     */
    public GameManager(GUI gui)
    {
        this.gui = gui;
        netManager = new NetManager(this);
        netManager.start();
        playerList = new ArrayList<>();
        gameList = new ArrayList<>();
    }

    public synchronized void receive(String s) {

    }

    public synchronized void receive(ArrayList<String> a) {
        if(a.get(0).equals("playerList")) {
            a.remove(0);
            playerList = a;
        } else if(a.get(0).equals("gameList")) {
            a.remove(0);
            gameList = a;
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

    // TODO: Accept other player's challenge
    public boolean acceptChallenge()
    {
        return true;
    }

    // TODO: Challenge other player
    public boolean challenge(String player)
    {
        return true;
    }

    // TODO: Get the list of players
    public ArrayList<String> getPlayerList()
    {
        //netManager.getPlayerList();
        return playerList;
    }

    // TODO: Get list of supported games
    public ArrayList<String> getGameList()
    {
        return gameList;
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
        return true;
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

}
