package nl.kaasintl.main;

import javax.swing.*;

/**
 * Created by david on 8-4-15.
 */
public class GUI {

    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JPanel statusPanel;
    private JPanel lobbyView;
    private JPanel boardView;

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");

        /*
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        */
        
        frame.setContentPane(new GUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
    }
}
