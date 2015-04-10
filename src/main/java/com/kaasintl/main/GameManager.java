package main.java.com.kaasintl.main;

import main.java.com.kaasintl.api.Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by David on 4-4-2015.
 */
public class GameManager
{
    private Thread netManager;

    // Constructor
    public GameManager() {
        netManager = new NetManager();
        netManager.start();
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
    public List<String> getPlayerList()
    {
        ArrayList list = new ArrayList();

        list.add("Gert");
        list.add("Albert");
        list.add("Frederik");

        long seed = System.nanoTime();

        Collections.shuffle(list);

        return list;
    }

    // TODO: Get list of supported games
    public List<String> getGameList()
    {
        ArrayList list = new ArrayList();

        list.add("Mens-erger-je-wel");
        list.add("Botsauto's");

        return list;
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
