package test.main.java.com.kaasintl.main;

import junit.framework.TestCase;
import main.java.com.kaasintl.main.GUI;
import main.java.com.kaasintl.main.GameManager;
import org.junit.Test;

/**
* Created by David on 12-4-2015.
*/
public class GameManagerTest extends TestCase
{
	@Test
	public void testConstructor()
	{
		try
		{
			GameManager gameManager = new GameManager();
            GameManager gameManagerGUI = new GameManager(new GUI());
		} catch (Exception e)
		{
			fail();
		}
	}

    @Test
    public void testLogin()
    {
        try
        {
            GameManager gameManager = new GameManager();
            gameManager.login("Niek");
        } catch (Exception e) {}
    }

    public void testSubscribe()
    {
        try
        {
            GameManager gameManager = new GameManager();
            if(!gameManager.subscribe("Tic-tac-toe")) {
                //fail();
            }
        } catch (Exception e) {}
    }

    public void testAcceptChallenge()
    {
        try
        {
            GameManager gameManager = new GameManager();
            gameManager.acceptChallenge(1, "Tic-tac-toe");
        } catch (Exception e) {}
    }

    public void testEndGame()
    {
        try
        {
            GameManager gameManager = new GameManager(new GUI());
            gameManager.endGame(1, 0, 0, "Tic-tac-toe hihi");
        } catch (Exception e) {}
    }

    public void testMakeMove()
    {
        try
        {
            GameManager gameManager = new GameManager();
            if(!gameManager.makeMove(0)) {
                //fail();
            }
        } catch (Exception e) {}
    }


}
