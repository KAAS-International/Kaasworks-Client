package test.main.java.com.kaasintl.main;

import junit.framework.TestCase;
import main.java.com.kaasintl.main.NetManager;
import org.junit.Test;
/**
 * Created by Niek on 12-4-2015.
 */
public class NetManagerTest extends TestCase{
    @Test
    public void testParser() {
        try{
            NetManager netManager = new NetManager();

            netManager.parser("SVR GAMELIST [\"Guess Game\", \"Guess Game Deluxe\", \"Ultra Guess Game\"]");
        } catch(Exception e) {
            fail();
        }
    }
}
