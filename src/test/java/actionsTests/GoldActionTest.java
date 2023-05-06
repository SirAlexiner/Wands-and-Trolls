package actionsTests;

import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.gr13.Player;
import no.ntnu.idatg2001.gr13.actions.GoldAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GoldActionTest {
    int gold = 10;
    Player player;
    GoldAction goldAction;
    @BeforeEach
    void setUp(){
        player = new Player("Test Player", 10, gold, 10);
        goldAction = new GoldAction(gold);
    }

    // TODO WIP
    /*
    @Test
    void testExecute() {
        GoldAction otherGoldAction = new GoldAction(100);
        otherGoldAction.execute(player);
        int actual = player.getGold();
        int expected = 100 + gold;
        // Positive
        assertEquals(expected, actual);
        // Negative
        int unexpected = 10;
        assertNotEquals(unexpected, actual);
    }

     */

    @Test
    void testGetActionType() {
        String expected = "Gold";
        String actual = goldAction.getActionType();
        // Positive
        assertEquals(expected, actual);
        // Negative
        String unexpected = "Health";
        assertNotEquals(unexpected, actual);
    }

    @Test
    void testGetActionValue() {
        String expected = "10";
        String actual = goldAction.getActionValue();
        // Positive
        assertEquals(expected, actual);
        // Negative
        String unexpected = "Health";
        assertNotEquals(unexpected, actual);
    }
    @Test
    void testIsFulFilled() {
        goldAction.execute(player);
        player.addGold(-11);
        // Negative
        assertFalse(goldAction.isFulFilled(player));
        // Positive
        player.addGold(10);
        assertTrue(goldAction.isFulFilled(player));
    }
}
