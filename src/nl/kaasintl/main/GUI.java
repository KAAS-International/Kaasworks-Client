package nl.kaasintl.main;

import javax.swing.*;

/**
 * Created by david on 8-4-15.
 */
public class GUI {

    private JPanel mainPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
