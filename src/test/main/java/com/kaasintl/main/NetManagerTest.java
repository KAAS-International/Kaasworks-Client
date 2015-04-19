package test.main.java.com.kaasintl.main;

import junit.framework.TestCase;
import main.java.com.kaasintl.main.GameManager;
import org.junit.Test;

/**
* Created by Niek on 12-4-2015.
*/
public class NetManagerTest extends TestCase {

    @Test
    public void testParser() {
        try{

            GameManager gameManager = new GameManager();
            gameManager.login("Niek");

        } catch(Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
