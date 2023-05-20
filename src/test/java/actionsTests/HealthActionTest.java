package actionsTests;

import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.gr13.model.Player;
import no.ntnu.idatg2001.gr13.model.actions.HealthAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class HealthActionTest {
    Player player;
    HealthAction healthAction;
    @BeforeEach
    void setUp(){
        player = new Player("Test Player", 0, 10, 10);
        healthAction = new HealthAction(0);
    }

    @Test
    void testExecute() {
        HealthAction otherHealthAction = new HealthAction(100);
        otherHealthAction.execute(player);
        int actual = player.getHealth();
        int expected = 100;
        // Positive
        assertEquals(expected, actual);
        // Negative
        int unexpected = 10;
        assertNotEquals(unexpected, actual);
    }

    @Test
    void testGetActionType() {
        String expected = "Health";
        String actual = healthAction.getActionType();
        // Positive
        assertEquals(expected, actual);
        // Negative
        String unexpected = "Gold";
        assertNotEquals(unexpected, actual);
    }

    @Test
    void testGetActionValue() {
        String expected = "0";
        String actual = healthAction.getActionValue();
        // Positive
        assertEquals(expected, actual);
        // Negative
        String unexpected = "Gold";
        assertNotEquals(unexpected, actual);
    }

    /*@Test
    void testIsFulFilled() {
        HealthAction healthGoal = new HealthAction(0);
        healthGoal.execute(player);
        player.addHealth(-1);
        // Positive
        assertTrue(healthGoal.isFulFilled(player), "Player died");
        // Negative
        player.addHealth(10);
        assertFalse(healthGoal.isFulFilled(player), "Player is alive");
    }

     */
}
