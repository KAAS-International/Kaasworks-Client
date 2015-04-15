package main.java.com.kaasintl.main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by david on 14-4-15.
 */
public class LoginWindow {
    public GUI gui;
    private JTextField textField1;
    private JButton logInButton;
    private JPanel mainPanel;

    public LoginWindow(final GUI gui) {
        this.gui = gui;

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (textField1.getText() == "" || textField1.getText() == null) {
                    // do nothing
                    textField1.setText("Please enter a name");
                } else {
                    gui.setUserName(textField1.getText());
                    gui.showMainGUI();
                    JFrame topframe = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                    topframe.setVisible(false);
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
