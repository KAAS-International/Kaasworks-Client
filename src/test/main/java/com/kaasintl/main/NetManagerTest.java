package test.main.java.com.kaasintl.main;

import junit.framework.TestCase;
import main.java.com.kaasintl.main.GameManager;
import main.java.com.kaasintl.main.NetManager;
import org.junit.Test;
/**
 * Created by Niek on 12-4-2015.
 */
public class NetManagerTest extends TestCase{
    @Test
    public void testParser() {
        try{
            GameManager gameManager = new GameManager();
            gameManager.login("Niek");
            gameManager.getPlayerList();
            System.out.println(gameManager.getPlayerList().isEmpty());

            //netManager.parser("SVR GAMELIST [\"Guess Game\", \"Guess Game Deluxe\", \"Ultra Guess Game\"]");
            //netManager.parser("SVR PLAYERLIST [\"Niek\", \"Tanja\", \"Kevin\", \"David\"]");
        } catch(Exception e) {
            //e.printStackTrace();
            fail();
        }
    }
}
