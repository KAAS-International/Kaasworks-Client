package main.java.com.kaasintl.main;

import main.java.com.kaasintl.api.GameBoard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by david on 8-4-15.
 */
public class GUI
{

	public GameManager gameManager;
	public String userName = null;
	private JPanel mainPanel;
	private JTabbedPane tabbedPane;
	private JPanel statusPanel;
	private JPanel lobbyView;
	private JPanel boardView;
	private JButton playMoveButton;
	private JTextField move;
	private JTextArea gameHistory;
	private JProgressBar timeLeft;
	private JTable gameBoard;
	private JList lobbyPlayerList;
	private JButton lobbyRefreshButton;
	private JButton forfeitButton;
	private JButton challengeButton;
	private JPanel gameListPanel;
	private JList gameList;
	private JComboBox gameSelector;
	private JButton subscribeButton;
	private JLabel nameIndicator;
	private JLabel supportedGamesLabel;
	private JLabel playerListLabel;
	private JButton redrawBoardButton;
	private JScrollPane gameHistoyScrollpane;
	private String currentGame = "";
	private JFrame loginWindow;

	public GUI()
	{
		gameManager = new GameManager(this);
		showLogin(this);
	}

	/**
	 * Runs the main program
	 *
	 * @param args The arguments received from the CLI
	 */
	public static void main(String[] args)
	{
		//Game UI
		JFrame frame = new JFrame("GUI");

        /*
        try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look and feel.
		}*/

		frame.setContentPane(new GUI().mainPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.pack();
	}

	public void showMainGUI()
	{
		JFrame topframe = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);

		setMovementEnabled(false);

		mainPanel.setVisible(true);
		topframe.setVisible(true);
		topframe.setContentPane(mainPanel);

		loginWindow.setVisible(false);

		nameIndicator.setText("Logged in as " + userName);

		updateGameList();

		playMoveButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    if (gameManager.makeMove(Integer.parseInt(move.getText())))
                    {
                        gameHistory.setText(gameHistory.getText() + "\n You made move :" + move.getText());
                        setMovementEnabled(false);
                    } else
                    {
                        JOptionPane.showMessageDialog(null, "[ERROR] Failed to perform action: move " + move.getText(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex)
                {
                    gameHistory.setText(gameHistory.getText() + "\n Invalid move :" + move.getText());
                }
            }
        });
        challengeButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (gameManager.challenge((String) lobbyPlayerList.getSelectedValue(), gameSelector.getSelectedItem().toString()))
                {
                    gameHistory.setText(gameHistory.getText() + "\n You challenged " + lobbyPlayerList.getSelectedValue() + " to a game of " + gameSelector.getSelectedItem().toString());
                } else
                {
                    JOptionPane.showMessageDialog(null, "[ERROR] Failed to perform action: challenge " + lobbyPlayerList.getSelectedValue(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        lobbyRefreshButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				updateLobby();
			}
		});

		redrawBoardButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                updateGameboard();
            }
        });

		subscribeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                gameManager.subscribe(gameList.getSelectedValue().toString());
            }
        });

		//Initialize Data into view
		updateLobby();

        gameBoard.setModel(new DefaultTableModel(1, 1));
        gameBoard.getModel().setValueAt("Not currently in a game", 0, 0);
    }

    /**
     * Ends the game with a certain result, and draws the result to the screen
     * (called externally only)
     *
     * @param winloss      1 means win, 0 means draw, -1 means loss
     * @param player1Score
     * @param player2Score
     * @param message
     */
    public void endGame(int winloss, int player1Score, int player2Score, String message)
    {
        switch (winloss)
        {
            case -1:
                appendHistory("You lost the game. \n Player1 scored: " + player1Score + ", Player2 scored " + player2Score);
                break;

            case 0:
                appendHistory("The game resulted in a draw. \n Player1 scored: " + player1Score + ", Player2 scored " + player2Score);
                break;

            case 1:
                appendHistory("You won the game!!!! Good job " + getUserName() + "!! \n Player1 scored: " + player1Score + ", Player2 scored " + player2Score);
                break;
        }

        gameBoard.setModel(new DefaultTableModel(1, 1));
        gameBoard.getModel().setValueAt("Not currently in a game", 0, 0);
    }

    /**
     * Updates the lobby list
     */
    private void updateLobby()
	{
		// get list of players
		ArrayList<String> players = gameManager.getPlayerList();

		String[] listData = new String[players.size()];

		for (int i = 0; i < players.size(); i++) {
			listData[i] = players.get(i);
		}

		DefaultListModel<String> newListModel = new DefaultListModel<String>();

		for (String s : listData) {
			if (s.equals(userName)) {
				s = s + " (you)";
			}
			newListModel.addElement(s);
		}

		lobbyPlayerList.setModel(newListModel);
		lobbyPlayerList.updateUI();

		String timeStamp = new SimpleDateFormat("HH-mm-ss").format(Calendar.getInstance().getTime());

		playerListLabel.setText("Lobby (last updated:  " + timeStamp + ")");
	}

	/**
	 * Updates the gamelist
	 */
	private void updateGameList()
	{
		ArrayList<String> gList = gameManager.getGameList();

		DefaultListModel listModel = new DefaultListModel<String>();
		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();

		for (String s : gList) {
			listModel.addElement(s);
			comboBoxModel.addElement(s);
		}

		gameList.setModel(listModel);
		gameSelector.setModel(comboBoxModel);
	}

	/**
	 * Updates the gameboard
	 */
	public void updateGameboard()
	{
		GameBoard gb = gameManager.getGameboard();
		int boardSize = gb.getBoardHeight();
		TableModel tableModel = new DefaultTableModel(boardSize, boardSize);

		for (int i = 0; i < gb.getBoard().size(); i++) {
			tableModel.setValueAt("(" + i + ")" + gb.getBoard().get(i).token, i / boardSize, i % boardSize);
		}

		gameBoard.setModel(tableModel);
		gameBoard.updateUI();

		return;
	}

	/**
	 * Called when the game starts to make the GUI update the view
	 */
	public void startGame()
	{
		updateGameboard();
		tabbedPane.setSelectedIndex(1);
		setMovementEnabled(true);
	}

	/**
	 * Enables, or disables the ability to enter moves by the player
	 *
	 * @param b
	 */
	public void setMovementEnabled(boolean b)
	{
		move.setEnabled(b);
		playMoveButton.setEnabled(b);
		forfeitButton.setEnabled(b);
	}

	/**
	 * Sets to this players turn
	 */
	public void setPlayersTurn()
	{
		setMovementEnabled(true);
		updateGameboard();
	}

    /**
     * Shows a popup prompting the player to accept or decline a challenge from another player
     * @param challengeNumber
     * @param player
     * @param game
     */
    public void showChallengePopup(int challengeNumber, String player, String game)
	{
		JFrame frame = new JFrame("You have been challenged");

		frame.setContentPane(new challengeWindow(this, challengeNumber, player, game).getMainPanel());
		frame.pack();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
	}

	/**
	 * Shows the login window
	 *
	 * @param gui
	 */
	public void showLogin(GUI gui)
	{
		JFrame frame = new JFrame("Login");

		frame.setContentPane(new LoginWindow(gui).getMainPanel());
		frame.pack();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.loginWindow = frame;

		frame.setVisible(true);
	}

    /**
     * Gets the current player's username
     * @return
     */
    public String getUserName()
	{
		return userName;
	}

    /**
     * Sets the current players username
     * @param userName
     */
    public void setUserName(String userName)
	{
		gameManager.login(userName);
		this.userName = userName;
	}

    /**
     * Appends a new line to the game history
     *
     * @param str
     */
    public void appendHistory(String str)
    {
        gameHistory.setText(gameHistory.getText() + "\n" + str);
    }

    /**
     * Gets the gamename currently stored in the GUI
     * @return
     */
    public String getCurrentGame()
	{
		return currentGame;
	}

    /**
     * Sets the game the GUI thinks is currently being played
     * @param currentGame
     */
    public void setCurrentGame(String currentGame)
	{
		this.currentGame = currentGame;
	}
}
