package main.java.com.kaasintl.main;

import main.java.com.kaasintl.api.AI;
import main.java.com.kaasintl.api.Field;
import main.java.com.kaasintl.api.GameBoard;
import main.java.com.kaasintl.api.RuleManager;
import main.java.com.kaasintl.reversi.ReversiGameBoard;
import main.java.com.kaasintl.reversi.ReversiRuleManager;
import main.java.com.kaasintl.tictactoe.TicTacAI;
import main.java.com.kaasintl.tictactoe.TicTacBoard;
import main.java.com.kaasintl.tictactoe.TicTacRuleManager;

import java.util.ArrayList;

/**
 * Created by David on 4-4-2015.
 */
public class GameManager
{
	ArrayList<String> playerList;
	ArrayList<String> gameList;
	RuleManager ruleManager;
    AI ai;
	// Connections to other game-components
	private NetManager netManager;
	private GUI gui;
	// local variables
	private String opponent;
	private String gameType;
	private boolean isTurn;
    private boolean aiPlays;
	private String turnMessage;
	private int isValid = 0;
	// Game Components
	private GameBoard gameBoard;
	private String currentGame;

	/**
	 * Creates an instance of the GameManager, with no GUI provided. This will cause it to make a GUI itself
	 */
	public GameManager()
	{
		netManager = new NetManager(this, "145.37.59.19", 7789);
		playerList = new ArrayList<>();
		gameList = new ArrayList<>();

		//TODO: load different games dynamically
		ruleManager = new TicTacRuleManager(this);
		gameBoard = new TicTacBoard(ruleManager);


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

		//TODO: load different games dynamically
		ruleManager = new TicTacRuleManager(this);
		gameBoard = new TicTacBoard(ruleManager);
        ai = new TicTacAI(ruleManager, this);
        this.aiPlays = true;
	}

    public void yourTurn(String turnMessage) {
        boolean ok = false;
        if(aiPlays) {
            while(!ok) {
                ok = makeAIPlay();
            }
        } else {
            gui.setPlayersTurn();
        }
    }

	/**
	 * TODO: zorg dat het speelveld wordt geupdate voordat AI move mag doen
	 */
    public boolean makeAIPlay()
    {
        return makeMove(ai.nextMove().getCoordinate());
    }

	/**
	 * Returns the current game's gameboard
	 *
	 * @return
	 */
	public GameBoard getGameBoard()
	{
		return gameBoard;
	}

	/**
	 * Sets the current game's gameboard to the provided Gameboard
	 *
	 * @param gameBoard
	 */
	public void setGameBoard(GameBoard gameBoard)
	{
		this.gameBoard = gameBoard;
		gui.updateGameboard();
	}

	/**
	 * Returns the netmanager
	 *
	 * @return
	 */
	public NetManager getNetManager()
	{
		return netManager;
	}

	/**
	 * Sets the netmanager
	 *
	 * @param netManager
	 */
	public void setNetManager(NetManager netManager)
	{
		this.netManager = netManager;
	}

	/**
	 * Log into server with a specific name
	 *
	 * @param name
	 */
	public void login(String name)
	{
		netManager.login(name);
	}

	/**
	 * Subscribes to a specific game
	 *
	 * @param game
	 * @return
	 */
	public boolean subscribe(String game)
	{
		return netManager.subscribe(game);
	}

	// TODO: Quit the game
	public boolean quit()
	{
		return true;
	}

	/**
	 * sets the challenge to be accepted
	 *
	 * @param challenger
	 * @param challengeNumber
	 * @param gameType
	 */
	public void setChallenge(String challenger, int challengeNumber, String gameType)
	{
		gui.showChallengePopup(challengeNumber, challenger, gameType);
	}

	// TODO: Accept other player's challenge
	public boolean acceptChallenge(int challengeNumber, String game)
	{
		if (this.getNetManager().acceptChallenge(challengeNumber)) {
			switch (game) {
				case "Tic-tac-toe":
					this.setGameType("Tic-tac-toe");
					this.setRuleManager(new TicTacRuleManager(this));
					this.setGameBoard(new TicTacBoard(this.getRuleManager()));

					break;
				case "Reversi":
					this.setGameType("Reversi");
					this.setRuleManager(new ReversiRuleManager(this));
					this.setGameBoard(new ReversiGameBoard(this.getRuleManager()));

					break;
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Challenges a player to a game
	 *
	 * @param player The player to challenge
	 * @param game   The game to be played
	 * @return If successful, returns true
	 */
	public boolean challenge(String player, String game)
	{
		return netManager.challengePlayer(player, game);
	}

	/**
	 * Fetches current playerList
	 */
	public ArrayList<String> getPlayerList()
	{
		return netManager.fetchPlayerList();
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
	 *
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
	 *
	 * @param winloss      1 means win, 0 means draw, -1 means loss
	 * @param player1Score
	 * @param player2Score
	 * @param message
	 */
	public void endGame(int winloss, int player1Score, int player2Score, String message)
	{
		switch (winloss) {

		}
	}

	/**
	 * Notify gameManager when new game starts
	 * TODO: notify GUI of new game and move
	 *
	 * @param playerToMove
	 * @param gameType
	 * @param opponent
	 */
	public void setMatch(String playerToMove, String gameType, String opponent)
	{
		setOpponent(opponent);
		if (!playerToMove.equals(opponent)) {
            switch (gameType)
            {
                case "Tic-tac-toe":
                    this.setGameType("Tic-tac-toe");
                    this.setRuleManager(new TicTacRuleManager(this));
                    this.setGameBoard(new TicTacBoard(this.getRuleManager()));

                    break;
                case "Reversi":
                    this.setGameType("Reversi");
                    this.setRuleManager(new ReversiRuleManager(this));
                    this.setGameBoard(new ReversiGameBoard(this.getRuleManager()));

                    break;
            }
        }
	}

	/**
	 * fetches the gameList
	 */
	public ArrayList<String> getGameList()
	{
		return netManager.fetchGameList();
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
		netManager.forfeit();
		return true;
	}

	public boolean makeMove(int move)
	{
        System.out.println("AIMove: " + move);
        return netManager.sendMove(move);
	}

	// TODO: Return the game's board
	public GameBoard getGameboard()
	{
		return gameBoard;
	}

	// TODO: Reset game
	public boolean reset()
	{
		return true;
	}

	// TODO: Check if move is valid
	public boolean isValid(Field f)
	{
		return f == f;
	}

	/**
	 * Gets opponent name
	 *
	 * @return String - opponent
	 */
	public String getOpponent()
	{
		return opponent;
	}

	/**
	 * Sets opponent name
	 *
	 * @param opponent
	 */
	public void setOpponent(String opponent)
	{
		this.opponent = opponent;
	}

	/**
	 * Returns the current game type
	 *
	 * @return
	 */
	public String getGameType()
	{
		return gameType;
	}

	/**
	 * Sets the current game type
	 *
	 * @param gameType
	 */
	public void setGameType(String gameType)
	{
		this.gameType = gameType;
	}

	/**
	 * processes the move just played
	 *
	 * @param player
	 * @param move
	 * @param details
	 */
	public void setMove(String player, int move, String details)
	{
        if (!(player.equals(opponent)))
        {
            gameBoard.getBoard().get(move).setState(Field.STATE.Friendly);
        } else
        {
            gameBoard.getBoard().get(move).setState(Field.STATE.Enemy);
        }

        gui.appendHistory(player + " made move " + move + " " + details);
        gui.updateGameboard();
    }
}
