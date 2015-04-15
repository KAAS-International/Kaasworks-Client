package test.main.java.com.kaasintl.main;

import junit.framework.TestCase;
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
		} catch (Exception e)
		{
			fail();
		}
	}
}
