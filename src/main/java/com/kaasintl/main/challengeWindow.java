package main.java.com.kaasintl.main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by david on 16-4-15.
 */
public class challengeWindow
{
	private JButton acceptButton;
	private JButton declineButton;
	private JLabel challengeText;
	private JPanel mainPanel;

	public challengeWindow(final GUI gui, final int challengeNumber, final String player, final String game)
	{
		challengeText.setText("You have been invited to a game of " + game + " by " + player + "( challenge id: " + challengeNumber + ")");

		acceptButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent actionEvent)
			{
                closeWindow();
                gui.gameManager.acceptChallenge(challengeNumber, game);
			}
		});

		declineButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				closeWindow();
			}
		});
	}

	public void closeWindow()
	{
		JFrame topframe = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
		topframe.setVisible(false);
	}

	public JPanel getMainPanel()
	{
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel)
	{
		this.mainPanel = mainPanel;
	}
}
