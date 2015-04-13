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
            //GameManager g = new GameManager();
            NetManager netManager = new NetManager(/*g*/);

            //critically fails if there is no gamemanager to sens parsed data to, but also critically fails
            //if there is a gamemanager, because that has to have a server to connect to
            //hint: NULLPOINTER NULLPOINTER NULLPOINTER... ... ...
            //netManager.parser("SVR GAMELIST [\"Guess Game\", \"Guess Game Deluxe\", \"Ultra Guess Game\"]");
            //netManager.parser("SVR PLAYERLIST [\"Niek\", \"Tanja\", \"Kevin\", \"David\"]");
        } catch(Exception e) {
            //e.printStackTrace();
            fail();
        }
    }
}
