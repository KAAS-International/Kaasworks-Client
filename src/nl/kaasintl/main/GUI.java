package nl.kaasintl.main;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by david on 8-4-15.
 */
public class GUI {

    private GameManager gameManager;

    private JPanel       mainPanel;
    private JTabbedPane  tabbedPane;
    private JPanel       statusPanel;
    private JPanel       lobbyView;
    private JPanel       boardView;
    private JButton      doMove;
    private JTextField   move;
    private JTextArea    gameHistory;
    private JProgressBar timeLeft;
    private JTable       gameBoard;
    private JList playerList;

    public GUI()
    {
        this.gameManager = new GameManager();

        updateLobby();
    }

    public static void main(String[] args)
    {
        //Game UI
        JFrame frame = new JFrame("GUI");

        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        frame.setContentPane(new GUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
    }

    private void updateLobby()
    {
        // get list of players
        ArrayList<String> players = (ArrayList<String>) gameManager.getPlayerList();

        String[] listdata = new String[players.size()];

        for (int i = 0; i < players.size(); i++)
        {
            listdata[i] = players.get(i);
        }

        DefaultListModel<String> newListModel = new DefaultListModel<String>();

        for (String s : listdata)
        {
            newListModel.addElement(s);
        }

        playerList.setModel(newListModel);
        playerList.updateUI();
    }
}
